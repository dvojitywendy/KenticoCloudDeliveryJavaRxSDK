package kenticocloud.kenticoclouddancinggoat.app.articles;

/**
 * Created by RichardS on 15. 8. 2017.
 */
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.BaseActivity;
import kenticocloud.kenticoclouddancinggoat.injection.Injection;
import kenticocloud.kenticoclouddancinggoat.util.ActivityUtils;

public class ArticlesActivity extends BaseActivity{

    @Override
    protected int getLayoutResourceId() {
        return R.layout.main_layout;
    }

    @Override
    protected int getTitleResourceId() {
        return R.string.main_menu_articles;
    }

    @Override
    protected int getMenuItemId() {
        return R.id.articles;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set floating button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Check connection
        if (!_networkHelper.isNetworkAvailable(this.getApplicationContext())){
            showConnectionNotAvailable();
            return;
        }

        // Set fragment
        ArticlesFragment articlesFragment = (ArticlesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (articlesFragment == null) {
            // Create the fragment
            articlesFragment = ArticlesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), articlesFragment, R.id.contentFrame);
        }

        // create presenter
        new ArticlesPresenter(Injection.provideArticlesRepository(getApplicationContext()), articlesFragment);
    }
}

