package com.kentico.delivery.sample.androidapp.app.coffees;

import android.support.annotation.NonNull;

import java.util.List;

import com.kentico.delivery.sample.androidapp.data.models.Article;
import com.kentico.delivery.sample.androidapp.data.models.Coffee;
import com.kentico.delivery.sample.androidapp.data.source.articles.ArticlesDataSource;
import com.kentico.delivery.sample.androidapp.data.source.articles.ArticlesRepository;
import com.kentico.delivery.sample.androidapp.data.source.coffees.CoffeesDataSource;
import com.kentico.delivery.sample.androidapp.data.source.coffees.CoffeesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

class CoffeesPresenter implements CoffeesContract.Presenter {

    private final CoffeesRepository repository;

    private final CoffeesContract.View view;

    CoffeesPresenter(@NonNull CoffeesRepository repository, @NonNull CoffeesContract.View view) {
        this.repository = checkNotNull(repository, "repository cannot be null");
        this.view = checkNotNull(view, "view cannot be null!");

        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        loadCoffees();
    }

    @Override
    public void loadCoffees() {
        this.view.setLoadingIndicator(true);

        this.repository.getCoffees(new CoffeesDataSource.LoadCoffeesCallback() {
            @Override
            public void onItemsLoaded(List<Coffee> coffees) {
                view.setLoadingIndicator(false);
                view.showCoffees(coffees);
            }

            @Override
            public void onDataNotAvailable() {
                view.showNoData(true);
            }

            @Override
            public void onError(Throwable e) {
                view.showLoadingError();
            }
        });
    }
}
