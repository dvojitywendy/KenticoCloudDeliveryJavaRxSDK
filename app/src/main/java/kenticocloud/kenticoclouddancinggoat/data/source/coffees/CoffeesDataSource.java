package kenticocloud.kenticoclouddancinggoat.data.source.coffees;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Coffee;
import kenticocloud.kenticoclouddancinggoat.data.source.IMultipleCallback;
import kenticocloud.kenticoclouddancinggoat.data.source.ISingleCallback;

public interface CoffeesDataSource {

    interface LoadCoffeesCallback extends IMultipleCallback<Coffee> {
    }

    interface LoadCoffeeCallback extends ISingleCallback<Coffee> {
    }

    void getCoffees(@NonNull LoadCoffeesCallback callback);

    void getCoffee(@NonNull String codename, @NonNull LoadCoffeeCallback callback);
}
