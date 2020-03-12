package rpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import entity.Location;
import entity.Route;
import entity.RouteInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//https://maps.googleapis.com/maps/api/directions/json?origin=37.8266636,-122.4230122&destination=37.7992627,-122.3976732&key=AIzaSyCQd2_s804T25-Xtvm5PndruimLb6pEuY4

public class GoogleMapClient {
    private static final String HOST = "https://maps.googleapis.com";
    private static final String PATH = "/maps/api/directions/json";
    private static final String API_KEY = "AIzaSyCQd2_s804T25-Xtvm5PndruimLb6pEuY4";

    public Route findPath(Location origin, Location destination ) {
        String query = String.format("origin=%s,%s&destination=%s,%s&key=%s", origin.getLat(), origin.getLon(), destination.getLat(), destination.getLon(), API_KEY);
        String url = HOST + PATH + "?" + query;

        StringBuilder responseBody = new StringBuilder();
        try {

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            System.out.println("Sending request to url:" + url);
            System.out.println("Response code: " + responseCode);

            if (responseCode != 200) {
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(responseBody.toString());
            if (!obj.isNull("routes")) {
                JSONArray embedded = obj.getJSONArray("routes");
                JSONObject legs = embedded.getJSONObject(0).getJSONArray("legs").getJSONObject(0);
                Route finalRoute = new Route.RouteBuilder()
                        .setFromLoc(new Location(legs.getJSONObject("start_location")))
                        .setToLoc(new Location(legs.getJSONObject("end_location")))
                        .build();



                JSONArray steps = legs.getJSONArray("steps");

               List<RouteInfo> routes = new ArrayList<RouteInfo>();
                //Merge it to Route
                for (int i = 0; i < steps.length(); i++){
                    JSONObject tmp = steps.getJSONObject(i);
                    RouteInfo tmpri = new RouteInfo.RouteInfoBuilder().setDistance(tmp.getJSONObject("distance").getDouble("value"))
                            .setTime(tmp.getJSONObject("duration").getDouble("value"))
                            .setPolyline(tmp.getJSONObject("polyline").getString("points"))
                            .build();
                    routes.add(tmpri);
                }

                //add routes into route
                finalRoute.setRoutes(routes);


                return finalRoute;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String [] args) {
        GoogleMapClient client = new GoogleMapClient();
        Route routes = client.findPath(
                new Location(37.8266636,-122.4230122, "A"),
                new Location(37.7992627,-122.3976732, "B")
        );

        try {
            List<RouteInfo> routelist = routes.getRoutes();
            for (int i  = 0; i < routelist.size(); i++) {
                System.out.println(routelist.get(i).getRouteData().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
