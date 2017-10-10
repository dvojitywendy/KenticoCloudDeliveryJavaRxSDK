package kenticocloud.kenticoclouddancinggoat.app.coffee_detail;

import kenticocloud.kenticoclouddancinggoat.app.core.IBasePresenter;
import kenticocloud.kenticoclouddancinggoat.app.core.IBaseView;
import kenticocloud.kenticoclouddancinggoat.data.models.Coffee;

interface CoffeeDetailContract {
    interface View extends IBaseView<Presenter> {
        void showCoffee(Coffee coffee);
    }

    interface Presenter extends IBasePresenter {
        void loadCoffee();
    }
}
