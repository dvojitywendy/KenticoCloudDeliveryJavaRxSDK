package samplejava;

import com.kenticocloud.delivery.config.DeliveryClientConfig;
import com.kenticocloud.delivery.services.IDeliveryService;
import com.kenticocloud.rxjava2.DeliveryRxJava2;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class Main {
    public static void main( final String[] args ){
        System.out.println("Sample rxjava2 app start");

        Flowable.just("Hello world")
                .subscribe(new Consumer<String>() {
                    @Override public void accept(String s) {
                        System.out.println(s);
                    }
                });

        //Demo demo = new Demo(getDeliveryService());
        //demo.runTests();
    }

    private static IDeliveryService getDeliveryService(){
        return DeliveryRxJava2.getInstance(new DeliveryClientConfig(AppConfig.KENTICO_CLOUD_PROJECT_ID, AppConfig.getTypeResolvers()));
    }
}
