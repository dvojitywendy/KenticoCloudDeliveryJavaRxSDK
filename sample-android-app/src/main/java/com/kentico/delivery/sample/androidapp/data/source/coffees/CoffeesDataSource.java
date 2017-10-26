package com.kentico.delivery.sample.androidapp.data.source.coffees;

import android.support.annotation.NonNull;

import com.kentico.delivery.sample.androidapp.data.models.Coffee;
import com.kentico.delivery.core.data.IMultipleCallback;
import com.kentico.delivery.core.data.ISingleCallback;

public interface CoffeesDataSource {

    interface LoadCoffeesCallback extends IMultipleCallback<Coffee> {
    }

    interface LoadCoffeeCallback extends ISingleCallback<Coffee> {
    }

    void getCoffees(@NonNull LoadCoffeesCallback callback);

    void getCoffee(@NonNull String codename, @NonNull LoadCoffeeCallback callback);
}
