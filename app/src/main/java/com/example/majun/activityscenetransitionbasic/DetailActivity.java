package com.example.majun.activityscenetransitionbasic;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by majun on 17/3/19.
 * 普通的activity是默认不带actionbar的
 */
public class DetailActivity extends Activity {
    public static final String PARAM_ID = "param_id";
    public static final String VIEW_IMAGE = "view_image";
    public static final String VIEW_TITLE = "view_title";

    private ImageView mImageView;
    private TextView mTitle;
    private TextView mBoby;
    private Item mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        mImageView = (ImageView) findViewById(R.id.big_imageview);
        mTitle = (TextView) findViewById(R.id.title);
        mBoby = (TextView) findViewById(R.id.body);
        ViewCompat.setTransitionName(mImageView, VIEW_IMAGE);
        ViewCompat.setTransitionName(mTitle, VIEW_TITLE);
        mItem = Item.getItem(getIntent().getIntExtra(PARAM_ID, 0));
        mBoby.setText(R.string.bacon_ipsum);
        mTitle.setText(mItem.getName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition transition = getWindow().getSharedElementEnterTransition();
            if (transition != null) {
                transition.addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {
                        Glide.with(mImageView.getContext()).load(mItem.getPhotoUrl()).into(mImageView);
                        transition.removeListener(this);
                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {

                    }

                    @Override
                    public void onTransitionCancel(Transition transition) {
                        transition.removeListener(this);
                    }

                    @Override
                    public void onTransitionPause(Transition transition) {

                    }

                    @Override
                    public void onTransitionResume(Transition transition) {

                    }
                });
            } else {
                Glide.with(this).load(mItem.getPhotoUrl()).into(mImageView);
            }
        } else {
            Glide.with(this).load(mItem.getPhotoUrl()).into(mImageView);
        }
    }

}
