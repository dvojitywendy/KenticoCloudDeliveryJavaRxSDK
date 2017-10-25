package com.kenticocloud.delivery.utils;



import java.util.List;


public class StringHelper {

    public static String join( List<String> list,  String separator){
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
