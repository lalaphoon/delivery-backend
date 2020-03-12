package entity;

import org.json.JSONArray;
import utility.LatLng;
import utility.PolylineEncoding;

import java.util.List;

public class RouteInfo {
    private String polyline; //encoded set of LatLng
    private List<LatLng> routeData;
    private double distance;
    private double time;
    private String type;
    private String routeID;
    private double price;

    public void setPolyline(String polyline_points) {this.polyline = polyline_points;}

    public String getEncodedPolyline() {
        return this.polyline;
    }

    public String getRouteID() {
        return this.routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    public double getPrice() {
        return this.price;
    }

    public String getDeliverType() {
        return this.type;
    }
    public double getDistance() {
        return this.distance;
    }

    public List<LatLng> getRouteData() {
        return PolylineEncoding.decode(this.polyline);
    }

    public JSONArray getRouteDataJSONArray() {
        JSONArray arr = new JSONArray();
        List<LatLng> data = getRouteData();
        for(LatLng i :  data){
            arr.put(i.toJSONObject());
        }
        return arr;
    }


    public RouteInfo (RouteInfoBuilder builder) {
        this.distance = builder.distance;
        this.time = builder.time;
        this.routeData = builder.routeData;
        this.polyline = builder.polyline;
        this.type = builder.type;
        this.price = RouteJSONBuilder.getPriceByDistanceAndType(this.distance,this.type);
        this.routeID = null;
    }

    public static class RouteInfoBuilder {
        private String polyline;
        private List<LatLng> routeData;
        private double distance;
        private double time;
        private String type;

        public RouteInfoBuilder setPolyline(String polyline) {
            this.polyline = polyline;
            return this;
        }

        public RouteInfoBuilder setRouteData(List<LatLng> routeData) {
            this.routeData = routeData;
            return this;
        }

        public RouteInfoBuilder setDistance(double distance) {
            this.distance = distance;
            return this;
        }

        public RouteInfoBuilder setTime(double time) {
            this.time = time;
            return this;
        }

        public RouteInfoBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public RouteInfo build() {
            return new RouteInfo(this);
        }
    }
}
