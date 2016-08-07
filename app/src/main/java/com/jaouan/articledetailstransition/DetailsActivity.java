package com.jaouan.articledetailstransition;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaouan.articledetailstransition.models.Article;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    /**
     * Extra key for article.
     */
    public static final String EXTRA_ARTICLE = "article";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.date)
    TextView mDate;

    @BindView(R.id.dark_title)
    TextView mDarkTitle;

    @BindView(R.id.dark_date)
    TextView mDarkDate;

    @BindView(R.id.header)
    ImageView mHeader;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;

    @BindView(R.id.dark_title_container)
    FrameLayout mDarkTitleContainer;

    @BindView(R.id.dark_title_content)
    LinearLayout mDarkTitleContent;

    @BindView(R.id.detail)
    TextView mDetail;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bindArticleDatas();

        beginEnterTransition();
    }


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Begins enter transition.
     */
    private void beginEnterTransition() {
        mHeader.post(new Runnable() {
            @Override
            public void run() {

                final Animator circularReveal = ViewAnimationUtils.createCircularReveal(mHeader, mHeader.getWidth() / 2, 0, 0, mHeader.getWidth());
                circularReveal.setInterpolator(new AccelerateInterpolator(1.5f));
                circularReveal.setDuration(500);
                circularReveal.start();

                mDarkTitleContent.startAnimation(AnimationUtils.loadAnimation(DetailsActivity.this, R.anim.translatedownandfadein));
                mDarkTitleContainer.startAnimation(AnimationUtils.loadAnimation(DetailsActivity.this, R.anim.scaleheight));
                mDetail.startAnimation(AnimationUtils.loadAnimation(DetailsActivity.this, R.anim.fadein));

            }
        });
    }

    /**
     * Binds article datas.
     */
    private void bindArticleDatas() {
        final Article article = (Article) getIntent().getExtras().get(EXTRA_ARTICLE);
        mHeader.setImageResource(article.getHeader());
        mDarkTitleContainer.setBackgroundResource(article.getBackgroundColor());
        mTitle.setText(article.getTitle());
        mDarkTitle.setText(article.getTitle());
        mDate.setText(article.getDate());
        mDarkDate.setText(article.getDate());
    }

}
