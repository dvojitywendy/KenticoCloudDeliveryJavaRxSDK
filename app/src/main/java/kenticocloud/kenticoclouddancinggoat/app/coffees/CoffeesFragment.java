package kenticocloud.kenticoclouddancinggoat.app.coffees;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.article_detail.ArticleDetailActivity;
import kenticocloud.kenticoclouddancinggoat.app.coffee_detail.CoffeeDetailActivity;
import kenticocloud.kenticoclouddancinggoat.app.shared.ScrollChildSwipeRefreshLayout;
import kenticocloud.kenticoclouddancinggoat.data.models.Coffee;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public class CoffeesFragment extends Fragment implements CoffeesContract.View{

    private CoffeesContract.Presenter _presenter;

    private CoffeesAdapter _adapter;

    private View _noCoffeesView;
    private LinearLayout _coffeesView;

    public CoffeesFragment() {
        // Requires empty public constructor
    }

    public static CoffeesFragment newInstance() {
        return new CoffeesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _adapter = new CoffeesAdapter(new ArrayList<Coffee>(0), coffeeItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.coffees_frag, container, false);

        // Set up articles view
        ListView listView = (ListView) root.findViewById(R.id.coffeesLV);
        listView.setAdapter(_adapter);
        _coffeesView = (LinearLayout) root.findViewById(R.id.coffeesLL);

        // Set up no articles view
        _noCoffeesView = root.findViewById(R.id.noCoffeesTV);

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
                _presenter.loadCoffees();
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
    public void setPresenter(CoffeesContract.Presenter presenter) {
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
    public void showCoffees(List<Coffee> coffees) {
        _adapter.replaceData(coffees);
        _coffeesView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingError() {

        showMessage(getString(R.string.error_loading_data));
        setLoadingIndicator(false);
    }

    private void showMessage(String message) {
        View view = getView();

        if (view == null){
            return;
        }

        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public void showNoData(boolean show){
        if (show){
            _noCoffeesView.setVisibility(View.VISIBLE);
        }
        else{
            _noCoffeesView.setVisibility(View.GONE);

        }
    }

    /**
     * Listener for clicks on items in the ListView.
     */
    CoffeeItemListener coffeeItemListener = new CoffeeItemListener() {
        @Override
        public void onCoffeeClick(Coffee clickedCoffee) {
            Intent coffeeDetailIntent = new Intent(getContext(), CoffeeDetailActivity.class);
            coffeeDetailIntent.putExtra("coffee_codename", clickedCoffee.getSystem().getCodename());
            startActivity(coffeeDetailIntent);
        }
    };

    private static class CoffeesAdapter extends BaseAdapter {

        private List<Coffee> _coffees;
        private CoffeeItemListener _coffeeItemListener;

        CoffeesAdapter(List<Coffee> coffees, CoffeeItemListener itemListener) {
            setList(coffees);
            _coffeeItemListener = itemListener;
        }

        void replaceData(List<Coffee> coffees) {
            setList(coffees);
            notifyDataSetChanged();
        }

        private void setList(List<Coffee> coffees) {
            _coffees = checkNotNull(coffees);
        }

        @Override
        public int getCount() {
            return _coffees.size();
        }

        @Override
        public Coffee getItem(int i) {
            return _coffees.get(i);
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
                rowView = inflater.inflate(R.layout.coffee_item, viewGroup, false);
            }

            final Coffee coffee = getItem(i);

            // title
            TextView productNameTV = (TextView) rowView.findViewById(R.id.coffeeProductNameTV);
            productNameTV.setText(coffee.getProductName());

            // image
            final ImageView imageIV = (ImageView) rowView.findViewById(R.id.coffeeImageIV);
            Picasso.with(viewGroup.getContext()).load(coffee.getImageUrl()).into(imageIV);


            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _coffeeItemListener.onCoffeeClick(coffee);
                }
            });

            return rowView;
        }

    }

    interface CoffeeItemListener {

        void onCoffeeClick(Coffee clickedCoffee);
    }
}
