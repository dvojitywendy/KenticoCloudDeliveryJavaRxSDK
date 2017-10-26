package com.kentico.delivery.sample.androidapp.app.article_detail;

import android.os.Bundle;

import com.kentico.delivery.sample.androidapp.R;
import com.kentico.delivery.sample.androidapp.app.core.BaseActivity;
import com.kentico.delivery.sample.androidapp.app.shared.CommunicationHub;
import com.kentico.delivery.sample.androidapp.injection.Injection;
import com.kentico.delivery.sample.androidapp.util.ActivityUtils;

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
        if (!this.networkHelper.isNetworkAvailable(this.getApplicationContext())){
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

