package entity;

import utility.LatLng;

import java.util.List;

public class RouteInfo {
    private String polyline; //encoded set of LatLng
    private List<LatLng> routeData;
    private double distance;
    private double time;

    public void setPolyline(String polyline_points) {this.polyline = polyline_points;}

    public String getPolyline() {
        return this.polyline;
    }

    public RouteInfo (RouteInfoBuilder builder) {
        this.distance = builder.distance;
        this.time = builder.time;
        this.routeData = builder.routeData;
        this.polyline = builder.polyline;
    }

    public static class RouteInfoBuilder {
        private String polyline;
        private List<LatLng> routeData;
        private double distance;
        private double time;

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

        public RouteInfo build() {
            return new RouteInfo(this);
        }
    }
}
