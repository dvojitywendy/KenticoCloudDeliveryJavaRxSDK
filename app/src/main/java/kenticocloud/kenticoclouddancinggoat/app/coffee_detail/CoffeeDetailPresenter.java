package kenticocloud.kenticoclouddancinggoat.app.coffee_detail;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;
import kenticocloud.kenticoclouddancinggoat.data.models.Coffee;
import kenticocloud.kenticoclouddancinggoat.data.source.cafes.CafesDataSource;
import kenticocloud.kenticoclouddancinggoat.data.source.coffees.CoffeesDataSource;
import kenticocloud.kenticoclouddancinggoat.data.source.coffees.CoffeesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

class CoffeeDetailPresenter implements CoffeeDetailContract.Presenter {

    private final String _coffeeCodename;
    private final CoffeesRepository _repository;

    private final CoffeeDetailContract.View _view;

    CoffeeDetailPresenter(@NonNull CoffeesRepository repository, @NonNull CoffeeDetailContract.View view, @NonNull String coffeeCodename) {
        _repository = checkNotNull(repository, "repository cannot be null");
        _view = checkNotNull(view, "view cannot be null!");
        _view.setPresenter(this);
        _coffeeCodename = coffeeCodename;
    }

    @Override
    public void start() {
        loadCoffee();
    }

    @Override
    public void loadCoffee() {
        _view.setLoadingIndicator(true);

        _repository.getCoffee(_coffeeCodename, new CoffeesDataSource.LoadCoffeeCallback() {

            @Override
            public void onItemLoaded(Coffee item) {
                _view.showCoffee(item);
            }

            @Override
            public void onDataNotAvailable() {
                _view.showLoadingError();
            }

            @Override
            public void onError(Throwable e) {
                _view.showLoadingError();
            }
        });
    }
}
