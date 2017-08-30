package kenticocloud.kenticoclouddancinggoat.app.cafes;

/**
 * Created by RichardS on 15. 8. 2017.
 */

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.core.BaseActivity;
import kenticocloud.kenticoclouddancinggoat.injection.Injection;
import kenticocloud.kenticoclouddancinggoat.util.ActivityUtils;

public class CafesActivity extends BaseActivity{

    @Override
    protected int getLayoutResourceId() {
        return R.layout.main_layout;
    }

    @Override
    protected int getTitleResourceId() {
        return R.string.main_menu_cafes;
    }

    @Override
    protected int getMenuItemId() {
        return R.id.cafes;
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
        CafesFragment cafesFragment = (CafesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (cafesFragment == null) {
            // Create the fragment
            cafesFragment = CafesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), cafesFragment, R.id.contentFrame);
        }

        // create presenter
        new CafesPresenter(Injection.provideCafessRepository(getApplicationContext()), cafesFragment);
    }
}

