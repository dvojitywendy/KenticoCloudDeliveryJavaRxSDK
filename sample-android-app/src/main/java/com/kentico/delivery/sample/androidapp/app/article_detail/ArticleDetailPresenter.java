package com.kentico.delivery.sample.androidapp.app.article_detail;

import android.support.annotation.NonNull;

import com.kentico.delivery.sample.androidapp.data.models.Article;
import com.kentico.delivery.sample.androidapp.data.source.articles.ArticlesDataSource;
import com.kentico.delivery.sample.androidapp.data.source.articles.ArticlesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

class ArticleDetailPresenter implements ArticleDetailContract.Presenter {

    private final String articleCodename;
    private final ArticlesRepository repository;

    private final ArticleDetailContract.View view;

    ArticleDetailPresenter(@NonNull ArticlesRepository repository, @NonNull ArticleDetailContract.View view, @NonNull String articleCodename) {
        this.repository = checkNotNull(repository, "repository cannot be null");
        this.view = checkNotNull(view, "view cannot be null!");
        this.view.setPresenter(this);
        this.articleCodename = articleCodename;
    }

    @Override
    public void start() {
        loadArticle();
    }

    @Override
    public void loadArticle() {
        this.view.setLoadingIndicator(true);

        this.repository.getArticle(this.articleCodename, new ArticlesDataSource.LoadArticleCallback() {

            @Override
            public void onItemLoaded(Article item) {
                view.showArticle(item);
            }

            @Override
            public void onDataNotAvailable() {
                 view.showLoadingError();
            }

            @Override
            public void onError(Throwable e) {
                view.showLoadingError();
            }
        });
    }
}
