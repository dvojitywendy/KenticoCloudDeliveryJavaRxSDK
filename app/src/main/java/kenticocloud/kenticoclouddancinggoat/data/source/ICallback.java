package kenticocloud.kenticoclouddancinggoat.data.source;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public interface ICallback {

    void onDataNotAvailable();

    void onError(Throwable e);
}
