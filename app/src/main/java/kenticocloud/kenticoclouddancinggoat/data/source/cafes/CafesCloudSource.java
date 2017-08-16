package kenticocloud.kenticoclouddancinggoat.data.source.cafes;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public class CafesCloudSource implements CafesDataSource {

    private static CafesCloudSource INSTANCE;

    // Prevent direct instantiation.
    private CafesCloudSource(@NonNull Context context) {
        checkNotNull(context);
    }

    public static CafesCloudSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CafesCloudSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getCafes(@NonNull LoadCafesCallback callback) {
        List<Cafe> cafes = new ArrayList<Cafe>();

        cafes.add(new Cafe("cafe 1"));
        cafes.add(new Cafe("cafe 2"));
        cafes.add(new Cafe("cafe 3"));
        cafes.add(new Cafe("cafe 4"));

        callback.onCafesLoaded(cafes);

        if (cafes.size() == 0){
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void getCafe(@NonNull String articleId, @NonNull GetCafeCallback callback) {
        Cafe cafe = new Cafe("Single cafe 1");

        callback.onCafeLoaded(cafe);
    }
}
