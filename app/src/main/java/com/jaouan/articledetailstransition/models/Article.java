package com.jaouan.articledetailstransition.models;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import java.io.Serializable;

/**
 * Article structure.
 */
public class Article implements Serializable {

    private String title;

    private String date;

    private int header;

    private int backgroundColor;

    /**
     * Article's constructor.
     *
     * @param title           Title.
     * @param date            Date.
     * @param header          Header image.
     * @param backgroundColor Background color;
     */
    public Article(final String title, final String date, @DrawableRes int header, @ColorRes int backgroundColor) {
        setTitle(title);
        setDate(date);
        setHeader(header);
        setBackgroundColor(backgroundColor);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHeader() {
        return header;
    }

    public void setHeader(int header) {
        this.header = header;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
