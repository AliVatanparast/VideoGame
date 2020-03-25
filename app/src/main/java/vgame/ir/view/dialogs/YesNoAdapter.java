package vgame.ir.view.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import vgame.ir.R;

public class YesNoAdapter extends BaseAdapter {

    private Context context;
    private String strYes, strNo;

    public YesNoAdapter(Context context, String strYes, String strNo) {
        this.context = context;
        this.strNo = strNo;
        this.strYes = strYes;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.simple_list_item, null);

        Holder holder = new Holder(view.findViewById(R.id.text_view), view.findViewById(R.id.image_view));

        if (i == 0) {
            holder.textView.setText(strYes);
        } else {
            holder.textView.setText(strNo);
        }

        return view;
    }

    public class Holder {
        private TextView textView;
        private ImageView imageView;

        public Holder(TextView textView, ImageView imageView) {
            this.textView = textView;
            this.imageView = imageView;
        }
    }
}
