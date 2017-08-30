package kenticocloud.kenticoclouddancinggoat.app.article_detail;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesDataSource;
import kenticocloud.kenticoclouddancinggoat.data.source.articles.ArticlesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

class ArticleDetailPresenter implements ArticleDetailContract.Presenter {

    private final String _articleCodename;
    private final ArticlesRepository _repository;

    private final ArticleDetailContract.View _view;

    ArticleDetailPresenter(@NonNull ArticlesRepository repository, @NonNull ArticleDetailContract.View view, @NonNull String articleCodename) {
        _repository = checkNotNull(repository, "repository cannot be null");
        _view = checkNotNull(view, "view cannot be null!");
        _view.setPresenter(this);
        _articleCodename = articleCodename;
    }

    @Override
    public void start() {
        loadArticle();
    }

    @Override
    public void loadArticle() {
        _view.setLoadingIndicator(true);

        _repository.getArticle(_articleCodename, new ArticlesDataSource.LoadArticleCallback() {

            @Override
            public void onItemLoaded(Article item) {
                _view.showArticle(item);
            }

            @Override
            public void onDataNotAvailable() {
                _view.showLoadingError();
            }

            @Override
            public void onError(Throwable e) {
                _view.showLoadingError();
            }
        });
    }
}
