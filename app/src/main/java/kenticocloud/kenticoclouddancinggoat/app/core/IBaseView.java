package kenticocloud.kenticoclouddancinggoat.app.core;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public interface IBaseView<T> {
    void setLoadingIndicator(boolean active);
    void showNoData(boolean show);
    void showLoadingError();
    void setPresenter(T presenter);
}
