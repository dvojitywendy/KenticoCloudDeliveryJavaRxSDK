package kenticocloud.kenticoclouddancinggoat.app.cafe_detail;

/**
 * Created by RichardS on 15. 8. 2017.
 */

import android.os.Bundle;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.BaseActivity;
import kenticocloud.kenticoclouddancinggoat.injection.Injection;
import kenticocloud.kenticoclouddancinggoat.util.ActivityUtils;

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
        if (!_networkHelper.isNetworkAvailable(this.getApplicationContext())){
            showConnectionNotAvailable();
            return;
        }

        // get codename of the cafe from extra data
        String cafeCodename = getIntent().getStringExtra("cafe_codename");

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

