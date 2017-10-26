package kenticocloud.kenticoclouddancinggoat.app.coffees;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.coffee_detail.CoffeeDetailActivity;
import kenticocloud.kenticoclouddancinggoat.app.core.BaseFragment;
import kenticocloud.kenticoclouddancinggoat.app.shared.CommunicationHub;
import kenticocloud.kenticoclouddancinggoat.data.models.Coffee;

import static com.google.common.base.Preconditions.checkNotNull;

public class CoffeesFragment extends BaseFragment<CoffeesContract.Presenter> implements CoffeesContract.View{

    private CoffeesAdapter adapter;

    public CoffeesFragment() {
        // Requires empty public constructor
    }

    public static CoffeesFragment newInstance() {
        return new CoffeesFragment();
    }

    @Override
    protected int getFragmentId(){
        return R.layout.coffees_frag;
    }

    @Override
    protected int getViewId(){
        return R.id.coffeesLL;
    }

    @Override
    protected boolean hasScrollSwipeRefresh() {
        return true;
    }

    @Override
    protected void onScrollSwipeRefresh() {
        this.presenter.loadCoffees();
    }

    @Override
    protected View scrollUpChildView() {
        return this.root.findViewById(R.id.coffeesLV);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new CoffeesAdapter(new ArrayList<Coffee>(0), coffeeItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Set up articles view
        ListView listView = (ListView) this.root.findViewById(R.id.coffeesLV);
        listView.setAdapter(this.adapter);

        return this.root;
    }

    @Override
    public void showCoffees(List<Coffee> coffees) {
        this.adapter.replaceData(coffees);
        this.fragmentView.setVisibility(View.VISIBLE);
    }

    /**
     * Listener for clicks on items in the ListView.
     */
    CoffeeItemListener coffeeItemListener = new CoffeeItemListener() {
        @Override
        public void onCoffeeClick(Coffee clickedCoffee) {
            Intent coffeeDetailIntent = new Intent(getContext(), CoffeeDetailActivity.class);
            coffeeDetailIntent.putExtra(CommunicationHub.CoffeeCodename.toString(), clickedCoffee.getSystem().getCodename());
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