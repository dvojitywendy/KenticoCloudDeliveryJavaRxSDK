package kenticocloud.kenticoclouddancinggoat.app.cafes;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.BasePresenter;
import kenticocloud.kenticoclouddancinggoat.BaseView;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public interface CafesContract {
    interface View extends BaseView<Presenter>{

        void setLoadingIndicator(boolean active);
        void showCafes(List<Cafe> cafes);

        void showLoadingTasksError();
    }

    interface Presenter extends BasePresenter{
        void loadCafes();
    }
}
