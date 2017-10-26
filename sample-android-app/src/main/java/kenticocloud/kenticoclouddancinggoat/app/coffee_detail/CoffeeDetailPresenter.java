package kenticocloud.kenticoclouddancinggoat.app.coffee_detail;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.data.models.Coffee;
import kenticocloud.kenticoclouddancinggoat.data.source.coffees.CoffeesDataSource;
import kenticocloud.kenticoclouddancinggoat.data.source.coffees.CoffeesRepository;

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
