package vgame.ir.exoplayer

import android.content.Context

import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.LoadControl
import com.google.android.exoplayer2.RenderersFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.database.DatabaseProvider
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.offline.ActionFileUpgradeUtil
import com.google.android.exoplayer2.offline.DefaultDownloadIndex
import com.google.android.exoplayer2.offline.DefaultDownloaderFactory
import com.google.android.exoplayer2.offline.DownloadManager
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.upstream.FileDataSourceFactory
import com.google.android.exoplayer2.upstream.HttpDataSource
import com.google.android.exoplayer2.upstream.cache.Cache
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import com.google.android.exoplayer2.util.Log
import com.google.android.exoplayer2.util.Util

import java.io.File
import java.io.IOException

import vgame.ir.BuildConfig
import vgame.ir.app.AppLoader

class ExoUtils {

    private var downloadManager: DownloadManager? = null
    private var downloadTracker: DownloadTracker? = null

    private var databaseProvider: DatabaseProvider? = null
    private var downloadDirectory: File? = null
    var playerNotificationManager: PlayerNotificationManager? = null
    var playing_id = 0

    fun getDownloadManager(): DownloadManager? {
        initDownloadManager()
        return downloadManager
    }

    fun getDownloadTracker(): DownloadTracker? {
        initDownloadManager()
        return downloadTracker
    }

    fun buildRenderersFactory(preferExtensionRenderer: Boolean): RenderersFactory {
        @DefaultRenderersFactory.ExtensionRendererMode
        val extensionRendererMode = if (useExtensionRenderers())
            if (preferExtensionRenderer)
                DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER
            else
                DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON
        else
            DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF
        return DefaultRenderersFactory(/* context= */AppLoader.instance)
                .setExtensionRendererMode(extensionRendererMode)
    }

    @Synchronized
    private fun initDownloadManager() {
        if (downloadManager ==
                /* eventListener= */ null) {
            val downloadIndex = DefaultDownloadIndex(getDatabaseProvider())
            upgradeActionFile(
                    DOWNLOAD_ACTION_FILE, downloadIndex, /* addNewDownloadsAsCompleted= */ false)
            upgradeActionFile(
                    DOWNLOAD_TRACKER_ACTION_FILE, downloadIndex, /* addNewDownloadsAsCompleted= */ true)
            val downloaderConstructorHelper = DownloaderConstructorHelper(getDownloadCache(), buildHttpDataSourceFactory())
            downloadManager = DownloadManager(
                    AppLoader.instance, downloadIndex, DefaultDownloaderFactory(downloaderConstructorHelper))
            downloadTracker = DownloadTracker(/* context= */AppLoader.instance, buildDataSourceFactory(), downloadManager!!)
        }
    }

    private fun getDatabaseProvider(): DatabaseProvider {
        if (databaseProvider == null) {
            databaseProvider = ExoDatabaseProvider(AppLoader.instance)
        }
        return databaseProvider!!
    }

    private fun getDownloadDirectory(): File? {
        if (downloadDirectory == null) {
            downloadDirectory = AppLoader.instance.getExternalFilesDir(null)
            if (downloadDirectory == null) {
                downloadDirectory = AppLoader.instance.filesDir
            }
        }
        return downloadDirectory
    }

    @Synchronized
    protected fun getDownloadCache(): Cache {
        if (downloadCache == null) {
            val downloadContentDirectory = File(getDownloadDirectory(), DOWNLOAD_CONTENT_DIRECTORY)
            downloadCache = SimpleCache(downloadContentDirectory, NoOpCacheEvictor(), getDatabaseProvider())
        }
        return downloadCache!!
    }

    private fun upgradeActionFile(
            fileName: String, downloadIndex: DefaultDownloadIndex, addNewDownloadsAsCompleted: Boolean) {
        try {
            ActionFileUpgradeUtil.upgradeAndDelete(
                    File(getDownloadDirectory(), fileName), null,
                    downloadIndex,
                    /* deleteOnFailure= */ true,
                    addNewDownloadsAsCompleted)/* downloadIdProvider= */
        } catch (e: IOException) {
            Log.e(TAG, "Failed to upgrade action file: $fileName", e)
        }

    }

    /**
     * Returns a [DataSource.Factory].
     */
    fun buildDataSourceFactory(): DataSource.Factory {
        val upstreamFactory = DefaultDataSourceFactory(AppLoader.instance, buildHttpDataSourceFactory())
        return buildReadOnlyCacheDataSource(upstreamFactory, getDownloadCache())
    }

    /**
     * Returns a [HttpDataSource.Factory].
     */
    fun buildHttpDataSourceFactory(): HttpDataSource.Factory {
        return DefaultHttpDataSourceFactory(userAgent)
    }

    /**
     * Returns whether extension renderers should be used.
     */
    fun useExtensionRenderers(): Boolean {
        return "withExtensions" == BuildConfig.FLAVOR
    }

    companion object {

        private var instance: ExoUtils? = null

        private val TAG = "DemoApplication"
        private val DOWNLOAD_ACTION_FILE = "actions"
        private val DOWNLOAD_TRACKER_ACTION_FILE = "tracked_actions"
        private val DOWNLOAD_CONTENT_DIRECTORY = "downloads"
        var downloadCache: Cache? = null

        lateinit var userAgent: String

        lateinit var app_player: SimpleExoPlayer

        private var defaultBandwidthMeter: DefaultBandwidthMeter? = null
        private var videoTrackSelectionFactory: TrackSelection.Factory? = null
        private var trackSelector: DefaultTrackSelector? = null
        lateinit var extractorsFactory: ExtractorsFactory
        lateinit var dateSourceFactory: DataSource.Factory

        fun getInstance(context: Context): ExoUtils {
            if (instance == null) {
                instance = ExoUtils()

                userAgent = Util.getUserAgent(context, "ExoPlayerDemo")

                defaultBandwidthMeter = DefaultBandwidthMeter()
                videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(defaultBandwidthMeter)
                trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)


                //RenderersFactory renderersFactory = new DefaultRenderersFactory(context, null, DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF);
                // app_player = ExoPlayerFactory.newSimpleInstance(context ,renderersFactory, trackSelector);

                val loadControl = DefaultLoadControl()
                app_player = ExoPlayerFactory.newSimpleInstance(context, trackSelector!!, loadControl)

                extractorsFactory = DefaultExtractorsFactory()

                dateSourceFactory = DefaultDataSourceFactory(context, userAgent, defaultBandwidthMeter)

            }
            return instance!!
        }

        protected fun buildReadOnlyCacheDataSource(
                upstreamFactory: DataSource.Factory, cache: Cache): CacheDataSourceFactory {
            return CacheDataSourceFactory(
                    cache,
                    upstreamFactory,
                    FileDataSourceFactory(), null,
                    CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR, null)/* cacheWriteDataSinkFactory= */
        }
    }
}
