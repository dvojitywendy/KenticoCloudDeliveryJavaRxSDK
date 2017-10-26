package com.kentico.delivery.sample.androidapp.app.articles;

import android.support.annotation.NonNull;

import java.util.List;

import com.kentico.delivery.sample.androidapp.data.models.Article;
import com.kentico.delivery.sample.androidapp.data.source.articles.ArticlesDataSource;
import com.kentico.delivery.sample.androidapp.data.source.articles.ArticlesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

class ArticlesPresenter implements ArticlesContract.Presenter {

    private final ArticlesRepository repository;

    private final ArticlesContract.View view;

    ArticlesPresenter(@NonNull ArticlesRepository repository, @NonNull ArticlesContract.View view) {
        this.repository = checkNotNull(repository, "repository cannot be null");
        this.view = checkNotNull(view, "view cannot be null!");

        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        loadArticles();
    }

    @Override
    public void loadArticles() {
        this.view.setLoadingIndicator(true);

        this.repository.getArticles(new ArticlesDataSource.LoadArticlesCallback() {
            @Override
            public void onItemsLoaded(List<Article> articles) {
                view.setLoadingIndicator(false);
                view.showArticles(articles);
            }

            @Override
            public void onDataNotAvailable() {
                view.showNoData(true);
            }

            @Override
            public void onError(Throwable e) {
                view.showLoadingError();
            }
        });
    }
}
