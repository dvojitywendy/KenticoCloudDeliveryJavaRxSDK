package kenticocloud.kenticoclouddancinggoat.app.cafe_detail;

import kenticocloud.kenticoclouddancinggoat.app.core.IBasePresenter;
import kenticocloud.kenticoclouddancinggoat.app.core.IBaseView;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;

/**
 * Created by RichardS on 15. 8. 2017.
 */

interface CafeDetailContract {
    interface View extends IBaseView<Presenter> {
        void showCafe(Cafe cafe);
    }

    interface Presenter extends IBasePresenter {
        void loadCafe();
    }
}
