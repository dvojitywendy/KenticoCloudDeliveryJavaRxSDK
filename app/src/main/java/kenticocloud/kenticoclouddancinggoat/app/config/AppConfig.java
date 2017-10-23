package kenticocloud.kenticoclouddancinggoat.app.config;

import com.google.common.base.Function;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;
import kenticocloud.kenticoclouddancinggoat.data.models.Coffee;
import com.kenticocloud.delivery.models.item.TypeResolver;

public class AppConfig {
    public final static String KENTICO_CLOUD_PROJECT_ID = "683771be-aa26-4887-b1b6-482f56418ffd";

    public static List<TypeResolver<?>> getTypeResolvers(){

        // Type resolvers are responsible for creating the strongly typed object out of type
        List<TypeResolver<?>> typeResolvers = new ArrayList<>();

        // Cafe resolver
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
