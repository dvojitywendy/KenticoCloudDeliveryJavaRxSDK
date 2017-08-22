package kenticocloud.kenticoclouddancinggoat.app.articles;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.shared.ScrollChildSwipeRefreshLayout;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public class ArticlesFragment extends Fragment implements ArticlesContract.View{

    private ArticlesContract.Presenter _presenter;

    private ArticlesAdapter _adapter;

    private View _noArticlesView;
    private LinearLayout _articlesView;

    public ArticlesFragment() {
        // Requires empty public constructor
    }

    public static ArticlesFragment newInstance() {
        return new ArticlesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _adapter = new ArticlesAdapter(new ArrayList<Article>(0), articleItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.articles_frag, container, false);

        // Set up tasks view
        ListView listView = (ListView) root.findViewById(R.id.articlesLV);
        listView.setAdapter(_adapter);
        _articlesView = (LinearLayout) root.findViewById(R.id.articlesLL);

        // Set up  no tasks view
        _noArticlesView = root.findViewById(R.id.noArticlesTV);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(listView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                _presenter.loadArticles();
            }
        });

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        _presenter.start();
    }

    @Override
    public void setPresenter(ArticlesContract.Presenter presenter) {
        _presenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }

        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showArticles(List<Article> articles) {
        _adapter.replaceData(articles);

        _articlesView.setVisibility(View.VISIBLE);
        _noArticlesView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingTasksError() {
        showMessage("error loading data");
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Listener for clicks on tasks in the ListView.
     */
    ArticleItemListener articleItemListener = new ArticleItemListener() {
        @Override
        public void onArticleClick(Article clickedTask) {
            // to do some action when item is clicked
        }
    };

    private static class ArticlesAdapter extends BaseAdapter {

        private List<Article> _articles;
        private ArticleItemListener _articleItemListener;

        public ArticlesAdapter(List<Article> articles, ArticleItemListener itemListener) {
            setList(articles);
            _articleItemListener = itemListener;
        }

        public void replaceData(List<Article> articles) {
            setList(articles);
            notifyDataSetChanged();
        }

        private void setList(List<Article> articles) {
            _articles = checkNotNull(articles);
        }

        @Override
        public int getCount() {
            return _articles.size();
        }

        @Override
        public Article getItem(int i) {
            return _articles.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = view;
            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                rowView = inflater.inflate(R.layout.article_item, viewGroup, false);
            }

            final Article article = getItem(i);

            // title
            TextView titleTV = (TextView) rowView.findViewById(R.id.articleTitleTV);
            titleTV.setText(article.getTitle());

            // image
            final ImageView teaserIV = (ImageView) rowView.findViewById(R.id.articleTeaserIV);
            Picasso.with(viewGroup.getContext()).load(article.getTeaserImageUrl()).into(teaserIV);

            // release date
            TextView postDateTV = (TextView) rowView.findViewById(R.id.articlePostDateTV);
            SimpleDateFormat postDf = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
            postDateTV.setText(postDf.format(article.getPostDate()));

            // summary
            TextView summaryTV = (TextView) rowView.findViewById(R.id.articleSummaryTV);
            summaryTV.setText(article.getSummary());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _articleItemListener.onArticleClick(article);
                }
            });

            return rowView;
        }

        private class DownloadImagesTask extends AsyncTask<String, Void, Drawable> {

            ImageView _imageView;

            public DownloadImagesTask(ImageView imageView){
                _imageView = imageView;
            }

            @Override
            protected Drawable doInBackground(String... urls) {
                Drawable image = LoadImageFromWebOperations(urls[0]);

                return image;
            }

            @Override
            protected void onPostExecute(Drawable drawable) {
                _imageView.setImageDrawable(drawable);
            }
        }
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "teaser");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    interface ArticleItemListener {

        void onArticleClick(Article clickedArticle);
    }


}
