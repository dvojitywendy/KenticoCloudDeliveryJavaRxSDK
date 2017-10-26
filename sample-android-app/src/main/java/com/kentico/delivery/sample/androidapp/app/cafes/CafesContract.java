package com.kentico.delivery.sample.androidapp.app.cafes;

import java.util.List;

import com.kentico.delivery.sample.androidapp.app.core.IBasePresenter;
import com.kentico.delivery.sample.androidapp.app.core.IBaseView;
import com.kentico.delivery.sample.androidapp.data.models.Cafe;

interface CafesContract {
    interface View extends IBaseView<Presenter> {

        void showCafes(List<Cafe> cafes);
    }

    interface Presenter extends IBasePresenter {
        void loadCafes();
    }
}
