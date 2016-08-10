package com.jaouan.articledetailstransition.popfromitem;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.jaouan.articledetailstransition.popfromitem.adapters.ArticleAdapter;
import com.jaouan.articledetailstransition.popfromitem.models.Article;
import com.jaouan.articledetailstransition.popfromitem.utils.ArticlesUtils;
import com.jaouan.articledetailstransition.popfromitem.utils.TransitionInformation;

import java.util.List;

/**
 * List activity that shows how to make a material transition between a list and a mDetail.
 */
public class ListActivity extends AppCompatActivity {

    /**
     * Current transition informations.
     */
    private final TransitionInformation mTransitionInformation = new TransitionInformation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initializeList(ArticlesUtils.mockArticles());
    }

    @Override
    protected void onResume() {
        super.onResume();
        reverseTransition();
    }

    /**
     * Initialize list with articles.
     *
     * @param articles Articles to display.
     */
    private void initializeList(final List<Article> articles) {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.articles);
        assert recyclerView != null;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArticleAdapter articleAdapter = new ArticleAdapter(articles);
        articleAdapter.setOnArticleClickedListener(new ArticleAdapter.OnArticleClickedListener() {
            @Override
            public void onArticleClicked(Article article, ArticleAdapter.ViewHolder articleViewHolder, PointF touchPoint) {
                navigateToDetails(article, articleViewHolder, touchPoint);
            }
        });
        recyclerView.setAdapter(articleAdapter);
    }

    /**
     * Navigate to details.
     *
     * @param article           Article.
     * @param articleViewHolder Article view holder.
     * @param touchPoint        Touch point.
     */
    private void navigateToDetails(final Article article, final ArticleAdapter.ViewHolder articleViewHolder, final PointF touchPoint) {
        synchronized (mTransitionInformation) {
            // - Disallow multiple transition at the same time.
            if (mTransitionInformation.getCoveringView() != null) {
                return;
            }

            // - Remember transition to reverse transition.
            mTransitionInformation.rememberTransition(articleViewHolder.ivCoveringImage, touchPoint);

            // - Reveal new covering image.
            articleViewHolder.ivCoveringImage.setVisibility(View.VISIBLE);
            final Animator circularReveal = ViewAnimationUtils.createCircularReveal(articleViewHolder.ivCoveringImage, (int) touchPoint.x, (int) touchPoint.y, 0, articleViewHolder.ivCoveringImage.getWidth());
            circularReveal.setInterpolator(new AccelerateInterpolator());
            circularReveal.setDuration(150);
            circularReveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    // - Start details activity.
                    final ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(ListActivity.this, articleViewHolder.ivCoveringImage, articleViewHolder.ivCoveringImage.getTransitionName());
                    final Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
                    intent.putExtra(DetailsActivity.EXTRA_ARTICLE, article);
                    startActivity(intent, options.toBundle());
                }
            });
            circularReveal.start();
        }
    }

    /**
     * Reverse transition from details.
     */
    private void reverseTransition() {
        synchronized (mTransitionInformation) {
            if (mTransitionInformation.getCoveringView() != null) {
                final Animator circularReveal = ViewAnimationUtils.createCircularReveal(mTransitionInformation.getCoveringView(), (int) mTransitionInformation.getTouchPoint().x, (int) mTransitionInformation.getTouchPoint().y, mTransitionInformation.getCoveringView().getWidth(), 0);
                circularReveal.setInterpolator(new DecelerateInterpolator());
                circularReveal.setDuration(150);
                circularReveal.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mTransitionInformation.getCoveringView().setVisibility(View.INVISIBLE);
                        mTransitionInformation.clear();
                    }
                });
                circularReveal.start();
            }
        }
    }


}
