package delivery.data;


import java.util.List;

public interface IMultipleCallback<T> extends ICallback{

    void onItemsLoaded(List<T> items);

}