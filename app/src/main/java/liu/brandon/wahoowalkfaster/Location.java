package liu.brandon.wahoowalkfaster;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Brandon on 7/22/16.
 */
public class Location {

    @SerializedName("lat")
    private Double latitude;
    @SerializedName("lng")
    private Double longitude;

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
}
