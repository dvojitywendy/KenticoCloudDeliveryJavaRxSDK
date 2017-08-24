package kenticocloud.kenticoclouddancinggoat;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public interface BaseView<T> {
    void setLoadingIndicator(boolean active);
    void showNoData(boolean show);
    void showLoadingError();
    void setPresenter(T presenter);
}
