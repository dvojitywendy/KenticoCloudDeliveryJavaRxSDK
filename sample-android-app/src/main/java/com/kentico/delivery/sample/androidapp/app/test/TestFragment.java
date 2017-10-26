package com.kentico.delivery.sample.androidapp.app.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.kentico.delivery.sample.androidapp.R;
import com.kentico.delivery.sample.androidapp.app.core.BaseFragment;

public class TestFragment extends BaseFragment<TestContract.Presenter> implements TestContract.View{

    public TestFragment() {
        // Requires empty public constructor
    }

    @Override
    protected int getFragmentId(){
        return R.layout.test_frag;
    }

    @Override
    protected int getViewId(){
        return R.id.testLL;
    }

    @Override
    protected boolean hasScrollSwipeRefresh() {
        return true;
    }

    @Override
    protected void onScrollSwipeRefresh() {
        this.presenter.loadData();
    }

    @Override
    protected View scrollUpChildView() {
        return this.root.findViewById(R.id.articlesLV);
    }

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return this.root;
    }

    public void showData(List<?> data) {
        fragmentView.setVisibility(View.VISIBLE);
    }

}
