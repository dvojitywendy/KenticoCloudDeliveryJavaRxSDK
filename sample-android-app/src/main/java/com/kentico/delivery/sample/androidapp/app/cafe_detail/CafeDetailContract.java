package com.kentico.delivery.sample.androidapp.app.cafe_detail;

import com.kentico.delivery.sample.androidapp.app.core.IBasePresenter;
import com.kentico.delivery.sample.androidapp.app.core.IBaseView;
import com.kentico.delivery.sample.androidapp.data.models.Cafe;

interface CafeDetailContract {

    interface View extends IBaseView<Presenter> {
        void showCafe(Cafe cafe);
    }

    interface Presenter extends IBasePresenter {
        void loadCafe();
    }
}
