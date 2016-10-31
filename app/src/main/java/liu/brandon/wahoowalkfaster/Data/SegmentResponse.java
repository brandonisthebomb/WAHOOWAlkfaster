package liu.brandon.wahoowalkfaster.Data;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Brandon on 8/14/16.
 */

public class SegmentResponse {

    @SerializedName("data")
    private List<Segment> segments;
    @SerializedName("rate_limit")
    private int rateLimit;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("api_latest_version")
    private String apiLatestVersion;
    @SerializedName("generated_on")
    private String generatedOn;
    @SerializedName("api_version")
    private String apiVersion;

    public List<Segment> getSegmentList() {
        return this.segments;
    }

    public void setSegmentList(List<Segment> segments) {
        this.segments = segments;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public int getRateLimit() {
        return rateLimit;
    }

    public void setRateLimit(int rateLimit) {
        this.rateLimit = rateLimit;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getApiLatestVersion() {
        return apiLatestVersion;
    }

    public void setApiLatestVersion(String apiLatestVersion) {
        this.apiLatestVersion = apiLatestVersion;
    }

    public String getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(String generatedOn) {
        this.generatedOn = generatedOn;
    }

    public Map<String, Segment> getSegmentMap() {
        Map<String, Segment> map = new HashMap<>();
        List<Segment> segmentList = this.getSegmentList();
        for (int i = 0; i < segmentList.size(); i++) {
            Segment segment = segmentList.get(i);
            String id = segment.getId();
            map.put(id, segment);
        }
        return map;
    }

}
