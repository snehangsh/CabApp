package com.project.cab.cabApp.services;


import org.locationtech.jts.geom.Point;

public interface DistanceService {
    double calculateDistance(Point src, Point dest); //to calculate the distance between source and destination
}
