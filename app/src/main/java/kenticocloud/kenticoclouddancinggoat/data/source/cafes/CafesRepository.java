package kenticocloud.kenticoclouddancinggoat.data.source.cafes;

import android.support.annotation.NonNull;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public class CafesRepository implements CafesDataSource {

    private static CafesRepository INSTANCE = null;

    private final CafesDataSource _dataSource;

    // Prevent direct instantiation.
    public CafesRepository(@NonNull CafesDataSource dataSource){
        _dataSource = checkNotNull(dataSource);
    }

    @Override
    public void getCafes(@NonNull final LoadCafesCallback callback) {
        checkNotNull(callback);

        _dataSource.getCafes(new LoadCafesCallback() {
            @Override
            public void onCafesLoaded(List<Cafe> cafes) {
                callback.onCafesLoaded(cafes);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getCafe(@NonNull String cafeId, @NonNull final GetCafeCallback callback) {
        checkNotNull(cafeId);
        checkNotNull(callback);

        _dataSource.getCafe(cafeId, new GetCafeCallback() {
            @Override
            public void onCafeLoaded(Cafe cafe) {
                callback.onCafeLoaded(cafe);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param dataSource the backend data source
     * @return the {@link CafesRepository} instance
     */
    public static CafesRepository getInstance(CafesDataSource dataSource) {
        if (INSTANCE == null) {
            INSTANCE = new CafesRepository(dataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
