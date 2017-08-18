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

import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesCloudSource;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesRepository;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesDataSource;
import kenticocloud.kenticoclouddancinggoat.data.source.cafes.CafesCloudSource;
import kenticocloud.kenticoclouddancinggoat.data.source.cafes.CafesRepository;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.DeliveryService;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.IDeliveryService;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.TypeResolver;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Enables injection of production implementations for
 * {@link ArticlesDataSource} at compile time.
 */
public class Injection {

    public static ArticlesRepository provideArticlesRepository(@NonNull Context context) {
        return ArticlesRepository.getInstance(ArticlesCloudSource.getInstance(context));
    }

    public static CafesRepository provideCafessRepository(@NonNull Context context) {
        return CafesRepository.getInstance(CafesCloudSource.getInstance(context));
    }

    public static IDeliveryService provideDeliveryService() {
        // Kentico cloud project id
        String projectId = "683771be-aa26-4887-b1b6-482f56418ffd";

        // Type resolvers are responsible for creating the strongly typed object out of type
        List<TypeResolver> typeResolvers = new ArrayList<>();
        typeResolvers.add(new TypeResolver("cafe", new Function<Void, IContentItem>() {
            @Nullable
            @Override
            public IContentItem apply(@Nullable Void input) {
                return new Cafe();
            }
        }));

        return DeliveryService.getInstance(new DeliveryClientConfig(projectId, typeResolvers));
    }
}