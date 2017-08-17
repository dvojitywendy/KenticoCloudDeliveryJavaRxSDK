package kenticocloud.kenticoclouddancinggoat.injection;

/**
 * Created by RichardS on 15. 8. 2017.
 */

import android.content.Context;
import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesCloudSource;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesRepository;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesDataSource;
import kenticocloud.kenticoclouddancinggoat.data.source.cafes.CafesCloudSource;
import kenticocloud.kenticoclouddancinggoat.data.source.cafes.CafesRepository;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.DeliveryService;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.IDeliveryService;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Enables injection of production implementations for
 * {@link ArticlesDataSource} at compile time.
 */
public class Injection {

    public static ArticlesRepository provideArticlesRepository(@NonNull Context context) {
        checkNotNull(context);
        return ArticlesRepository.getInstance(ArticlesCloudSource.getInstance(context));
    }

    public static CafesRepository provideCafessRepository(@NonNull Context context) {
        checkNotNull(context);
        return CafesRepository.getInstance(CafesCloudSource.getInstance(context));
    }

    public static IDeliveryService provideDeliveryService() {
        return DeliveryService.getInstance(new DeliveryClientConfig("683771be-aa26-4887-b1b6-482f56418ffd"));
    }
}