package com.kentico.delivery.sample.androidapp.app.cafes;

import android.support.annotation.NonNull;

import java.util.List;

import com.kentico.delivery.sample.androidapp.data.models.Cafe;
import com.kentico.delivery.sample.androidapp.data.source.cafes.CafesDataSource;
import com.kentico.delivery.sample.androidapp.data.source.cafes.CafesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

class CafesPresenter implements CafesContract.Presenter {

    private final CafesRepository repository;

    private final CafesContract.View view;

    CafesPresenter(@NonNull CafesRepository repository, @NonNull CafesContract.View view) {
        this.repository = checkNotNull(repository, "repository cannot be null");
        this.view = checkNotNull(view, "view cannot be null!");

        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        loadCafes();
    }

    @Override
    public void loadCafes() {
        this.view.setLoadingIndicator(true);

        this.repository.getCafes(new CafesDataSource.LoadCafesCallback() {
            @Override
            public void onItemsLoaded(List<Cafe> cafes) {
                view.setLoadingIndicator(false);
                view.showCafes(cafes);
            }

            @Override
            public void onDataNotAvailable() {
                view.showNoData(true);
            }

            @Override
            public void onError(Throwable e) {
                view.showLoadingError();
            }
        });
    }
}
