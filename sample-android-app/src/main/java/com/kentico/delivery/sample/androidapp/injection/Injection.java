package com.kentico.delivery.sample.androidapp.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.kentico.delivery.android.DeliveryAndroidService;
import com.kentico.delivery.core.config.DeliveryClientConfig;
import com.kentico.delivery.core.services.IDeliveryService;

import com.kentico.delivery.sample.androidapp.app.config.AppConfig;
import com.kentico.delivery.sample.androidapp.data.source.articles.ArticlesCloudSource;
import com.kentico.delivery.sample.androidapp.data.source.articles.ArticlesRepository;
import com.kentico.delivery.sample.androidapp.data.source.cafes.CafesCloudSource;
import com.kentico.delivery.sample.androidapp.data.source.cafes.CafesRepository;
import com.kentico.delivery.sample.androidapp.data.source.coffees.CoffeesCloudSource;
import com.kentico.delivery.sample.androidapp.data.source.coffees.CoffeesRepository;

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
       return DeliveryAndroidService.getInstance(new DeliveryClientConfig(AppConfig.KENTICO_CLOUD_PROJECT_ID, AppConfig.getTypeResolvers()));
    }
}