package com.kentico.delivery.sample.androidapp.app.article_detail;

import com.kentico.delivery.sample.androidapp.app.core.IBasePresenter;
import com.kentico.delivery.sample.androidapp.app.core.IBaseView;
import com.kentico.delivery.sample.androidapp.data.models.Article;

interface ArticleDetailContract {
    interface View extends IBaseView<Presenter> {
        void showArticle(Article article);
    }

    interface Presenter extends IBasePresenter {
        void loadArticle();
    }
}
