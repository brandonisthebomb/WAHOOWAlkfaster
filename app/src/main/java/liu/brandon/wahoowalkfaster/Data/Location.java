package liu.brandon.wahoowalkfaster.Data;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Brandon on 7/22/16.
 */
public class Location {

    @SerializedName("lat")
    private Double latitude;
    @SerializedName("lng")
    private Double longitude;

    public Location (Double lat, Double lng) {
        latitude = lat;
        longitude = lng;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }

    @Override
    public String toString() {
        return "Location latitude: " + latitude + ", longitude: " + longitude;
    }
}
