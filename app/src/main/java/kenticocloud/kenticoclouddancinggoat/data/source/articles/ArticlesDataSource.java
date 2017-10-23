package kenticocloud.kenticoclouddancinggoat.data.source.articles;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import com.kenticocloud.delivery.data.IMultipleCallback;
import com.kenticocloud.delivery.data.ISingleCallback;

public interface ArticlesDataSource {

    interface LoadArticlesCallback extends IMultipleCallback<Article> {
    }

    interface LoadArticleCallback extends ISingleCallback<Article> {
    }

    void getArticles(@NonNull LoadArticlesCallback callback);

    void getArticle(@NonNull String codename, @NonNull LoadArticleCallback callback);
}
