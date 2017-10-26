package com.kentico.delivery.sample.androidapp.app.test;

import java.util.List;

import com.kentico.delivery.sample.androidapp.app.core.IBasePresenter;
import com.kentico.delivery.sample.androidapp.app.core.IBaseView;
import com.kentico.delivery.sample.androidapp.data.models.Article;

interface TestContract {
    interface View extends IBaseView<Presenter> {
    }

    interface Presenter extends IBasePresenter {
        void loadData();
    }
}
