package com.kentico.delivery.sample.androidapp.app.cafe_detail;

import android.os.Bundle;

import com.kentico.delivery.sample.androidapp.R;
import com.kentico.delivery.sample.androidapp.app.core.BaseActivity;
import com.kentico.delivery.sample.androidapp.app.shared.CommunicationHub;
import com.kentico.delivery.sample.androidapp.injection.Injection;
import com.kentico.delivery.sample.androidapp.util.ActivityUtils;

public class CafeDetailActivity extends BaseActivity{

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

        // get codename of the cafe from extra data
        String cafeCodename = getIntent().getStringExtra(CommunicationHub.CafeCodename.toString());

        // Set fragment
        CafeDetailFragment cafeDetailFragment = (CafeDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (cafeDetailFragment == null) {
            // Create the fragment
            cafeDetailFragment = CafeDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), cafeDetailFragment, R.id.contentFrame);
        }

        // create presenter
        new CafeDetailPresenter(Injection.provideCafessRepository(getApplicationContext()), cafeDetailFragment, cafeCodename);
    }
}

