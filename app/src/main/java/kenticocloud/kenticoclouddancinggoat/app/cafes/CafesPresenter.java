package kenticocloud.kenticoclouddancinggoat.app.cafes;

import android.support.annotation.NonNull;

import java.util.List;

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

public class CafesPresenter implements CafesContract.Presenter {

    private final CafesRepository _repository;

    private final CafesContract.View _view;

    public CafesPresenter(@NonNull CafesRepository repository, @NonNull CafesContract.View view) {
        _repository = checkNotNull(repository, "repository cannot be null");
        _view = checkNotNull(view, "view cannot be null!");

        _view.setPresenter(this);
    }

    @Override
    public void start() {
        loadCafes();
    }

    @Override
    public void loadCafes() {
        _view.setLoadingIndicator(true);

        _repository.getCafes(new CafesDataSource.LoadCafesCallback() {
            @Override
            public void onItemsLoaded(List<Cafe> cafes) {
                _view.setLoadingIndicator(false);
                _view.showCafes(cafes);
            }

            @Override
            public void onDataNotAvailable() {
                _view.showLoadingTasksError();
            }

            @Override
            public void onError() {

            }
        });
    }
}
