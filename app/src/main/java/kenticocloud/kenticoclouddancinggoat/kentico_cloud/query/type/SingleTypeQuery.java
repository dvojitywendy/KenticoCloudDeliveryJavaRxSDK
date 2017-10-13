package kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.type;

import android.support.annotation.NonNull;
import android.util.Log;

import com.androidnetworking.common.Priority;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.SDKConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.exceptions.KenticoCloudException;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.type.DeliverySingleTypeResponse;

public class SingleTypeQuery extends BaseTypeQuery {

    private static final String URL_PATH = "/types/";
    private final String typeCodename;

    public SingleTypeQuery(@NonNull DeliveryClientConfig config, @NonNull String typeCodename) {
        super(config);
        this.typeCodename = typeCodename;
    }

    @Override
    public String getQueryUrl(){
        String action = URL_PATH + this.typeCodename;

        return this.queryService.getUrl(action, parameters);
    }

    // observable
    public Observable<DeliverySingleTypeResponse> get() {
        return Rx2AndroidNetworking.get(getQueryUrl())
                .setPriority(Priority.MEDIUM)
                .build()
                .getJSONObjectObservable()
                .map(new Function<JSONObject, DeliverySingleTypeResponse>() {
                    @Override
                    public DeliverySingleTypeResponse apply(JSONObject jsonObject) throws KenticoCloudException {
                        try {
                            return responseMapService.mapDeliverySingleTypeResponse(jsonObject);
                        } catch (IOException ex) {
                            Log.e(SDKConfig.APP_TAG, ex.getMessage());
                            throw new KenticoCloudException("Could not get type response with error: " + ex.getMessage(), ex);
                        }
                    }
                });
    }
}
