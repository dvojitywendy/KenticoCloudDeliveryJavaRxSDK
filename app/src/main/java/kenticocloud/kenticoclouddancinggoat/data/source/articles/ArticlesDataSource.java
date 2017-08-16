package kenticocloud.kenticoclouddancinggoat.data.source.articles;

import android.support.annotation.NonNull;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public interface ArticlesDataSource {
    interface LoadArticlesCallback {

        void onArticlesLoaded(List<Article> articles);

        void onDataNotAvailable();
    }

    interface GetArticleCallback {

        void onArticleLoaded(Article article);

        void onDataNotAvailable();
    }

    void getArticles(@NonNull LoadArticlesCallback callback);

    void getArticle(@NonNull String articleId, @NonNull GetArticleCallback callback);
}
