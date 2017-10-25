package samplejava;

import com.kenticocloud.delivery.interfaces.item.item.IContentItem;
import com.kenticocloud.delivery.models.item.DeliveryItemListingResponse;
import com.kenticocloud.delivery.services.IDeliveryService;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Demo {

    private final IDeliveryService deliveryService;

    public Demo(IDeliveryService deliveryService){
        this.deliveryService = deliveryService;
    }

    public void runTests(){
        this.deliveryService.items().get().subscribe(new Observer<DeliveryItemListingResponse<IContentItem>>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(DeliveryItemListingResponse<IContentItem> iContentItemDeliveryItemListingResponse) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
