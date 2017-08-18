package kenticocloud.kenticoclouddancinggoat.data.source.cafes;

import android.support.annotation.NonNull;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;
import kenticocloud.kenticoclouddancinggoat.data.source.IMultipleCallback;
import kenticocloud.kenticoclouddancinggoat.data.source.ISingleCallback;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public interface CafesDataSource {
    interface LoadCafesCallback extends IMultipleCallback<Cafe> {
    }

    interface GetCafeCallback  extends ISingleCallback<Cafe> {
    }

    void getCafes(@NonNull LoadCafesCallback callback);

    void getCafe(@NonNull String codename, @NonNull GetCafeCallback  callback);
}
