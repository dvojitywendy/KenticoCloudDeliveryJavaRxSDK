package kenticocloud.kenticoclouddancinggoat.injection;

/**
 * Created by RichardS on 15. 8. 2017.
 */

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.common.base.Function;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import kenticocloud.kenticoclouddancinggoat.app.config.AppConfig;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesCloudSource;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesRepository;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesDataSource;
import kenticocloud.kenticoclouddancinggoat.data.source.cafes.CafesCloudSource;
import kenticocloud.kenticoclouddancinggoat.data.source.cafes.CafesRepository;
import kenticocloud.kenticoclouddancinggoat.data.source.coffees.CoffeesCloudSource;
import kenticocloud.kenticoclouddancinggoat.data.source.coffees.CoffeesRepository;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.DeliveryService;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.IDeliveryService;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.TypeResolver;

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
        return DeliveryService.getInstance(new DeliveryClientConfig(AppConfig.KenticoCloudProjectId, AppConfig.getTypeResolvers()));
    }
}