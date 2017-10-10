package kenticocloud.kenticoclouddancinggoat.data.source;

public interface ICallback {

    void onDataNotAvailable();

    void onError(Throwable e);
}
