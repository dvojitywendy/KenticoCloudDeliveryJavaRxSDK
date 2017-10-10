package kenticocloud.kenticoclouddancinggoat.data.source.cafes;

import android.support.annotation.NonNull;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;
import kenticocloud.kenticoclouddancinggoat.data.source.IMultipleCallback;
import kenticocloud.kenticoclouddancinggoat.data.source.ISingleCallback;

public interface CafesDataSource {
    interface LoadCafesCallback extends IMultipleCallback<Cafe> {
    }

    interface LoadCafeCallback  extends ISingleCallback<Cafe> {
    }

    void getCafes(@NonNull LoadCafesCallback callback);

    void getCafe(@NonNull String codename, @NonNull LoadCafeCallback  callback);
}
