package liu.brandon.wahoowalkfaster.Data;

import android.graphics.Color;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Brandon on 8/14/16.
 */

public class RouteDeserializer implements JsonDeserializer<RouteResponse> {

    private final static String TAG = "RouteDeserializer";

    @Override
    public RouteResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
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
        List<Route> routes = new ArrayList<>();
        // Agency
        for (Map.Entry<String, JsonElement> entry : jsonData.entrySet()) {
            Log.d(TAG, entry.toString());

            // Route
            for (JsonElement element : entry.getValue().getAsJsonArray()) {
                JsonObject jsonRoute = element.getAsJsonObject();

                // ID
                String id = jsonRoute.getAsJsonPrimitive("route_id").getAsString();

                // Segments
                JsonArray jsonSegments = jsonRoute.getAsJsonArray("segments");
                Map<String, String> segments = new HashMap<>();
                for (JsonElement segmentElement : jsonSegments) {
                    JsonArray segmentTuple = segmentElement.getAsJsonArray();
                    segments.put(segmentTuple.get(0).getAsString(), segmentTuple.get(1).getAsString());
                }
                //Log.d(TAG, segments.toString());

                // Stops
                JsonArray jsonStops = jsonRoute.getAsJsonArray("stops");
                List<String> stopsArrayList = new ArrayList<>();
                for (JsonElement stopElement : jsonStops) {
                    stopsArrayList.add(stopElement.getAsString());
                }
                String[] stops = stopsArrayList.toArray(new String[0]);

                //Log.d(TAG, stops.toString());

                // isActive
                boolean isActive = jsonRoute.getAsJsonPrimitive("is_active").getAsBoolean();
                //Log.d(TAG, String.valueOf(isActive));

                // Agency ID
                int agencyId = jsonRoute.getAsJsonPrimitive("agency_id").getAsInt();
                //Log.d(TAG, String.valueOf(agencyId));

                // Name
                String longName = jsonRoute.getAsJsonPrimitive("long_name").getAsString();
                //Log.d(TAG, longName);

                // Type
                String type = jsonRoute.getAsJsonPrimitive("type").getAsString();
                //Log.d(TAG, type);

                // Color
                int color = Color.parseColor('#' + jsonRoute.getAsJsonPrimitive("color").getAsString());
                //Log.d(TAG, String.valueOf(color));

                Log.d(TAG, longName + ' ' + Arrays.toString(stops));
                Route route = new Route();
                route.setActive(isActive);
                route.setAgencyId(agencyId);
                route.setColor(color);
                route.setId(id);
                route.setLongName(longName);
                route.setSegments(segments);
                route.setStops(stops);
                route.setType(type);
                routes.add(route);
            }
        }

        // Build the final route response
        final RouteResponse response = new RouteResponse();
        response.setRateLimit(rateLimit);
        response.setExpiresIn(expiresIn);
        response.setApiLatestVersion(apiLatestVersion);
        response.setGeneratedOn(generatedOn);
        response.setApiVersion(apiVersion);
        response.setRouteList(routes);
        return response;
    }
}
