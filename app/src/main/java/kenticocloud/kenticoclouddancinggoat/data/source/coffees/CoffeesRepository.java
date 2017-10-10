package kenticocloud.kenticoclouddancinggoat.data.source.coffees;

import android.support.annotation.NonNull;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Coffee;

import static com.google.common.base.Preconditions.checkNotNull;

public class CoffeesRepository implements CoffeesDataSource {

    private static CoffeesRepository INSTANCE = null;

    private final CoffeesDataSource dataSource;

    // Prevent direct instantiation.
    private CoffeesRepository(@NonNull CoffeesDataSource dataSource){
        this.dataSource = checkNotNull(dataSource);
    }

    @Override
    public void getCoffees(@NonNull final LoadCoffeesCallback callback) {
        this.dataSource.getCoffees(new LoadCoffeesCallback() {
            @Override
            public void onItemsLoaded(List<Coffee> coffees) {
                callback.onItemsLoaded(coffees);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e);
            }
        });
    }

    @Override
    public void getCoffee(@NonNull String codename, @NonNull final LoadCoffeeCallback callback) {
        this.dataSource.getCoffee(codename, new LoadCoffeeCallback() {
            @Override
            public void onItemLoaded(Coffee coffee) {
                callback.onItemLoaded(coffee);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e);
            }
        });
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param dataSource the backend data source
     * @return the {@link CoffeesRepository} instance
     */
    public static CoffeesRepository getInstance(CoffeesDataSource dataSource) {
        if (INSTANCE == null) {
            INSTANCE = new CoffeesRepository(dataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
