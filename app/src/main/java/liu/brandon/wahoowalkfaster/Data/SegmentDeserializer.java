package liu.brandon.wahoowalkfaster.Data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Brandon on 8/14/16.
 */

public class SegmentDeserializer implements JsonDeserializer<SegmentResponse> {

    private final static String TAG = "SegmentDeserializer";

    @Override
    public SegmentResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final JsonElement jsonRateLimit = jsonObject.getAsJsonPrimitive("rate_limit");
        final int rateLimit = jsonRateLimit.getAsInt();

        final JsonElement jsonExpiresIn = jsonObject.getAsJsonPrimitive("expires_in");
        final int expiresIn = jsonExpiresIn.getAsInt();

        final JsonElement jsonApiLatestVersion = jsonObject.getAsJsonPrimitive("api_latest_version");
        final String apiLatestVersion = jsonApiLatestVersion.getAsString();

        final JsonElement jsonGeneratedOn = jsonObject.getAsJsonPrimitive("generated_on");
        final String generatedOn = jsonGeneratedOn.getAsString();

        final JsonElement jsonApiVersion = jsonObject.getAsJsonPrimitive("api_version");
        final String apiVersion = jsonApiVersion.getAsString();

        final JsonObject jsonData = jsonObject.getAsJsonObject("data");
        List<Segment> segments = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : jsonData.entrySet()) {
            //Log.d(TAG, entry.getValue().getAsString());
            Segment segment = new Segment();
            segment.setId(entry.getKey());
            segment.setPolyline(entry.getValue().getAsString());
            segments.add(segment);
        }

        final SegmentResponse response = new SegmentResponse();
        response.setRateLimit(rateLimit);
        response.setExpiresIn(expiresIn);
        response.setApiLatestVersion(apiLatestVersion);
        response.setGeneratedOn(generatedOn);
        response.setApiVersion(apiVersion);
        response.setSegmentList(segments);
        return response;
    }
}
