package kenticocloud.kenticoclouddancinggoat.kentico_cloud;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.MultipleItemQuery;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.SingleItemQuery;

/**
 * Created by RichardS on 10. 8. 2017.
 */
public interface IDeliveryService{

     /**
      * Gets query for multiple items
      */
      MultipleItemQuery items();

     /**
      * Gets query for single item
      */
     SingleItemQuery item(@NonNull String itemCodename);
}
