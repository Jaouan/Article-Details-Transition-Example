package com.jaouan.articledetailstransition.popfromitem.utils;

import com.jaouan.articledetailstransition.popfromitem.R;
import com.jaouan.articledetailstransition.popfromitem.models.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Utils for articles.
 */
public final class ArticlesUtils {

    /**
     * Disallow instantiation.
     */
    private ArticlesUtils() {
    }

    /**
     * Mock articles.
     *
     * @return Articles.
     */
    public static List<Article> mockArticles() {
        final List<Article> articles = new ArrayList<>(100);
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
