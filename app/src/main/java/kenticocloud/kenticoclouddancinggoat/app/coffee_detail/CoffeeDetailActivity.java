package kenticocloud.kenticoclouddancinggoat.app.coffee_detail;

/**
 * Created by RichardS on 15. 8. 2017.
 */

import android.os.Bundle;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.BaseActivity;
import kenticocloud.kenticoclouddancinggoat.injection.Injection;
import kenticocloud.kenticoclouddancinggoat.util.ActivityUtils;

public class CoffeeDetailActivity extends BaseActivity{

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

        // get codename of the coffee from extra data
        String coffeeCodename = getIntent().getStringExtra("coffee_codename");

        // Set fragment
        CoffeeDetailFragment coffeeDetailFragment = (CoffeeDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (coffeeDetailFragment == null) {
            // Create the fragment
            coffeeDetailFragment = CoffeeDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), coffeeDetailFragment, R.id.contentFrame);
        }

        // create presenter
        new CoffeeDetailPresenter(Injection.provideCoffeesRepository(getApplicationContext()), coffeeDetailFragment, coffeeCodename);
    }
}

