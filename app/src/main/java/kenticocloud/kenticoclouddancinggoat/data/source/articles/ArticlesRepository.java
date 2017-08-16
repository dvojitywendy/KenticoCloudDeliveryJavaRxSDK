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
    public ArticlesRepository(@NonNull ArticlesDataSource dataSource){
        _dataSource = checkNotNull(dataSource);
    }

    @Override
    public void getArticles(@NonNull final LoadArticlesCallback callback) {
        checkNotNull(callback);

        _dataSource.getArticles(new LoadArticlesCallback() {
            @Override
            public void onArticlesLoaded(List<Article> articles) {
                callback.onArticlesLoaded(articles);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getArticle(@NonNull String articleId, @NonNull final GetArticleCallback callback) {
        checkNotNull(articleId);
        checkNotNull(callback);

        _dataSource.getArticle(articleId, new GetArticleCallback() {
            @Override
            public void onArticleLoaded(Article article) {
                callback.onArticleLoaded(article);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
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
