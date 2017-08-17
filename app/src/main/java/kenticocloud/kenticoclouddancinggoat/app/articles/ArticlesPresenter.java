package kenticocloud.kenticoclouddancinggoat.app.articles;

import android.support.annotation.NonNull;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesDataSource;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public class ArticlesPresenter implements ArticlesContract.Presenter {

    private final ArticlesRepository _repository;

    private final ArticlesContract.View _view;

    public ArticlesPresenter(@NonNull ArticlesRepository repository, @NonNull ArticlesContract.View view) {
        _repository = checkNotNull(repository, "repository cannot be null");
        _view = checkNotNull(view, "view cannot be null!");

        _view.setPresenter(this);
    }

    @Override
    public void start() {
        loadArticles();
    }

    @Override
    public void loadArticles() {
        _view.setLoadingIndicator(true);

        _repository.getArticles(new ArticlesDataSource.LoadArticlesCallback() {
            @Override
            public void onItemsLoaded(List<Article> articles) {
                _view.setLoadingIndicator(false);
                _view.showArticles(articles);
            }

            @Override
            public void onDataNotAvailable() {
                _view.showLoadingTasksError();
            }

            @Override
            public void onError() {

            }
        });
    }
}
