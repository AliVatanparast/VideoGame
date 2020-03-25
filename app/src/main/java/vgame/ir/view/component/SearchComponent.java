package vgame.ir.view.component;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

import vgame.ir.R;
import vgame.ir.utils.UtilMethods;

public class SearchComponent extends LinearLayout implements View.OnClickListener {

    private EditTextCell edt_input;
    private TextviewCell txt_teacher, txt_lesson, txt_course;

    private HashMap<String, String> filters = new HashMap<>();

    public final static String TEXT = "text";
    public final static String TYPE = "type";
    public final static String TEACHER = "teacher";
    public final static String COURSE = "course";
    public final static String LESSON = "lesson";

    public interface Callback {
        void onSearchChange(Map<String, String> filters);
    }

    private Callback callback;

    public SearchComponent(Context context) {
        super(context);
        init(context);
    }

    public SearchComponent(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init(context);
    }

    Handler handler = new Handler(Looper.getMainLooper());
    Runnable workRunnable;

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.component_search, this, true);

        edt_input = view.findViewById(R.id.edt_input);

        txt_teacher = view.findViewById(R.id.txt_teacher);
        txt_lesson = view.findViewById(R.id.txt_lesson);
        txt_course = view.findViewById(R.id.txt_course);

        edt_input.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
               /* timer.cancel();
                timer = new Timer();
                timer.schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                if (callback != null) {
                                    UtilMethods.replaceOrAddItemMap(filters, TEXT, s.toString());
                                    callback.onSearchChange(filters);
                                }
                            }
                        },
                        DELAY
                );*/

                handler.removeCallbacks(workRunnable);
                workRunnable = () -> {
                    if (callback != null) {
                        UtilMethods.replaceOrAddItemMap(filters, TEXT, s.toString());
                        callback.onSearchChange(filters);
                    }
                };
                handler.postDelayed(workRunnable, 700 /*delay*/);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("", "");
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        txt_course.setOnClickListener(this);
        txt_lesson.setOnClickListener(this);
        txt_teacher.setOnClickListener(this);

        selectType(txt_teacher);
        UtilMethods.replaceOrAddItemMap(filters, TYPE, TEACHER);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_teacher:
                if (callback != null) {
                    UtilMethods.replaceOrAddItemMap(filters, TYPE, TEACHER);
                    callback.onSearchChange(filters);
                }
                break;
            case R.id.txt_lesson:
                if (callback != null) {
                    UtilMethods.replaceOrAddItemMap(filters, TYPE, LESSON);
                    callback.onSearchChange(filters);
                }
                break;
            case R.id.txt_course:
                if (callback != null) {
                    UtilMethods.replaceOrAddItemMap(filters, TYPE, COURSE);
                    callback.onSearchChange(filters);
                }
                break;
        }

        selectType((TextviewCell) view);
    }

    private void selectType(TextviewCell textviewCell) {
        txt_course.setTextColor(getResources().getColor(R.color.md_grey_500));
        txt_lesson.setTextColor(getResources().getColor(R.color.md_grey_500));
        txt_teacher.setTextColor(getResources().getColor(R.color.md_grey_500));

        textviewCell.setTextColor(getResources().getColor(R.color.md_grey_900));
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
