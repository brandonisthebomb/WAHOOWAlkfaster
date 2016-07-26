package liu.brandon.wahoowalkfaster;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity implements Callback<TranslocResponse>, OnMapReadyCallback {

    private final static String TAG = "MainActivity";

    private String agencyId = "347"; // UVA
    private List<Stop> stops;

    private MapFragment mMapFragment;
    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();

        mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);

    }

    private void loadData() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TranslocAPI.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // Prepare call in Retrofit 2.0
        TranslocAPI translocAPI = retrofit.create(TranslocAPI.class);
        Call<TranslocResponse> call = translocAPI.getStops(
                TranslocAPI.FORMAT,
                TranslocAPI.CALLBACK,
                new String[]{agencyId});
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<TranslocResponse> call, Response<TranslocResponse> response) {
        int code = response.code();
        if (code == 200) {
            TranslocResponse data = response.body();
            List<Stop> stops = data.getStopList();
            Stop mStop = stops.get(0);
            Toast.makeText(this, "Got the stop: " + mStop, Toast.LENGTH_LONG).show();

            for (Stop x : stops) {
                Log.d(TAG, x.toString());
            }

        } else {
            Toast.makeText(this, "Did not work: " + String.valueOf(code), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<TranslocResponse> call, Throwable t) {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Query failed: " + call.toString());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
            .position(new LatLng(0,0))
            .title("Marker"));
    }
}
