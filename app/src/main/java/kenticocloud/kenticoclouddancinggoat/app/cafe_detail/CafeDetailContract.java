package kenticocloud.kenticoclouddancinggoat.app.cafe_detail;

import kenticocloud.kenticoclouddancinggoat.BasePresenter;
import kenticocloud.kenticoclouddancinggoat.BaseView;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;

/**
 * Created by RichardS on 15. 8. 2017.
 */

interface CafeDetailContract {
    interface View extends BaseView<Presenter>{
        void showCafe(Cafe cafe);
    }

    interface Presenter extends BasePresenter{
        void loadCafe();
    }
}
