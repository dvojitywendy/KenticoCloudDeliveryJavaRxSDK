package kenticocloud.kenticoclouddancinggoat.app.test;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.app.core.IBasePresenter;
import kenticocloud.kenticoclouddancinggoat.app.core.IBaseView;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;

interface TestContract {
    interface View extends IBaseView<Presenter> {
    }

    interface Presenter extends IBasePresenter {
        void loadData();
    }
}
