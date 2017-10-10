package kenticocloud.kenticoclouddancinggoat.app.articles;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.app.core.IBasePresenter;
import kenticocloud.kenticoclouddancinggoat.app.core.IBaseView;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;

interface ArticlesContract {
    interface View extends IBaseView<Presenter> {
        void showArticles(List<Article> articles);
    }

    interface Presenter extends IBasePresenter {
        void loadArticles();
    }
}
