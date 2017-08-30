package kenticocloud.kenticoclouddancinggoat.app.articles;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.app.core.IBasePresenter;
import kenticocloud.kenticoclouddancinggoat.app.core.IBaseView;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;

/**
 * Created by RichardS on 15. 8. 2017.
 */

interface ArticlesContract {
    interface View extends IBaseView<Presenter> {
        void showArticles(List<Article> articles);
    }

    interface Presenter extends IBasePresenter {
        void loadArticles();
    }
}
