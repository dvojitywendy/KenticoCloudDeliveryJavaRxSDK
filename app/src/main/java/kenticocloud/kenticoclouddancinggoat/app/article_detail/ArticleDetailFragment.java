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

import com.google.common.base.Function;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.core.BaseFragment;
import kenticocloud.kenticoclouddancinggoat.app.shared.ScrollChildSwipeRefreshLayout;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public class ArticleDetailFragment extends BaseFragment<ArticleDetailContract.Presenter> implements ArticleDetailContract.View {

    public ArticleDetailFragment() {
        // Requires empty public constructor
    }

    public static ArticleDetailFragment newInstance() {
        return new ArticleDetailFragment();
    }

    @Override
    protected int getFragmentId(){
        return R.layout.article_detail_frag;
    }

    @Override
    protected int getViewId(){
        return R.id.articleDetailLL;
    }

    @Override
    protected boolean hasScrollSwipeRefresh() {
        return true;
    }

    @Override
    protected void onScrollSwipeRefresh() {
        _presenter.loadArticle();
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

        _fragmentView.setVisibility(View.VISIBLE);

    }
}
