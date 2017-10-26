package com.kentico.delivery.sample.androidapp.app.coffee_detail;

import android.support.annotation.NonNull;

import com.kentico.delivery.sample.androidapp.data.models.Coffee;
import com.kentico.delivery.sample.androidapp.data.source.coffees.CoffeesDataSource;
import com.kentico.delivery.sample.androidapp.data.source.coffees.CoffeesRepository;

import static com.google.common.base.Preconditions.checkNotNull;


class CoffeeDetailPresenter implements CoffeeDetailContract.Presenter {

    private final String coffeeCodename;
    private final CoffeesRepository repository;

    private final CoffeeDetailContract.View view;

    CoffeeDetailPresenter(@NonNull CoffeesRepository repository, @NonNull CoffeeDetailContract.View view, @NonNull String coffeeCodename) {
        this.repository = checkNotNull(repository, "repository cannot be null");
        this.view = checkNotNull(view, "view cannot be null!");
        this.view.setPresenter(this);
        this.coffeeCodename = coffeeCodename;
    }

    @Override
    public void start() {
        loadCoffee();
    }

    @Override
    public void loadCoffee() {
        this.view.setLoadingIndicator(true);

        this.repository.getCoffee(this.coffeeCodename, new CoffeesDataSource.LoadCoffeeCallback() {

            @Override
            public void onItemLoaded(Coffee item) {
                view.showCoffee(item);
            }

            @Override
            public void onDataNotAvailable() {
                view.showLoadingError();
            }

            @Override
            public void onError(Throwable e) {
                view.showLoadingError();
            }
        });
    }
}
