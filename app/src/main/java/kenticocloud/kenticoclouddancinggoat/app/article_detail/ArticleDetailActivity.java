package kenticocloud.kenticoclouddancinggoat.app.article_detail;

/**
 * Created by RichardS on 15. 8. 2017.
 */

import android.os.Bundle;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.core.BaseActivity;
import kenticocloud.kenticoclouddancinggoat.app.shared.CommunicationHub;
import kenticocloud.kenticoclouddancinggoat.injection.Injection;
import kenticocloud.kenticoclouddancinggoat.util.ActivityUtils;

public class ArticleDetailActivity extends BaseActivity{

    @Override
    protected int getLayoutResourceId() {
        return R.layout.main_layout;
    }

    @Override
    protected boolean useBackButton() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check connection
        if (!_networkHelper.isNetworkAvailable(this.getApplicationContext())){
            showConnectionNotAvailable();
            return;
        }

        // get codename of the article from extra data
        String articleCodename = getIntent().getStringExtra(CommunicationHub.ArticleCodename.toString());

        // Set fragment
        ArticleDetailFragment articleDetailFragment = (ArticleDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (articleDetailFragment == null) {
            // Create the fragment
            articleDetailFragment = ArticleDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), articleDetailFragment, R.id.contentFrame);
        }

        // create presenter
        new ArticleDetailPresenter(Injection.provideArticlesRepository(getApplicationContext()), articleDetailFragment, articleCodename);
    }
}

