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
        _dataSource.getCafes(new LoadCafesCallback() {
            @Override
            public void onItemsLoaded(List<Cafe> cafes) {
                callback.onItemsLoaded(cafes);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e);
            }
        });
    }

    @Override
    public void getCafe(@NonNull String codename, @NonNull final GetCafeCallback callback) {
        _dataSource.getCafe(codename, new GetCafeCallback() {
            @Override
            public void onItemLoaded(Cafe cafe) {
                callback.onItemLoaded(cafe);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e);
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
