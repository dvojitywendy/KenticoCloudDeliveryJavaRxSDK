package kenticocloud.kenticoclouddancinggoat.KenticoCloud;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by RichardS on 10. 8. 2017.
 */
public interface IDeliveryService {
     ResponseBody get(String url) throws IOException;
}
