package com.kentico.delivery.sample.androidapp.app.articles;

import java.util.List;

import com.kentico.delivery.sample.androidapp.app.core.IBasePresenter;
import com.kentico.delivery.sample.androidapp.app.core.IBaseView;
import com.kentico.delivery.sample.androidapp.data.models.Article;

interface ArticlesContract {
    interface View extends IBaseView<Presenter> {
        void showArticles(List<Article> articles);
    }

    interface Presenter extends IBasePresenter {
        void loadArticles();
    }
}
