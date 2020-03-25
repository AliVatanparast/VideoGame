package vgame.ir.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vgame.ir.data.Resource;
import vgame.ir.view.base.BaseAdapter;

public final class ListBindingAdapter {
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null)
            return;

        if (resource == null)
            return;

       /* if (resource.status.equals(Status.ERROR)) {
            recyclerView.setVisibility(View.GONE);

            return;
        }*/

        if(resource.data == null){
            return;
        }

       // recyclerView.setVisibility(View.VISIBLE);

        if (adapter instanceof BaseAdapter) {
            ((BaseAdapter) adapter).setData((List) resource.data);
        }
    }
}
