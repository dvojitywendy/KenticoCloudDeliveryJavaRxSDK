package kenticocloud.kenticoclouddancinggoat.app.cafes;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.app.core.IBasePresenter;
import kenticocloud.kenticoclouddancinggoat.app.core.IBaseView;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;

/**
 * Created by RichardS on 15. 8. 2017.
 */

interface CafesContract {
    interface View extends IBaseView<Presenter> {

        void showCafes(List<Cafe> cafes);
    }

    interface Presenter extends IBasePresenter {
        void loadCafes();
    }
}
