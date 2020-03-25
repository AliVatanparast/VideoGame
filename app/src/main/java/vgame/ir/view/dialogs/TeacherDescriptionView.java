package vgame.ir.view.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import vgame.ir.R;
import vgame.ir.view.component.TextviewCell;


public class TeacherDescriptionView extends FrameLayout {

    public TeacherDescriptionView(Context context, String description) {
        super(context);
        init(context , description);
    }

    private void init(Context context, String str) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_teacher_description, this, true);

        ((TextviewCell) view.findViewById(R.id.txt_description)).setText(str);
    }
}
