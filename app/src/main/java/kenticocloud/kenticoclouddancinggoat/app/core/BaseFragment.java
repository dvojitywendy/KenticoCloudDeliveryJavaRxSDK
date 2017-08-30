package kenticocloud.kenticoclouddancinggoat.app.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.shared.ScrollChildSwipeRefreshLayout;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 29. 8. 2017.
 */

public abstract class BaseFragment<TPresenter extends IBasePresenter> extends Fragment implements IBaseView<TPresenter>{

    protected View _fragmentView;
    protected View _root;
    protected TPresenter _presenter;
    protected View _noDataView;

    /***
     * Called when refreshing view scroll swipe
     * scroll is not initialized
     */
    protected void onScrollSwipeRefresh(){

    }

    /**
     * Fragment layout
     * @return id of the layout
     */
    protected abstract int getFragmentId();

    /**
     * Fragment view
     * @return id of the layout
     */
    protected abstract int getViewId();

    /**
     * indicates if fragment should init swipe refresh feature
     */
    protected boolean hasScrollSwipeRefresh(){
        return true;
    }

    /**
     * Use to set up custom layout for swipe scroll
     * @return id of the layout
     */
    protected View scrollUpChildView(){
        // by default the fragment layout is used
        return _root.findViewById(getFragmentId());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _root = inflater.inflate(this.getFragmentId(), container, false);

        // Set fragment LL
        _fragmentView = (View) _root.findViewById(this.getViewId());

        setHasOptionsMenu(true);

        // Set up progress indicator
        if (this.hasScrollSwipeRefresh()) {
            final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                    (ScrollChildSwipeRefreshLayout) _root.findViewById(R.id.refresh_layout);
            swipeRefreshLayout.setColorSchemeColors(
                    ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                    ContextCompat.getColor(getActivity(), R.color.colorAccent),
                    ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
            );
            // Set the scrolling view in the custom SwipeRefreshLayout.
            swipeRefreshLayout.setScrollUpChild(scrollUpChildView());

            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    onScrollSwipeRefresh();
                }
            });
        }

        // set no data view
        _noDataView = _root.findViewById(R.id.noDataLL);

        return _root;
    }

    @Override
    public void onResume() {
        super.onResume();
        _presenter.start();
    }

    @Override
    public void setPresenter(TPresenter presenter) {
        _presenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }

        if (this.hasScrollSwipeRefresh()) {
            final SwipeRefreshLayout srl =
                    (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

            // Make sure setRefreshing() is called after the layout is done with everything else.
            srl.post(new Runnable() {
                @Override
                public void run() {
                    srl.setRefreshing(active);
                }
            });
        }
    }

    protected void showSnackbarMessage(String message) {
        View view = getView();

        if (view == null){
            return;
        }

        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoadingError() {
        showSnackbarMessage(getString(R.string.error_loading_data));
        setLoadingIndicator(false);
    }

    @Override
    public void showNoData(boolean show){
        if (show){
            _noDataView.setVisibility(View.VISIBLE);
        }
        else{
            _noDataView.setVisibility(View.GONE);
        }
    }
}
