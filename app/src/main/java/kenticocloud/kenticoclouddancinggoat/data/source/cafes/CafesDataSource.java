package kenticocloud.kenticoclouddancinggoat.data.source.cafes;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;
import com.kenticocloud.delivery.data.IMultipleCallback;
import com.kenticocloud.delivery.data.ISingleCallback;

public interface CafesDataSource {
    interface LoadCafesCallback extends IMultipleCallback<Cafe> {
    }

    interface LoadCafeCallback  extends ISingleCallback<Cafe> {
    }

    void getCafes(@NonNull LoadCafesCallback callback);

    void getCafe(@NonNull String codename, @NonNull LoadCafeCallback  callback);
}
