package samplejava;

import com.kenticocloud.delivery.models.item.TypeResolver;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;
import samplejava.models.Article;
import samplejava.models.Cafe;
import samplejava.models.Coffee;

public class AppConfig {
    public final static String KENTICO_CLOUD_PROJECT_ID = "683771be-aa26-4887-b1b6-482f56418ffd";

    public static List<TypeResolver<?>> getTypeResolvers(){

        // Type resolvers are responsible for creating the strongly typed object out of type
        List<TypeResolver<?>> typeResolvers = new ArrayList<>();

        // Cafe resolvers
        typeResolvers.add(new TypeResolver<>(Cafe.TYPE, new Function<Void, Cafe>() {
            @Override
            public Cafe apply(Void input) {
                return new Cafe();
            }
        }));

        /// Article resolver
        typeResolvers.add(new TypeResolver<>(Article.TYPE, new Function<Void, Article>() {
            @Override
            public Article apply(Void input) {
                return new Article();
            }
        }));

        /// Coffee resolver
        typeResolvers.add(new TypeResolver<>(Coffee.TYPE, new Function<Void, Coffee>() {
            @Override
            public Coffee apply(Void input) {
                return new Coffee();
            }
        }));

        return typeResolvers;
    }

}