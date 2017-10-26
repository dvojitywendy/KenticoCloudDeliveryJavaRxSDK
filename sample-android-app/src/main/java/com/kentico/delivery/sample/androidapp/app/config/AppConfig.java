package com.kentico.delivery.sample.androidapp.app.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import io.reactivex.functions.Function;
import com.kentico.delivery.sample.androidapp.data.models.Article;
import com.kentico.delivery.sample.androidapp.data.models.Cafe;
import com.kentico.delivery.sample.androidapp.data.models.Coffee;
import com.kentico.delivery.core.models.item.TypeResolver;

public class AppConfig {
    public final static String KENTICO_CLOUD_PROJECT_ID = "683771be-aa26-4887-b1b6-482f56418ffd";

    public static List<TypeResolver<?>> getTypeResolvers(){

        // Type resolvers are responsible for creating the strongly typed object out of type
        List<TypeResolver<?>> typeResolvers = new ArrayList<>();

        // Cafe resolvers
        typeResolvers.add(new TypeResolver<>(Cafe.TYPE, new Function<Void, Cafe>() {
            @Nullable
            @Override
            public Cafe apply(@Nullable Void input) {
                return new Cafe();
            }
        }));

        /// Article resolver
        typeResolvers.add(new TypeResolver<>(Article.TYPE, new Function<Void, Article>() {
            @Nullable
            @Override
            public Article apply(@Nullable Void input) {
                return new Article();
            }
        }));

        /// Coffee resolver
        typeResolvers.add(new TypeResolver<>(Coffee.TYPE, new Function<Void, Coffee>() {
            @Nullable
            @Override
            public Coffee apply(@Nullable Void input) {
                return new Coffee();
            }
        }));

        return typeResolvers;
    }

}
