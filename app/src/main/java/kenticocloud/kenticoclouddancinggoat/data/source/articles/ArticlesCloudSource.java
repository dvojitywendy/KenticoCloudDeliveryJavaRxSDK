package kenticocloud.kenticoclouddancinggoat.data.source.articles;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.source.BaseCloudSource;
import kenticocloud.kenticoclouddancinggoat.injection.Injection;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemListingResponse;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.DeliveryItemResponse;

import static com.google.common.base.Preconditions.checkNotNull;

public class ArticlesCloudSource extends BaseCloudSource implements ArticlesDataSource {

    private static ArticlesCloudSource INSTANCE;

    // Prevent direct instantiation.
    private ArticlesCloudSource(@NonNull Context context) {
        super(Injection.provideDeliveryService());
    }

    public static ArticlesCloudSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ArticlesCloudSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getArticles(@NonNull final LoadArticlesCallback callback) {
        this.deliveryService.<Article>items()
                .type(Article.TYPE)
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeliveryItemListingResponse<Article>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeliveryItemListingResponse<Article> response) {
                        List<Article> items = (response.getItems());

                        if (items == null || items.size() == 0){
                            callback.onDataNotAvailable();
                            return;
                        }

                        callback.onItemsLoaded(items);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getArticle(@NonNull String codename, @NonNull final LoadArticleCallback callback) {
        this.deliveryService.<Article>item(codename)
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeliveryItemResponse<Article>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeliveryItemResponse<Article> response) {
                        if (response.getItem() == null){
                            callback.onDataNotAvailable();
                        }

                        callback.onItemLoaded(response.getItem());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
