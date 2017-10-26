package com.kentico.delivery.sample.androidapp.app.test;

import android.os.Bundle;

import com.kentico.delivery.sample.androidapp.app.core.BaseActivity;
import com.kentico.delivery.sample.androidapp.injection.Injection;
import com.kentico.delivery.core.services.IDeliveryService;
import com.kentico.delivery.sample.androidapp.util.ActivityUtils;
import com.kentico.delivery.sample.androidapp.R;

public class TestActivity extends BaseActivity {

    private IDeliveryService deliveryService = Injection.provideDeliveryService();

    @Override
    protected int getLayoutResourceId() {
        return R.layout.main_layout;
    }

    @Override
    protected int getTitleResourceId() {
        return R.string.main_menu_test;
    }

    @Override
    protected int getMenuItemId() {
        return R.id.test;
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
        TestFragment testFragment = (TestFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (testFragment == null) {
            // Create the fragment
            testFragment = TestFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), testFragment, R.id.contentFrame);
        }

        // create presenter
        new TestPresenter(Injection.provideDeliveryService(), testFragment);
    }
}

