package com.jaouan.articledetailstransition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jaouan.articledetailstransition.models.Article;
import com.jaouan.articledetailstransition.views.adapters.ArticleAdapter;

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

        initializeList(mockArticles());
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
     * @param articleView Article articleView.
     */
    private void navigateToDetails(final Article article, final View articleView) {
        final ActivityOptions options =
                ActivityOptions.makeSceneTransitionAnimation(this, articleView, articleView.getTransitionName());
        final Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_ARTICLE, article);
        startActivity(intent, options.toBundle());

    }

    /**
     * Mock articles.
     *
     * @return Articles.
     */
    private List<Article> mockArticles() {
        final List articles = new ArrayList<>(100);
        for (int i = 0; i < 1; i++) {
            articles.add(new Article("Lorem ipsum " + articles.size(), "Today", R.drawable.header1, R.color.darker_darker_gray));
        }
        for (int i = 0; i < 2; i++) {
            articles.add(new Article("Lorem ipsum " + articles.size(), "Yesterday", R.drawable.header2, R.color.darker_darker_blue));
        }
        for (int i = 0; i < 10; i++) {
            articles.add(new Article("Lorem ipsum " + articles.size(), "19 August", R.drawable.header1, R.color.darker_darker_gray));
        }
        for (int i = 0; i < 8; i++) {
            articles.add(new Article("Lorem ipsum " + articles.size(), "18 August", R.drawable.header1, R.color.darker_darker_gray));
        }
        return articles;
    }

}
