package com.kentico.delivery.sample.androidapp.app.coffee_detail;

import com.kentico.delivery.sample.androidapp.app.core.IBasePresenter;
import com.kentico.delivery.sample.androidapp.app.core.IBaseView;
import com.kentico.delivery.sample.androidapp.data.models.Coffee;

interface CoffeeDetailContract {
    interface View extends IBaseView<Presenter> {
        void showCoffee(Coffee coffee);
    }

    interface Presenter extends IBasePresenter {
        void loadCoffee();
    }
}
