package kenticocloud.kenticoclouddancinggoat.data.source.cafes;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.androidnetworking.error.ANError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;
import kenticocloud.kenticoclouddancinggoat.injection.Injection;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.IDeliveryService;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemListingResponse;
import okhttp3.Call;
import okhttp3.ResponseBody;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public class CafesCloudSource implements CafesDataSource {

    private static CafesCloudSource INSTANCE;

    private IDeliveryService _deliveryService;

    // Prevent direct instantiation.
    private CafesCloudSource(@NonNull Context context) {
        checkNotNull(context);
        _deliveryService = Injection.provideDeliveryService();
    }

    public static CafesCloudSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CafesCloudSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getCafes(@NonNull final LoadCafesCallback callback) {
        _deliveryService.items()
                .type("cafe")
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeliveryItemListingResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DeliveryItemListingResponse response) {
                        List<Cafe> cafes = new ArrayList<Cafe>();
                        List<IContentItem> items = response.getItems();
                        for(int i = 0; i < items.size(); i++){
                            IContentItem item = items.get(i);
                            Cafe cafe = new Cafe(item.GetStringValue("city"));
                            cafes.add(cafe);
                        }

                        callback.onCafesLoaded(cafes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataNotAvailable();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getCafe(@NonNull String articleId, @NonNull GetCafeCallback callback) {
        Cafe cafe = new Cafe("Single cafe 1");

        callback.onCafeLoaded(cafe);
    }
}
