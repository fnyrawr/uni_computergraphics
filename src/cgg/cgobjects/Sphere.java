package cgg.cgobjects;

/**
 * @author Florian Kate
 * Date 2021-10-10
 * Student ID: 923081
 * E-Mail: s51541@bht-berlin.de
 */

import cgtools.*;

public class Sphere implements Shape {
    public final Point center;
    public final double radius;
    public final Material material;

    /**
     * Constructor for Sphere class
     * @param center - [Point] center point of sphere
     * @param radius - [double] radius of sphere
     * @param material - [Material] material of the sphere's surface
     */
    public Sphere(Point center, double radius, Material material) {
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    public Hit intersect(Ray ray) {
        Point shiftedX0 = Vector.subtract(ray.getX0(), Vector.direction(center.x, center.y, center.z));
        // a = d^2 | b = 2x0*d | c = x0^2 - r^2
        double a = Vector.dotProduct(ray.getDirection(), ray.getDirection());
        double b = 2 * Vector.dotProduct(shiftedX0, ray.getDirection());
        double c = Vector.dotProduct(shiftedX0, shiftedX0) - (radius*radius);

        // calculate discriminant n to check if hitpoint exists
        double n = b*b - 4*a*c;

        // calculate actual hitpoint
        if(n >= 0) {
            double t0 = (-b + Math.sqrt((b*b) - (4*a*c))) / (2*a);
            double t1 = (-b - Math.sqrt((b*b) - (4*a*c))) / (2*a);

            // if tmin <= t0|t1 <= tmax return hitpoint
            if(ray.isValid(t1)) {
                Point x = Vector.add(ray.getX0(), Vector.multiply(t1, ray.getDirection()));
                // normal vector = (x-center)/radius
                Direction normal = Vector.negate(Vector.divide(Vector.subtract(center, x), radius));
                return new Hit(t1, x, normal, material);
            }
            if(ray.isValid(t0)) {
                Point x = Vector.add(ray.getX0(), Vector.multiply(t0, ray.getDirection()));
                // normal vector = (x-center)/radius
                Direction normal = Vector.negate(Vector.divide(Vector.subtract(center, x), radius));
                return new Hit(t0, x, normal, material);
            }
        }
        return null;
    }
}
