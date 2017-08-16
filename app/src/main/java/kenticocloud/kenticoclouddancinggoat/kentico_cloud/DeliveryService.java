package kenticocloud.kenticoclouddancinggoat.kentico_cloud;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DeliveryService implements IDeliveryService{

    /**
     * Consider using Dependency injection in future improvements
     * https://github.com/google/dagger
     */
    private OkHttpClient httpClient;

    public DeliveryService() {
        httpClient = new OkHttpClient();
    }

    /**
     * Gets response from given url
     * @param url Url from which response will be loaded
     * @return data
     * @throws IOException
     */
    public ResponseBody get(String url) throws IOException{
        return getResponse(url);
    }

    private ResponseBody getResponse(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = httpClient.newCall(request).execute();
        return response.body();
    }
}
