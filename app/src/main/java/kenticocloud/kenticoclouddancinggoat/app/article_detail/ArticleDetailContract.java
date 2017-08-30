package kenticocloud.kenticoclouddancinggoat.app.article_detail;

import kenticocloud.kenticoclouddancinggoat.app.core.IBasePresenter;
import kenticocloud.kenticoclouddancinggoat.app.core.IBaseView;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;

/**
 * Created by RichardS on 15. 8. 2017.
 */

interface ArticleDetailContract {
    interface View extends IBaseView<Presenter> {
        void showArticle(Article article);
    }

    interface Presenter extends IBasePresenter {
        void loadArticle();
    }
}
