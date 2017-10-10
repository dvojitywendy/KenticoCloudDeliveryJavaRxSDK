package kenticocloud.kenticoclouddancinggoat.data.source.articles;

import android.support.annotation.NonNull;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.source.IMultipleCallback;
import kenticocloud.kenticoclouddancinggoat.data.source.ISingleCallback;

public interface ArticlesDataSource {

    interface LoadArticlesCallback extends IMultipleCallback<Article> {
    }

    interface LoadArticleCallback extends ISingleCallback<Article> {
    }

    void getArticles(@NonNull LoadArticlesCallback callback);

    void getArticle(@NonNull String codename, @NonNull LoadArticleCallback callback);
}
