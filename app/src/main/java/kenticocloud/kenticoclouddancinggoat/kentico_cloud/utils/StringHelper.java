package kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by RichardS on 18. 8. 2017.
 */

public class StringHelper {

    public static String Join(@NonNull List<String> list, @NonNull String separator){
        String result = "";
        for (int i = 0; i < list.size(); i++){
            String item = list.get(i);
            if (i == 0){
                result += item;
            }
            else{
                result += separator + item;
            }
        }

        return result;
    }
}
