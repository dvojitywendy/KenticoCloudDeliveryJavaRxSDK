package kenticocloud.kenticoclouddancinggoat.app.coffees;

import android.support.annotation.NonNull;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Coffee;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesDataSource;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesRepository;
import kenticocloud.kenticoclouddancinggoat.data.source.coffees.CoffeesDataSource;
import kenticocloud.kenticoclouddancinggoat.data.source.coffees.CoffeesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

class CoffeesPresenter implements CoffeesContract.Presenter {

    private final CoffeesRepository _repository;

    private final CoffeesContract.View _view;

    CoffeesPresenter(@NonNull CoffeesRepository repository, @NonNull CoffeesContract.View view) {
        _repository = checkNotNull(repository, "repository cannot be null");
        _view = checkNotNull(view, "view cannot be null!");

        _view.setPresenter(this);
    }

    @Override
    public void start() {
        loadCoffees();
    }

    @Override
    public void loadCoffees() {
        _view.setLoadingIndicator(true);

        _repository.getCoffees(new CoffeesDataSource.LoadCoffeesCallback() {
            @Override
            public void onItemsLoaded(List<Coffee> coffees) {
                _view.setLoadingIndicator(false);
                _view.showCoffees(coffees);
            }

            @Override
            public void onDataNotAvailable() {
                _view.showNoData(true);
            }

            @Override
            public void onError(Throwable e) {
                _view.showLoadingError();
            }
        });
    }
}
