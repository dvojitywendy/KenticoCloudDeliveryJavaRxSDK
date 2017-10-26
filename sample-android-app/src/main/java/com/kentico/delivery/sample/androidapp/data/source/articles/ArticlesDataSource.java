package com.kentico.delivery.sample.androidapp.data.source.articles;

import android.support.annotation.NonNull;

import com.kentico.delivery.sample.androidapp.data.models.Article;
import com.kentico.delivery.core.data.IMultipleCallback;
import com.kentico.delivery.core.data.ISingleCallback;

public interface ArticlesDataSource {

    interface LoadArticlesCallback extends IMultipleCallback<Article> {
    }

    interface LoadArticleCallback extends ISingleCallback<Article> {
    }

    void getArticles(@NonNull LoadArticlesCallback callback);

    void getArticle(@NonNull String codename, @NonNull LoadArticleCallback callback);
}
