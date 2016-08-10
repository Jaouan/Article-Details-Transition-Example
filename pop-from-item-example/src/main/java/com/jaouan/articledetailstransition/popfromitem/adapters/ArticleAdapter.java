package com.jaouan.articledetailstransition.popfromitem.adapters;

import android.graphics.PointF;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaouan.articledetailstransition.popfromitem.R;
import com.jaouan.articledetailstransition.popfromitem.models.Article;

import java.util.List;

/**
 * List adapter that handles {@link Article}.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<Article> mArticles;

    private OnArticleClickedListener mOnArticleClickedListener;

    /**
     * ArticleAdapter's constructor.
     *
     * @param articles Articles to display.
     */
    public ArticleAdapter(final List<Article> articles) {
        this.mArticles = articles;
    }

    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Article item = mArticles.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvDate.setText(item.getDate());
        holder.ivThumbnail.setImageResource(item.getHeader());
        holder.ivCoveringImage.setImageResource(item.getHeader());
        holder.rootView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mOnArticleClickedListener != null) {
                        mOnArticleClickedListener.onArticleClicked(item, holder, new PointF(event.getX(), event.getY()));
                    }
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    /**
     * Sets on article clicked listener.
     *
     * @param onArticleClickedListener Article clicked listener.
     */
    public void setOnArticleClickedListener(final OnArticleClickedListener onArticleClickedListener) {
        this.mOnArticleClickedListener = onArticleClickedListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View rootView;
        public final TextView tvTitle;
        public final TextView tvDate;
        public final ImageView ivThumbnail;
        public final ImageView ivCoveringImage;

        public ViewHolder(final View view) {
            super(view);
            this.rootView = view;
            this.tvTitle = (TextView) view.findViewById(R.id.title);
            this.tvDate = (TextView) view.findViewById(R.id.date);
            this.ivThumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            this.ivCoveringImage = (ImageView) view.findViewById(R.id.covering_image);
        }
    }

    /**
     * On item clicked callback.
     */
    public interface OnArticleClickedListener {

        void onArticleClicked(Article article, ViewHolder articleViewHolder, PointF touchPoint);

    }

}