package cgg.cgobjects;

/**
 * @author Florian Kate
 * Date 2021-10-10
 * Student ID: 923081
 * E-Mail: s51541@bht-berlin.de
 */

import cgtools.*;

public class CameraObscura {
    public final double phi;
    public final Point position;
    public final Direction transformation;
    public final double width;
    public final double height;

    /**
     * Constructor for CameraObscura class
     * @param phi - [double] camera's opening angle
     * @param position - [Point] position of camera
     * @param width - [double] width resolution of created image
     * @param height - [double] height resolution of created image
     */
    public CameraObscura(double phi, Point position, double width, double height) {
        this.phi = phi;
        this.position = position;
        this.transformation = Vector.subtract(Vector.point(0, 0, 0), position);
        this.width = width;
        this.height = height;
    }

    /**
     * Generating a new ray
     * @param x - [double] horizontal projection pixel
     * @param y - [double] vertical projection pixel
     */
    public Ray generateRay(double x, double y) {
        double dx = x-(width/2);
        double dy = -(y-(height/2));
        double dz = -((width/2) / (Math.tan(phi/2)));
        return new Ray(position, Vector.direction(dx, dy, dz), 0,Double.POSITIVE_INFINITY);
        // return new Ray(position, transformDirection(Vector.direction(dx, dy, dz), transformation), 0,Double.POSITIVE_INFINITY);
    }

    /**
     * transforming day direction
     * @param d - [Direction] direction of ray
     * @param t - [Direction] transformation direction
     */
    private Direction transformDirection(Direction d, Direction t) {
        Matrix rMat = Matrix.translation(t);
        return Matrix.multiply(rMat, d);
    }
}
