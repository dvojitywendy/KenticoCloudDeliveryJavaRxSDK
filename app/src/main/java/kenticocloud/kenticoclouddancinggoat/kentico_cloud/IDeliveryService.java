package kenticocloud.kenticoclouddancinggoat.kentico_cloud;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.MultipleItemQuery;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item.SingleItemQuery;

/**
 * Created by RichardS on 10. 8. 2017.
 */
public interface IDeliveryService{

     /**
      * Gets query for multiple items
      */
      <TItem extends IContentItem> MultipleItemQuery items();

     /**
      * Gets query for single item
      */
     SingleItemQuery item(@NonNull String itemCodename);
}
