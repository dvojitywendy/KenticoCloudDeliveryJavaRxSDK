package delivery.services;



import delivery.config.DeliveryClientConfig;
import delivery.interfaces.item.item.IContentItem;
import delivery.query.item.MultipleItemQuery;
import delivery.query.item.SingleItemQuery;
import delivery.query.type.MultipleTypeQuery;
import delivery.query.type.SingleTypeQuery;

public class DeliveryService implements IDeliveryService {

    private static DeliveryService INSTANCE;

    private DeliveryClientConfig config;

    private DeliveryService(DeliveryClientConfig config) {
        this.config = config;
    }

    /**
     * Gets static instance of Delivery client
     * @param config Delivery client configuration
     * @return Delivery client instance
     */
    public static IDeliveryService getInstance(DeliveryClientConfig config) {
        if (INSTANCE == null) {
            INSTANCE = new DeliveryService(config);
        }
        return INSTANCE;
    }

    public <TItem extends IContentItem> MultipleItemQuery<TItem> items(){
        return new MultipleItemQuery<>(this.config);
    }

    public <TItem extends IContentItem> SingleItemQuery<TItem> item( String itemCodename){
        return new SingleItemQuery<>(this.config, itemCodename);
    }

    @Override
    public SingleTypeQuery type( String typeCodename) {
        return new SingleTypeQuery(this.config, typeCodename);
    }

    @Override
    public MultipleTypeQuery types() {
        return new MultipleTypeQuery(this.config);
    }
}
