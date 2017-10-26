package com.kentico.delivery.sample.androidapp.app.coffees;

import android.os.Bundle;

import com.kentico.delivery.sample.androidapp.R;
import com.kentico.delivery.sample.androidapp.app.core.BaseActivity;
import com.kentico.delivery.sample.androidapp.injection.Injection;
import com.kentico.delivery.sample.androidapp.util.ActivityUtils;

public class CoffeesActivity extends BaseActivity{

    @Override
    protected int getLayoutResourceId() {
        return R.layout.main_layout;
    }

    @Override
    protected int getTitleResourceId() {
        return R.string.main_menu_coffees;
    }

    @Override
    protected int getMenuItemId() {
        return R.id.coffees;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check connection
        if (!this.networkHelper.isNetworkAvailable(this.getApplicationContext())){
            showConnectionNotAvailable();
            return;
        }

        // Set fragment
        CoffeesFragment coffeesFragment = (CoffeesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (coffeesFragment == null) {
            // Create the fragment
            coffeesFragment = CoffeesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), coffeesFragment, R.id.contentFrame);
        }

        // create presenter
        new CoffeesPresenter(Injection.provideCoffeesRepository(getApplicationContext()), coffeesFragment);
    }
}

