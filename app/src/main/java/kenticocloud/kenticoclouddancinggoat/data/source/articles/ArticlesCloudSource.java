package kenticocloud.kenticoclouddancinggoat.data.source.articles;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public class ArticlesCloudSource implements ArticlesDataSource {

    private static ArticlesCloudSource INSTANCE;

    // Prevent direct instantiation.
    private ArticlesCloudSource(@NonNull Context context) {
        checkNotNull(context);
    }

    public static ArticlesCloudSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ArticlesCloudSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getArticles(@NonNull LoadArticlesCallback callback) {
        List<Article> articles = new ArrayList<Article>();

        articles.add(new Article("article 1"));
        articles.add(new Article("article 2"));
        articles.add(new Article("article 3"));
        articles.add(new Article("article 4"));

        callback.onArticlesLoaded(articles);

        if (articles.size() == 0){
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void getArticle(@NonNull String articleId, @NonNull GetArticleCallback callback) {
        Article article = new Article("Single article 1");

        callback.onArticleLoaded(article);
    }
}
