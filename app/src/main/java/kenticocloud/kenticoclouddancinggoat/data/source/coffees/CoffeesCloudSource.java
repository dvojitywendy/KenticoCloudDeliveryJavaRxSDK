package kenticocloud.kenticoclouddancinggoat.data.source.coffees;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Coffee;
import kenticocloud.kenticoclouddancinggoat.data.source.BaseCloudSource;
import kenticocloud.kenticoclouddancinggoat.injection.Injection;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemListingResponse;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemResponse;

/**
 * Created by RichardS on 15. 8. 2017.
 */

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
        _deliveryService.items()
                .type(Coffee.TYPE)
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeliveryItemListingResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeliveryItemListingResponse response) {
                        List<IContentItem> items = (response.getItems());
                        List<Coffee> coffees = new ArrayList<Coffee>();

                        if (items == null || items.size() == 0){
                            callback.onDataNotAvailable();
                            return;
                        }

                        for(int i = 0; i < items.size(); i++){
                            Coffee coffee = (Coffee)items.get(i);
                            // add to strongly typed list (this should be somehow solved with generics)
                            coffees.add(coffee);
                        }

                        callback.onItemsLoaded(coffees);
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
        _deliveryService.item(codename)
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeliveryItemResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeliveryItemResponse response) {
                        if (response.getItem() == null){
                            callback.onDataNotAvailable();
                        }

                        callback.onItemLoaded((Coffee)response.getItem());
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
