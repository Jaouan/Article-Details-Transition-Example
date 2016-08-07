package com.jaouan.articledetailstransition.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaouan.articledetailstransition.R;
import com.jaouan.articledetailstransition.models.Article;

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
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (mOnArticleClickedListener != null) {
                    mOnArticleClickedListener.onArticleClicked(item, view);
                }
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

    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tvTitle;
        public TextView tvDate;

        public ViewHolder(final View view) {
            super(view);
            this.rootView = view;
            this.tvTitle = (TextView) view.findViewById(R.id.title);
            this.tvDate = (TextView) view.findViewById(R.id.date);
        }
    }

    /**
     * On item clicked callback.
     */
    public interface OnArticleClickedListener {

        void onArticleClicked(Article article, View articleView);

    }

}