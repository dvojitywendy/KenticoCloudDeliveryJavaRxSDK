package kenticocloud.kenticoclouddancinggoat.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.app.config.AppConfig;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesCloudSource;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesRepository;
import kenticocloud.kenticoclouddancinggoat.data.source.cafes.CafesCloudSource;
import kenticocloud.kenticoclouddancinggoat.data.source.cafes.CafesRepository;
import kenticocloud.kenticoclouddancinggoat.data.source.coffees.CoffeesCloudSource;
import kenticocloud.kenticoclouddancinggoat.data.source.coffees.CoffeesRepository;
import com.kenticocloud.delivery.services.DeliveryService;
import com.kenticocloud.delivery.services.IDeliveryService;
import com.kenticocloud.delivery.config.DeliveryClientConfig;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Enables injection of production implementations at compile time.
 */
public class Injection {

    public static ArticlesRepository provideArticlesRepository(@NonNull Context context) {
        return ArticlesRepository.getInstance(ArticlesCloudSource.getInstance(context));
    }

    public static CafesRepository provideCafessRepository(@NonNull Context context) {
        return CafesRepository.getInstance(CafesCloudSource.getInstance(context));
    }

    public static CoffeesRepository provideCoffeesRepository(@NonNull Context context) {
        return CoffeesRepository.getInstance(CoffeesCloudSource.getInstance(context));
    }

    public static IDeliveryService provideDeliveryService() {
        return DeliveryService.getInstance(new DeliveryClientConfig(AppConfig.KENTICO_CLOUD_PROJECT_ID, AppConfig.getTypeResolvers()));
    }
}