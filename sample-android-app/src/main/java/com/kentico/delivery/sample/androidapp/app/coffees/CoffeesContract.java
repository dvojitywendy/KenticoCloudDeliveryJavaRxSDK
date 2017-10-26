package com.kentico.delivery.sample.androidapp.app.coffees;

import java.util.List;

import com.kentico.delivery.sample.androidapp.app.core.IBasePresenter;
import com.kentico.delivery.sample.androidapp.app.core.IBaseView;
import com.kentico.delivery.sample.androidapp.data.models.Coffee;


interface CoffeesContract {
    interface View extends IBaseView<Presenter> {
        void showCoffees(List<Coffee> coffees);
    }

    interface Presenter extends IBasePresenter {
        void loadCoffees();
    }
}
