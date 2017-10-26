package kenticocloud.kenticoclouddancinggoat.app.cafe_detail;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;
import kenticocloud.kenticoclouddancinggoat.data.source.cafes.CafesDataSource;
import kenticocloud.kenticoclouddancinggoat.data.source.cafes.CafesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

class CafeDetailPresenter implements CafeDetailContract.Presenter {

    private final String cafeCodename;
    private final CafesRepository repository;

    private final CafeDetailContract.View view;

    CafeDetailPresenter(@NonNull CafesRepository repository, @NonNull CafeDetailContract.View view, @NonNull String cafeCodename) {
        this.repository = checkNotNull(repository, "repository cannot be null");
        this.view = checkNotNull(view, "view cannot be null!");
        this.view.setPresenter(this);
        this.cafeCodename = cafeCodename;
    }

    @Override
    public void start() {
        loadCafe();
    }

    @Override
    public void loadCafe() {
        this.view.setLoadingIndicator(true);

        this.repository.getCafe(this.cafeCodename, new CafesDataSource.LoadCafeCallback() {

            @Override
            public void onItemLoaded(Cafe item) {
                view.showCafe(item);
            }

            @Override
            public void onDataNotAvailable() {
                view.showLoadingError();
            }

            @Override
            public void onError(Throwable e) {
                view.showLoadingError();
            }
        });
    }
}
