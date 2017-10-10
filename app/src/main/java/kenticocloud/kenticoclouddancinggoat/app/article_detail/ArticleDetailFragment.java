package kenticocloud.kenticoclouddancinggoat.app.article_detail;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.core.BaseFragment;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;

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
        this.presenter.loadArticle();
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

        this.fragmentView.setVisibility(View.VISIBLE);

    }
}
