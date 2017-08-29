package kenticocloud.kenticoclouddancinggoat.app.coffee_detail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.shared.ScrollChildSwipeRefreshLayout;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;
import kenticocloud.kenticoclouddancinggoat.data.models.Coffee;
import kenticocloud.kenticoclouddancinggoat.util.Location.LocationHelper;
import kenticocloud.kenticoclouddancinggoat.util.Location.LocationInfo;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public class CoffeeDetailFragment extends Fragment implements CoffeeDetailContract.View, OnMapReadyCallback  {

    private CoffeeDetailContract.Presenter _presenter;
    private LinearLayout _coffeeDetailView;
    private GoogleMap _map;

    public CoffeeDetailFragment() {
        // Requires empty public constructor
    }

    public static CoffeeDetailFragment newInstance() {
        return new CoffeeDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showNoData(boolean show) {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.coffee_detail_frag, container, false);

        // Set coffee LL
        _coffeeDetailView = (LinearLayout) root.findViewById(R.id.coffeeDetailLL);

        setHasOptionsMenu(true);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(_coffeeDetailView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                _presenter.loadCoffee();
            }
        });

        // init map
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        _presenter.start();
    }

    @Override
    public void setPresenter(CoffeeDetailContract.Presenter presenter) {
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void showCoffee(Coffee coffee) {
        View view = getView();

        if (view == null) {
            return;
        }

        // Update activity title
        getActivity().setTitle(coffee.getProductName());

        // image
        final ImageView teaserIV = (ImageView) view.findViewById(R.id.coffeeDetailTeaserIV);
        Picasso.with(view.getContext()).load(coffee.getImageUrl()).into(teaserIV);

        TextView teaserLineTV = (TextView) view.findViewById(R.id.coffeeDetailTeaserLineTV);
        teaserLineTV.setText(coffee.getCountry());

        TextView originsLineTV = (TextView) view.findViewById(R.id.coffeeDetailOriginsLineTV);
        String originsText = "Experience coffee from the '" + coffee.getFarm() + "' farm, made in '" + coffee.getAltitude() + "' altitude";
        originsLineTV.setText(originsText);

        TextView longDescriptionTV = (TextView) view.findViewById(R.id.coffeeDetailDescriptionTV);
        longDescriptionTV.setText(Html.fromHtml(coffee.getLongDescription(), Html.FROM_HTML_MODE_COMPACT));

        TextView varietyLineTV = (TextView) view.findViewById(R.id.coffeeDetailVarietyLineTV);
        String varietyText = "Available in: " + coffee.getVariety();
        varietyLineTV.setText(varietyText);

        // init marker
        LocationInfo cafeLocation = null;
        try {
            cafeLocation = LocationHelper.getLocationFromAddress(getContext(), "", " ", coffee.getCountry());

            if (cafeLocation != null){
                LatLng cafeLatLng = new LatLng(cafeLocation.getLattitude(), cafeLocation.getLongtitude());
                _map.addMarker(new MarkerOptions().position(cafeLatLng).title("Coffee origin"));
                _map.moveCamera(CameraUpdateFactory.newLatLngZoom(cafeLatLng, 3));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLoadingIndicator(false);
        _coffeeDetailView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void showLoadingError() {
        showMessage(getString(R.string.error_loading_data));
    }

    private void showMessage(String message) {
        View view = getView();

        if (view == null){
            return;
        }

        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        _map = googleMap;

        _map.getUiSettings().setZoomGesturesEnabled(true);
        // scroll not enabled because it does not play nice with scroll that is required
        // it also causes issues when map takes full screen
        _map.getUiSettings().setScrollGesturesEnabled(false);

        _map.getUiSettings().setMapToolbarEnabled(true);
    }
}
