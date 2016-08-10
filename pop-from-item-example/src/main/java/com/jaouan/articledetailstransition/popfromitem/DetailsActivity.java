package com.jaouan.articledetailstransition.popfromitem;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaouan.articledetailstransition.popfromitem.models.Article;
import com.jaouan.viewsfrom.Views;

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

    @BindView(R.id.header)
    ImageView mHeader;

    @BindView(R.id.title_content)
    View mTitleContent;

    @BindView(R.id.detail)
    TextView mDetail;

    @BindView(R.id.details_content)
    ViewGroup mDetailsContent;

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

    /**
     * Begins enter transition.
     */
    private void beginEnterTransition() {
        Views.from(mDetailsContent).animateWith(this, R.anim.translaterightandfadein).withDelayBetweenEachChild(50).start();
    }

    /**
     * Binds article datas.
     */
    private void bindArticleDatas() {
        final Article article = (Article) getIntent().getExtras().get(EXTRA_ARTICLE);
        mHeader.setImageResource(article.getHeader());
        mTitle.setText(article.getTitle());
        mDate.setText(article.getDate());
        mTitleContent.setBackgroundResource(article.getBackgroundColor());
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }

}
