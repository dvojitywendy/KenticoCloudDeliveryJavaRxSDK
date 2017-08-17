package kenticocloud.kenticoclouddancinggoat.data.source.articles;

import android.support.annotation.NonNull;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.source.IMultipleCallback;
import kenticocloud.kenticoclouddancinggoat.data.source.ISingleCallback;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public interface ArticlesDataSource {

    interface LoadArticlesCallback extends IMultipleCallback<Article> {
    }

    interface GetArticleCallback extends ISingleCallback<Article> {
    }

    void getArticles(@NonNull LoadArticlesCallback callback);

    void getArticle(@NonNull String articleId, @NonNull GetArticleCallback callback);
}
