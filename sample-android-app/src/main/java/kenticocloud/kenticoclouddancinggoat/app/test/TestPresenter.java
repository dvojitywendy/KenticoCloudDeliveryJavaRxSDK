package kenticocloud.kenticoclouddancinggoat.app.test;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;
import com.kenticocloud.delivery.services.IDeliveryService;
import com.kenticocloud.delivery.models.item.DeliveryItemListingResponse;
import com.kenticocloud.delivery.models.type.DeliverySingleTypeResponse;
import com.kenticocloud.delivery.models.type.DeliveryTypeListingResponse;

import static com.google.common.base.Preconditions.checkNotNull;

class TestPresenter implements TestContract.Presenter {

    private final TestContract.View view;
    private final IDeliveryService deliveryService;

    TestPresenter(@NonNull IDeliveryService deliveryService, @NonNull TestContract.View view) {
        this.view = checkNotNull(view, "view cannot be null!");
        this.deliveryService = deliveryService;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        loadData();
    }

    @Override
    public void loadData() {
        this.view.setLoadingIndicator(true);

        this.deliveryService.type("coffee")
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeliverySingleTypeResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeliverySingleTypeResponse deliverySingleTypeResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        this.deliveryService.types()
                .limitParameter(5)
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeliveryTypeListingResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeliveryTypeListingResponse deliveryTypeListingResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

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
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
