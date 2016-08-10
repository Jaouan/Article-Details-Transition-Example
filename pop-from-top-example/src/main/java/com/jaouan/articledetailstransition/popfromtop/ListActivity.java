package com.jaouan.articledetailstransition.popfromtop;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jaouan.articledetailstransition.popfromtop.models.Article;
import com.jaouan.articledetailstransition.popfromtop.adapters.ArticleAdapter;
import com.jaouan.articledetailstransition.popfromtop.utils.ArticlesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * List activity that shows how to make a material transition between a list and a mDetail.
 */
public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initializeList(ArticlesUtils.mockArticles());
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
            public void onArticleClicked(Article article, View articleView) {
                navigateToDetails(article, articleView);
            }
        });
        recyclerView.setAdapter(articleAdapter);
    }

    /**
     * Navigate to details.
     *
     * @param article     Article.
     * @param articleView Article view.
     */
    private void navigateToDetails(final Article article, final View articleView) {
        final ActivityOptions options =
                ActivityOptions.makeSceneTransitionAnimation(this, articleView, articleView.getTransitionName());
        final Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_ARTICLE, article);
        startActivity(intent, options.toBundle());

    }

}
