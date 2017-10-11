package kenticocloud.kenticoclouddancinggoat.data.source.coffees;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kenticocloud.kenticoclouddancinggoat.data.models.Coffee;
import kenticocloud.kenticoclouddancinggoat.data.source.BaseCloudSource;
import kenticocloud.kenticoclouddancinggoat.injection.Injection;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemListingResponse;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemResponse;

public class CoffeesCloudSource extends BaseCloudSource implements CoffeesDataSource {

    private static CoffeesCloudSource INSTANCE;

    // Prevent direct instantiation.
    private CoffeesCloudSource(@NonNull Context context) {
        super(Injection.provideDeliveryService());
    }

    public static CoffeesCloudSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CoffeesCloudSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getCoffees(@NonNull final LoadCoffeesCallback callback) {
        this.deliveryService.<Coffee>items()
                .type(Coffee.TYPE)
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeliveryItemListingResponse<Coffee>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeliveryItemListingResponse<Coffee> response) {
                        List<Coffee> items = (response.getItems());

                        if (items == null || items.size() == 0){
                            callback.onDataNotAvailable();
                            return;
                        }

                        callback.onItemsLoaded(items);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getCoffee(@NonNull String codename, @NonNull final LoadCoffeeCallback callback) {
        this.deliveryService.<Coffee>item(codename)
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeliveryItemResponse<Coffee>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeliveryItemResponse<Coffee> response) {
                        if (response.getItem() == null){
                            callback.onDataNotAvailable();
                        }

                        callback.onItemLoaded(response.getItem());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
