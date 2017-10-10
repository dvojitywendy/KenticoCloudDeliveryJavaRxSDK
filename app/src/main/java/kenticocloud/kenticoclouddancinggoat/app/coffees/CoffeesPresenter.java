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
