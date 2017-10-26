package com.kentico.delivery.sample.androidapp.data.source.cafes;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.kentico.delivery.sample.androidapp.data.models.Cafe;
import com.kentico.delivery.core.data.BaseCloudSource;
import com.kentico.delivery.sample.androidapp.injection.Injection;
import com.kentico.delivery.core.models.item.DeliveryItemListingResponse;
import com.kentico.delivery.core.models.item.DeliveryItemResponse;

import static com.google.common.base.Preconditions.checkNotNull;

public class CafesCloudSource extends BaseCloudSource implements CafesDataSource {

    private static CafesCloudSource INSTANCE;

    // Prevent direct instantiation.
    private CafesCloudSource(@NonNull Context context) {
        super(Injection.provideDeliveryService());
    }

    public static CafesCloudSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CafesCloudSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getCafes(@NonNull final LoadCafesCallback callback) {
        this.deliveryService.<Cafe>items()
                .type(Cafe.TYPE)
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeliveryItemListingResponse<Cafe>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DeliveryItemListingResponse<Cafe> response) {
                        List<Cafe> items = (response.getItems());

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
    public void getCafe(@NonNull String codename, @NonNull final LoadCafeCallback callback) {
        this.deliveryService.<Cafe>item(codename)
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeliveryItemResponse<Cafe>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DeliveryItemResponse<Cafe> response) {
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
