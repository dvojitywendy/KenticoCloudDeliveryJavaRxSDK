package kenticocloud.kenticoclouddancinggoat.app.cafes;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.app.core.IBasePresenter;
import kenticocloud.kenticoclouddancinggoat.app.core.IBaseView;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;

interface CafesContract {
    interface View extends IBaseView<Presenter> {

        void showCafes(List<Cafe> cafes);
    }

    interface Presenter extends IBasePresenter {
        void loadCafes();
    }
}
