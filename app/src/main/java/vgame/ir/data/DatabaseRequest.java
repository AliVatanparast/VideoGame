package vgame.ir.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

import retrofit2.Call;

public abstract class DatabaseRequest<ResultType> {
    private final MediatorLiveData<Resource> result = new MediatorLiveData<>();

    @MainThread
    public DatabaseRequest() {
        result.setValue(Resource.Companion.loading(null));
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                createRequest();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                result.setValue(Resource.Companion.success(null));
            }
        }.execute();
    }

    @NonNull
    protected abstract Call<ResultType> createRequest();

    public final LiveData<Resource> getAsLiveData() {
        return result;
    }
}
