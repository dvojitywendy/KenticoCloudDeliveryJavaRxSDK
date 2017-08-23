package kenticocloud.kenticoclouddancinggoat.app.article_detail;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.BasePresenter;
import kenticocloud.kenticoclouddancinggoat.BaseView;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;

/**
 * Created by RichardS on 15. 8. 2017.
 */

interface ArticleDetailContract {
    interface View extends BaseView<Presenter>{
        void showArticle(Article article);
    }

    interface Presenter extends BasePresenter{
        void loadArticle();
    }
}
