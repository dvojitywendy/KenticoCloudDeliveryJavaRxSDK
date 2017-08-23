package kenticocloud.kenticoclouddancinggoat.data.source.articles;

import android.support.annotation.NonNull;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public class ArticlesRepository implements  ArticlesDataSource {

    private static ArticlesRepository INSTANCE = null;

    private final ArticlesDataSource _dataSource;

    // Prevent direct instantiation.
    private ArticlesRepository(@NonNull ArticlesDataSource dataSource){
        _dataSource = checkNotNull(dataSource);
    }

    @Override
    public void getArticles(@NonNull final LoadArticlesCallback callback) {
        _dataSource.getArticles(new LoadArticlesCallback() {
            @Override
            public void onItemsLoaded(List<Article> articles) {
                callback.onItemsLoaded(articles);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e);
            }
        });
    }

    @Override
    public void getArticle(@NonNull String codename, @NonNull final LoadArticleCallback callback) {
        _dataSource.getArticle(codename, new LoadArticleCallback() {
            @Override
            public void onItemLoaded(Article article) {
                callback.onItemLoaded(article);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e);
            }
        });
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param dataSource the backend data source
     * @return the {@link ArticlesRepository} instance
     */
    public static ArticlesRepository getInstance(ArticlesDataSource dataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ArticlesRepository(dataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
