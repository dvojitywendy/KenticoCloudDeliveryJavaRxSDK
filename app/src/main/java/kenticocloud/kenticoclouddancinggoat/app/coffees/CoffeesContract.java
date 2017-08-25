package kenticocloud.kenticoclouddancinggoat.app.coffees;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.BasePresenter;
import kenticocloud.kenticoclouddancinggoat.BaseView;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Coffee;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public interface CoffeesContract {
    interface View extends BaseView<Presenter>{
        void showCoffees(List<Coffee> coffees);
    }

    interface Presenter extends BasePresenter{
        void loadCoffees();
    }
}
