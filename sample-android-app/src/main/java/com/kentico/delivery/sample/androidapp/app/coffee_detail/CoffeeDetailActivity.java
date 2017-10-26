package com.kentico.delivery.sample.androidapp.app.coffee_detail;

import android.os.Bundle;

import com.kentico.delivery.sample.androidapp.R;
import com.kentico.delivery.sample.androidapp.app.core.BaseActivity;
import com.kentico.delivery.sample.androidapp.app.shared.CommunicationHub;
import com.kentico.delivery.sample.androidapp.injection.Injection;
import com.kentico.delivery.sample.androidapp.util.ActivityUtils;

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
        if (!this.networkHelper.isNetworkAvailable(this.getApplicationContext())){
            showConnectionNotAvailable();
            return;
        }

        // get codename of the coffee from extra data
        String coffeeCodename = getIntent().getStringExtra(CommunicationHub.CoffeeCodename.toString());

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

