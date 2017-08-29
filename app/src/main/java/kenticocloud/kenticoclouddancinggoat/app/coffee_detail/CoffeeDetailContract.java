package kenticocloud.kenticoclouddancinggoat.app.coffee_detail;

import kenticocloud.kenticoclouddancinggoat.BasePresenter;
import kenticocloud.kenticoclouddancinggoat.BaseView;
import kenticocloud.kenticoclouddancinggoat.data.models.Coffee;

/**
 * Created by RichardS on 15. 8. 2017.
 */

interface CoffeeDetailContract {
    interface View extends BaseView<Presenter>{
        void showCoffee(Coffee coffee);
    }

    interface Presenter extends BasePresenter{
        void loadCoffee();
    }
}
