package kenticocloud.kenticoclouddancinggoat.data.source.cafes;

import android.support.annotation.NonNull;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public interface CafesDataSource {
    interface LoadCafesCallback {

        void onCafesLoaded(List<Cafe> cafes);

        void onDataNotAvailable();
    }

    interface GetCafeCallback {

        void onCafeLoaded(Cafe cafe);

        void onDataNotAvailable();
    }

    void getCafes(@NonNull LoadCafesCallback callback);

    void getCafe(@NonNull String articleId, @NonNull GetCafeCallback callback);
}
