package liu.brandon.wahoowalkfaster;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.android.PolyUtil;

import java.util.List;
import java.util.Map;

import liu.brandon.wahoowalkfaster.Data.Location;
import liu.brandon.wahoowalkfaster.Data.Route;
import liu.brandon.wahoowalkfaster.Data.RouteDeserializer;
import liu.brandon.wahoowalkfaster.Data.RouteResponse;
import liu.brandon.wahoowalkfaster.Data.Segment;
import liu.brandon.wahoowalkfaster.Data.SegmentDeserializer;
import liu.brandon.wahoowalkfaster.Data.SegmentResponse;
import liu.brandon.wahoowalkfaster.Data.Stop;
import liu.brandon.wahoowalkfaster.Data.StopResponse;
import liu.brandon.wahoowalkfaster.Data.TranslocAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private final static String TAG = "MainActivity";

    private String agencyId = "347"; // UVA

    private MapFragment mMapFragment;
    private GoogleMap mGoogleMap;

    private Stop mCentralStop;

    private Map<String, Stop> mStops;
    private Map<String, Segment> mSegments;
    private Map<String, Route> mRoutes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();

        mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);

    }

    private void loadData() {
        // Create GSON
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .registerTypeAdapter(SegmentResponse.class, new SegmentDeserializer())
                .registerTypeAdapter(RouteResponse.class, new RouteDeserializer())
                .create();

        // Create Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TranslocAPI.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // Prepare call in Retrofit 2.0
        String[] args = new String[]{agencyId};
        TranslocAPI translocAPI = retrofit.create(TranslocAPI.class);

        // Call get stops
        Call<StopResponse> stopResponseCall = translocAPI.getStops(
                TranslocAPI.FORMAT,
                TranslocAPI.CALLBACK,
                // Convert the args list into a string separated by commas
                TextUtils.join(",", args));
        stopResponseCall.enqueue(new StopCallback());

        // Call get segments
        Call<SegmentResponse> segmentResponseCall = translocAPI.getSegments(
                TranslocAPI.FORMAT,
                TranslocAPI.CALLBACK,
                // Convert the args list into a string separated by commas
                TextUtils.join(",", args));
        segmentResponseCall.enqueue(new SegmentCallback());

        // Call get routes
        Call<RouteResponse> routeResponseCall = translocAPI.getRoutes(
                TranslocAPI.FORMAT,
                TranslocAPI.CALLBACK,
                // Convert the args list into a string separated by commas
                TextUtils.join(",", args));
        routeResponseCall.enqueue(new RouteCallback());

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        // Enable zoom gestures
        UiSettings settings = mGoogleMap.getUiSettings();
        settings.setAllGesturesEnabled(true);

        Log.d(TAG, "Map ready.");
    }

    private class StopCallback implements Callback<StopResponse> {
        @Override
        public void onResponse(Call<StopResponse> call, Response<StopResponse> response) {
            int code = response.code();
            if (code == 200) {
                StopResponse data = response.body();
                List<Stop> stops = data.getStopList();

                // Verify onResponse by printing out Alderman Road at Monroe Hall
                mCentralStop = stops.get(81);
                //Toast.makeText(getApplicationContext(), "Got the stop: " + mStop, Toast.LENGTH_LONG).show();

                // Log print all stops and their indexes, render all stops on the map
                for (int index = 0; index < stops.size(); index++) {
                    Stop stop = stops.get(index);
                    //Log.d(TAG, stop.getId() + ", " + stop.getRoutes().toString());
                    mGoogleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(stop.getLocation().getLatitude(),
                                    stop.getLocation().getLongitude()))
                            .title(stop.getName()));
                }

                // Set the initial location and zoom level of the map
                Location mLocation = mCentralStop.getLocation();
                //Log.d(TAG, mLocation.getLatLng().toString());
                double mLat = mLocation.getLatitude();
                double mLong = mLocation.getLongitude();
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(mLat, mLong),
                        16.0f)
                );

                // Get a map of stops
                mStops = data.getStopMap();

            }
        }

        @Override
        public void onFailure(Call<StopResponse> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "Stop Response Failed", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Query failed: " + call.toString());
        }
    }

    private class SegmentCallback implements Callback<SegmentResponse> {
        @Override
        public void onResponse(Call<SegmentResponse> call, Response<SegmentResponse> response) {
            int code = response.code();
            if (code == 200) {
                SegmentResponse data = response.body();
                List<Segment> segments = data.getSegmentList();

                // Draw the segments on the map
                for (int index = 0; index < segments.size(); index++) {
                    // Log the segments and their polyline values
                    Segment segment = segments.get(index);
                    String polyline = segment.getPolyline();
                    //Log.d(TAG, segment.getId() + " " + polyline);

                    // Decode the polyline and draw it on the map
                    PolylineOptions options = new PolylineOptions()
                            .addAll(PolyUtil.decode(polyline))
                            .color(Color.parseColor("#02306D"));
                    mGoogleMap.addPolyline(options);
                }

                mSegments = data.getSegmentMap();


            }
        }

        @Override
        public void onFailure(Call<SegmentResponse> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "Segment Response Failed", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Segment query failed: " + call.toString());
        }
    }

    private class RouteCallback implements Callback<RouteResponse> {
        @Override
        public void onResponse(Call<RouteResponse> call, Response<RouteResponse> response) {
            int code = response.code();
            if (code == 200) {
                RouteResponse data = response.body();
                List<Route> routes = data.getRouteList();

                // Draw the segments on the map
                for (int index = 0; index < routes.size(); index++) {
                    // Log the segments and their polyline values
                    Route route = routes.get(index);
                    Log.d(TAG, route.getId() + " " + route.getLongName());
                }

                mRoutes = data.getRouteMap();
            }
        }

        @Override
        public void onFailure(Call<RouteResponse> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "Route Response Failed", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Route query failed: " + call.toString());
        }
    }

}
