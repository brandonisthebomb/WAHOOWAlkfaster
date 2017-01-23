package liu.brandon.wahoowalkfaster.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Brandon on 7/12/16.
 */
public interface TranslocAPI {

    String ENDPOINT = "https://transloc-api-1-2.p.mashape.com";
    String FORMAT = "json";
    String CALLBACK = "call";

    @Headers({
            "X-Mashape-Authorization: CwYowCwhaVmshCbsGzvBFhXBXjprp1FdIQkjsnYrOa6UKkiZBF",
            "Accept: application/json"
    })
    @GET("/stops.{format}")
    Call<StopResponse> getStops(
            @Path("format") String format,
            @Query("callback") String callback,
            @Query("agencies") String agencies);


    @Headers({
            "X-Mashape-Authorization: CwYowCwhaVmshCbsGzvBFhXBXjprp1FdIQkjsnYrOa6UKkiZBF",
            "Accept: application/json"
    })
    @GET("/segments.{format}")
    Call<SegmentResponse> getSegments(
            @Path("format") String format,
            @Query("callback") String callback,
            @Query("agencies") String agencies);


    @Headers({
            "X-Mashape-Authorization: CwYowCwhaVmshCbsGzvBFhXBXjprp1FdIQkjsnYrOa6UKkiZBF",
            "Accept: application/json"
    })
    @GET("/routes.{format}")
    Call<RouteResponse> getRoutes(
            @Path("format") String format,
            @Query("callback") String callback,
            @Query("agencies") String agencies);


    @Headers({
            "X-Mashape-Authorization: CwYowCwhaVmshCbsGzvBFhXBXjprp1FdIQkjsnYrOa6UKkiZBF",
            "Accept: application/json"
    })
    @GET("/vehicles.{format}")
    Call<VehicleResponse> getVehicles(
            @Path("format") String format,
            @Query("callback") String callback,
            @Query("agencies") String agencies);




}
