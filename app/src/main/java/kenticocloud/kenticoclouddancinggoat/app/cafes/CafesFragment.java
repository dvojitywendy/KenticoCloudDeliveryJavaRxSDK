package kenticocloud.kenticoclouddancinggoat.app.cafes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.shared.ScrollChildSwipeRefreshLayout;
import kenticocloud.kenticoclouddancinggoat.data.models.Article;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public class CafesFragment extends Fragment implements CafesContract.View{

    private CafesContract.Presenter _presenter;

    private CafesAdapter _adapter;

    private View _noCafesView;
    private LinearLayout _cafesView;

    public CafesFragment() {
        // Requires empty public constructor
    }

    public static CafesFragment newInstance() {
        return new CafesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _adapter = new CafesAdapter(new ArrayList<Cafe>(0), cafeItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.cafes_frag, container, false);

        // Set up tasks view
        ListView listView = (ListView) root.findViewById(R.id.cafesLV);
        listView.setAdapter(_adapter);
        _cafesView = (LinearLayout) root.findViewById(R.id.cafesLL);

        // Set up  no cafes view
        _noCafesView = root.findViewById(R.id.noCafesTV);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(listView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                _presenter.loadCafes();
            }
        });

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        _presenter.start();
    }

    @Override
    public void setPresenter(CafesContract.Presenter presenter) {
        _presenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }

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

    @Override
    public void showCafes(List<Cafe> cafes) {
        _adapter.replaceData(cafes);

        _cafesView.setVisibility(View.VISIBLE);
        _noCafesView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingTasksError() {
        showMessage("Error loading data");
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Listener for clicks on cafes in the ListView.
     */
    CafeItemListener cafeItemListener = new CafeItemListener() {
        @Override
        public void onCafeClick(Cafe clickedCafe) {
            // to do some action when item is clicked
        }
    };

    private static class CafesAdapter extends BaseAdapter {

        private List<Cafe> _cafes;
        private CafeItemListener _cafeItemListener;

        CafesAdapter(List<Cafe> cafes, CafeItemListener itemListener) {
            setList(cafes);
            _cafeItemListener = itemListener;
        }

        void replaceData(List<Cafe> cafes) {
            setList(cafes);
            notifyDataSetChanged();
        }

        private void setList(List<Cafe> cafes) {
            _cafes = checkNotNull(cafes);
        }

        @Override
        public int getCount() {
            return _cafes.size();
        }

        @Override
        public Cafe getItem(int i) {
            return _cafes.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = view;
            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                rowView = inflater.inflate(R.layout.cafe_item, viewGroup, false);
            }

            final Cafe cafe = getItem(i);

            TextView titleTV = (TextView) rowView.findViewById(R.id.cafeTitleTV);
            titleTV.setText(cafe.getTitle());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _cafeItemListener.onCafeClick(cafe);
                }
            });

            return rowView;
        }
    }

    interface CafeItemListener {

        void onCafeClick(Cafe clickedCafe);
    }
}
