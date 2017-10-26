package com.kentico.delivery.sample.androidapp.data.source.cafes;

import android.support.annotation.NonNull;

import com.kentico.delivery.sample.androidapp.data.models.Cafe;
import com.kentico.delivery.core.data.IMultipleCallback;
import com.kentico.delivery.core.data.ISingleCallback;

public interface CafesDataSource {
    interface LoadCafesCallback extends IMultipleCallback<Cafe> {
    }

    interface LoadCafeCallback  extends ISingleCallback<Cafe> {
    }

    void getCafes(@NonNull LoadCafesCallback callback);

    void getCafe(@NonNull String codename, @NonNull LoadCafeCallback  callback);
}
