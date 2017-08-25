package kenticocloud.kenticoclouddancinggoat.app.cafe_detail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

import kenticocloud.kenticoclouddancinggoat.R;
import kenticocloud.kenticoclouddancinggoat.app.shared.ScrollChildSwipeRefreshLayout;
import kenticocloud.kenticoclouddancinggoat.data.models.Cafe;
import kenticocloud.kenticoclouddancinggoat.util.Location.LocationHelper;
import kenticocloud.kenticoclouddancinggoat.util.Location.LocationInfo;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public class CafeDetailFragment extends Fragment implements CafeDetailContract.View, OnMapReadyCallback  {

    private CafeDetailContract.Presenter _presenter;
    private GoogleMap _map;
    private LinearLayout _cafeDetailView;

    public CafeDetailFragment() {
        // Requires empty public constructor
    }

    public static CafeDetailFragment newInstance() {
        return new CafeDetailFragment();
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
        View root = inflater.inflate(R.layout.cafe_detail_frag, container, false);

        // Set cafe LL
        _cafeDetailView = (LinearLayout) root.findViewById(R.id.cafeDetailRL);

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
        swipeRefreshLayout.setScrollUpChild(_cafeDetailView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                _presenter.loadCafe();
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
    public void setPresenter(CafeDetailContract.Presenter presenter) {
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
    public void showCafe(Cafe cafe) {
        View view = getView();

        if (view == null) {
            return;
        }

        // Update activity title
        getActivity().setTitle(cafe.getCity());

        // image
        final ImageView teaserIV = (ImageView) view.findViewById(R.id.cafeDetailTeaserIV);
        Picasso.with(view.getContext()).load(cafe.getPhotoUrl()).into(teaserIV);

        TextView streetLineTV = (TextView) view.findViewById(R.id.cafeStreetLineTV);
        streetLineTV.setText(cafe.getStreet());

        TextView cityLineTV = (TextView) view.findViewById(R.id.cafeCityLineTV);
        cityLineTV.setText(cafe.getZipCode() + ", " + cafe.getCity());

        TextView cafeCountryLineTV = (TextView) view.findViewById(R.id.cafeCountryLineTV);
        if (TextUtils.isEmpty(cafe.getState())) {
            cafeCountryLineTV.setText(cafe.getCountry());
        } else {
            cafeCountryLineTV.setText(cafe.getCountry() + ", " + cafe.getState());
        }

        TextView cafePhoneTV = (TextView) view.findViewById(R.id.cafeDetailPhoneTV);
        cafePhoneTV.setText(cafe.getPhone());

        TextView cafeEmailTV = (TextView) view.findViewById(R.id.cafeDetailEmailTV);
        cafeEmailTV.setText(cafe.getEmail());

        // init marker
        LocationInfo cafeLocation = null;
        try {
            cafeLocation = LocationHelper.getLocationFromAddress(getContext(), cafe.getStreet(), cafe.getCity(), cafe.getCountry());

            if (cafeLocation != null){
                LatLng cafeLatLng = new LatLng(cafeLocation.getLattitude(), cafeLocation.getLongtitude());
                _map.addMarker(new MarkerOptions().position(cafeLatLng).title("Cafe"));
                _map.moveCamera(CameraUpdateFactory.newLatLngZoom(cafeLatLng, 12));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLoadingIndicator(false);
        _cafeDetailView.setVisibility(View.VISIBLE);
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
