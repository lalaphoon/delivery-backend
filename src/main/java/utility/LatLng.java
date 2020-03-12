package utility;
/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 * ANY KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */


import org.json.JSONObject;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

/** A place on Earth, represented by a latitude/longitude pair. */
public class LatLng implements  Serializable {

    private static final long serialVersionUID = 1L;

    /** The latitude of this location. */
    public double lat;

    /** The longitude of this location. */
    public double lng;

    /**
     * Constructs a location with a latitude/longitude pair.
     *
     * @param lat The latitude of this location.
     * @param lng The longitude of this location.
     */
    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    /** Serialisation constructor. */
    public LatLng() {}

    @Override
    public String toString() {
        return toUrlValue();
    }

    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject().put("lat", this.lat).put("lng", this.lng);
        return obj;
    }

    public String toUrlValue() {
        // Enforce Locale to English for double to string conversion
        return String.format(Locale.ENGLISH, "%.8f,%.8f", lat, lng);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LatLng latLng = (LatLng) o;
        return Double.compare(latLng.lat, lat) == 0 && Double.compare(latLng.lng, lng) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }

    //reference : https://stackoverflow.com/questions/120283/how-can-i-measure-distance-and-create-a-bounding-box-based-on-two-latitudelongi
    public static double distFrom(LatLng loc1, LatLng loc2) {
        return LatLng.distFrom(loc1.lat, loc1.lng, loc2.lat, loc2.lng);
    }

    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
    }
}