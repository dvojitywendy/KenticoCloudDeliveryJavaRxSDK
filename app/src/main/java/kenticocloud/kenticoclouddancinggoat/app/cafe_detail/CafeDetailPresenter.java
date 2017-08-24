package kenticocloud.kenticoclouddancinggoat.app.cafe_detail;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesDataSource;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesRepository;
import kenticocloud.kenticoclouddancinggoat.data.source.cafes.CafesDataSource;
import kenticocloud.kenticoclouddancinggoat.data.source.cafes.CafesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

class CafeDetailPresenter implements CafeDetailContract.Presenter {

    private final String _cafeCodename;
    private final CafesRepository _repository;

    private final CafeDetailContract.View _view;

    CafeDetailPresenter(@NonNull CafesRepository repository, @NonNull CafeDetailContract.View view, @NonNull String cafeCodename) {
        _repository = checkNotNull(repository, "repository cannot be null");
        _view = checkNotNull(view, "view cannot be null!");
        _view.setPresenter(this);
        _cafeCodename = cafeCodename;
    }

    @Override
    public void start() {
        loadCafe();
    }

    @Override
    public void loadCafe() {
        _view.setLoadingIndicator(true);

        _repository.getCafe(_cafeCodename, new CafesDataSource.LoadCafeCallback() {

            @Override
            public void onItemLoaded(Cafe item) {
                _view.showCafe(item);
            }

            @Override
            public void onDataNotAvailable() {
                _view.showLoadingError();
            }

            @Override
            public void onError(Throwable e) {
                _view.showLoadingError();
            }
        });
    }
}
