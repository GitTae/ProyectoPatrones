package com.laca.entity.RouteC;

import java.util.HashMap;

public class RouteManager {
    private HashMap<String, Route> routes = new HashMap<>();

    public void addRoute(String key, Route route) {
        routes.put(key, route);
    }

    public Route getRoute(String key) {
        Route route = routes.get(key);
        return (Route) route.clone();
    }

    @Override
    public String toString() {
        return "RouteManager{" +
                "routes=" + routes +
                '}';
    }
}