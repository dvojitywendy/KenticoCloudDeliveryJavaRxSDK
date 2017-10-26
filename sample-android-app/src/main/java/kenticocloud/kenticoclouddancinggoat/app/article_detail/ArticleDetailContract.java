package kenticocloud.kenticoclouddancinggoat.app.article_detail;

import kenticocloud.kenticoclouddancinggoat.app.core.IBasePresenter;
import kenticocloud.kenticoclouddancinggoat.app.core.IBaseView;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;

interface ArticleDetailContract {
    interface View extends IBaseView<Presenter> {
        void showArticle(Article article);
    }

    interface Presenter extends IBasePresenter {
        void loadArticle();
    }
}
