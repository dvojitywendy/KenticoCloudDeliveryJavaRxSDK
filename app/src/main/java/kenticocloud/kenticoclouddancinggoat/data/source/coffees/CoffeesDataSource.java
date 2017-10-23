package kenticocloud.kenticoclouddancinggoat.data.source.coffees;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.data.models.Coffee;
import com.kenticocloud.delivery.data.IMultipleCallback;
import com.kenticocloud.delivery.data.ISingleCallback;

public interface CoffeesDataSource {

    interface LoadCoffeesCallback extends IMultipleCallback<Coffee> {
    }

    interface LoadCoffeeCallback extends ISingleCallback<Coffee> {
    }

    void getCoffees(@NonNull LoadCoffeesCallback callback);

    void getCoffee(@NonNull String codename, @NonNull LoadCoffeeCallback callback);
}
