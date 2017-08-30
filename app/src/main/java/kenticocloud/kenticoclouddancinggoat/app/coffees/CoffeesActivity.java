package kenticocloud.kenticoclouddancinggoat.app.coffees;

/**
 * Created by RichardS on 15. 8. 2017.
 */
import android.os.Bundle;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.core.BaseActivity;
import kenticocloud.kenticoclouddancinggoat.injection.Injection;
import kenticocloud.kenticoclouddancinggoat.util.ActivityUtils;

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
        if (!_networkHelper.isNetworkAvailable(this.getApplicationContext())){
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

