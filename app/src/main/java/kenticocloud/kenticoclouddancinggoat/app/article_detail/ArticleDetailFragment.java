package kenticocloud.kenticoclouddancinggoat.app.article_detail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.shared.ScrollChildSwipeRefreshLayout;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public class ArticleDetailFragment extends Fragment implements ArticleDetailContract.View {

    private ArticleDetailContract.Presenter _presenter;
    private String articleCodename;

    private LinearLayout _articleDetailView;

    public ArticleDetailFragment() {
        // Requires empty public constructor
    }

    public static ArticleDetailFragment newInstance() {
        return new ArticleDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showNoData(boolean show){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.article_detail_frag, container, false);

        // Set article LL
        _articleDetailView = (LinearLayout) root.findViewById(R.id.articleDetailLL);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(_articleDetailView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                _presenter.loadArticle();
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
    public void setPresenter(ArticleDetailContract.Presenter presenter) {
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void showArticle(Article article) {
        View view = getView();

        if (view == null){
            return;
        }

        // Update activity title
        getActivity().setTitle(article.getTitle());

        // Set up article detail view
        TextView articleTitleTV = (TextView) view.findViewById(R.id.articleDetailTitleTV);
        articleTitleTV.setText(article.getTitle());
        setLoadingIndicator(false);

        // image
        final ImageView teaserIV = (ImageView) view.findViewById(R.id.articleDetailTeaserIV);
        Picasso.with(view.getContext()).load(article.getTeaserImageUrl()).into(teaserIV);

        // release date
        TextView postDateTV = (TextView) view.findViewById(R.id.articleDetailPostDateTV);
        SimpleDateFormat postDf = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        postDateTV.setText(postDf.format(article.getPostDate()));

        // text
        TextView bodyCopyTV = (TextView) view.findViewById(R.id.articleDetailBodyCopyTV);
        bodyCopyTV.setText(Html.fromHtml(article.getBodyCopy(), Html.FROM_HTML_MODE_COMPACT));

        _articleDetailView.setVisibility(View.VISIBLE);

    }

    @Override
    public void showLoadingError() {
        showMessage(getString(R.string.error_loading_data));
        setLoadingIndicator(false);
    }

    private void showMessage(String message) {
        View view = getView();

        if (view == null){
            return;
        }

        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }



}
