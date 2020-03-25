package vgame.ir.view.ui.main.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import vgame.ir.data.ModelConvertor;
import vgame.ir.data.Status;
import vgame.ir.data.local.entity.CourseEntity;
import vgame.ir.data.model.Teacher;
import vgame.ir.data.remote.model.AllGamesResponse;
import vgame.ir.data.remote.model.SearchModel;
import vgame.ir.databinding.ItemCourseListBinding;
import vgame.ir.databinding.ItemGamesListBinding;
import vgame.ir.databinding.ItemTechersListBinding;
import vgame.ir.databinding.NetworkItemBinding;

public class AllGamesPagingAdapter extends PagedListAdapter<AllGamesResponse.Game, RecyclerView.ViewHolder> {

    private Status networkState;

    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_GAME = 1;

    public interface CallBack {
        void onTeacherClick(AllGamesResponse.Game teacher, View sharedView);

        void onCourseClick(CourseEntity course, View sharedView);
    }

    private CallBack callBack;
    private Context context;

    protected AllGamesPagingAdapter(Context context, CallBack callBack) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_PROGRESS) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            NetworkItemBinding headerBinding = NetworkItemBinding.inflate(layoutInflater, viewGroup, false);
            NetworkStateItemViewHolder viewHolder = new NetworkStateItemViewHolder(headerBinding);
            return viewHolder;
        } else {
            return TeacherHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, callBack);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder itemHolder, int i) {
        if (itemHolder instanceof TeacherHolder) {
            AllGamesResponse.Game searchModel = getItem(i);
            ((TeacherHolder) itemHolder).onBind(searchModel);
        } else {
            ((NetworkStateItemViewHolder) itemHolder).bindView(networkState);
        }
    }

    public void setNetworkState(Status newNetworkState) {
        Status previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_GAME;
        }
    }

    private boolean hasExtraRow() {
        if (networkState != null && networkState != Status.SUCCESS) {
            return true;
        } else {
            return false;
        }
    }

    public class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {

        private NetworkItemBinding binding;

        public NetworkStateItemViewHolder(NetworkItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(Status networkState) {
            if (networkState == Status.LOADING) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }

            if (networkState == Status.ERROR) {
                binding.errorMsg.setVisibility(View.VISIBLE);
                // binding.errorMsg.setText(networkState.getMsg());
            } else {
                binding.errorMsg.setVisibility(View.GONE);
            }
        }
    }

    private static DiffUtil.ItemCallback<AllGamesResponse.Game> DIFF_CALLBACK = new DiffUtil.ItemCallback<AllGamesResponse.Game>() {
        @Override
        public boolean areItemsTheSame(@NonNull AllGamesResponse.Game s1, @NonNull AllGamesResponse.Game s2) {
            if (s1.getId().equals(s2.getId())) {
                return true;
            }
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull AllGamesResponse.Game s1, @NonNull AllGamesResponse.Game s2) {
            if (s1.getBackground_image().equals(s2.getBackground_image())) {
                return true;
            }
            return false;
        }
    };

    public static class TeacherHolder extends RecyclerView.ViewHolder {

        ItemGamesListBinding binding;

        public static TeacherHolder create(LayoutInflater inflater, ViewGroup parent, AllGamesPagingAdapter.CallBack callback) {
            ItemGamesListBinding inflate = ItemGamesListBinding.inflate(inflater, parent, false);
            return new TeacherHolder(inflate, callback);
        }

        public TeacherHolder(ItemGamesListBinding binding, AllGamesPagingAdapter.CallBack callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onTeacherClick(binding.getGame(), binding.imgAvatar));
        }

        public void onBind(AllGamesResponse.Game teacher) {
            binding.setGame(teacher);
            binding.executePendingBindings();
        }
    }
}
