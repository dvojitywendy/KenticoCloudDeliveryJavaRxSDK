package kenticocloud.kenticoclouddancinggoat.app.core;

public interface IBaseView<T> {
    void setLoadingIndicator(boolean active);
    void showNoData(boolean show);
    void showLoadingError();
    void setPresenter(T presenter);
}
