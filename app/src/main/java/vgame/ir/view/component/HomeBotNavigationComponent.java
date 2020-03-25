package vgame.ir.view.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import vgame.ir.R;

public class HomeBotNavigationComponent extends LinearLayout {

    public interface Callback {
        void onSearchClick();

        void onHomeClick();

        void onProfileClick();
    }

    private Callback callback;

    private AwsomeTextCell btn_search, btn_profile, btn_home;

    public HomeBotNavigationComponent(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.component_home_btn_navigation, this, true);

        btn_search = view.findViewById(R.id.btn_search);
        btn_profile = view.findViewById(R.id.btn_profile);
        btn_home = view.findViewById(R.id.btn_home);

        btn_home.setOnClickListener(v -> {
            select(1);
            if (callback != null) {
                callback.onHomeClick();
            }
        });

        btn_profile.setOnClickListener(v -> {
            select(2);
            if (callback != null) {
                callback.onProfileClick();
            }
        });

        btn_search.setOnClickListener(v -> {
            select(0);
            if (callback != null) {
                callback.onSearchClick();
            }
        });
    }

    public void select(int position) {
        btn_search.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        btn_profile.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        btn_home.setBackground(getResources().getDrawable(R.drawable.circle_fill_accent));

        btn_search.setTextColor(getResources().getColor(R.color.md_grey_900));
        btn_profile.setTextColor(getResources().getColor(R.color.md_grey_900));
        btn_home.setTextColor(getResources().getColor(R.color.md_grey_900));

        AwsomeTextCell awsomeTextCell;
        switch (position) {
            case 0:
                awsomeTextCell = btn_search;
                break;
            case 1:
                awsomeTextCell = btn_home;
                break;
            case 2:
                awsomeTextCell = btn_profile;
                break;
            default:
                awsomeTextCell = btn_home;
        }

        awsomeTextCell.setTextColor(getResources().getColor(R.color.md_white_1000));

        if (awsomeTextCell.getId() == R.id.btn_home) {
            awsomeTextCell.setBackground(getResources().getDrawable(R.drawable.circle_fill_primary));
        } else {
            awsomeTextCell.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
