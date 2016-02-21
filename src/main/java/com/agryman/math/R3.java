package com.agryman.math;

import java.util.Random;

/**
 * Created by arthurryman on 2016-02-20.
 * <p>
 * Real, 3-dimensional vector space.
 */
public class R3 {

    public static final R3 ZERO = new R3(0, 0, 0);
    public static final R3 E1 = new R3(1, 0, 0);
    public static final R3 E2 = new R3(0, 1, 0);
    public static final R3 E3 = new R3(0, 0, 1);
    public static final R3[] BASIS = {E1, E2, E3};


    private double x, y, z;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public R3(double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public R3() {
        this(0, 0, 0);
    }

    public R3(R3 v) {
        this(v.x, v.y, v.z);
    }

    public R3 scale(double c) {

        x *= c;
        y *= c;
        z *= c;

        return this;
    }

    public R3 translate(R3 v) {

        x += v.x;
        y += v.y;
        z += v.z;

        return this;
    }

    public R3 mul(double c) {

        R3 w = new R3(this);

        return w.scale(c);
    }

    public R3 add(R3 v) {

        R3 w = new R3(this);

        return w.translate(v);
    }

    public double dot(R3 v) {

        return x * v.x + y * v.y + z * v.z;
    }

    public R3 cross(R3 v) {

        return new R3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }

    public String toString() {

        return "(" + x + ", " + y + ", " + z + ")";
    }

    public boolean equals(R3 v) {

        // TODO: allow for round-off errors
        return x == v.x && y == v.y && z == v.z;
    }

    public static void main(String[] args) {
        tests();
    }


    public static void tests() {

        System.out.println("R3: 3-dimensional real vector space");
        System.out.println("zero = " + ZERO);
        System.out.println("standard basis:");
        for (R3 v : BASIS) {
            System.out.println(v);
        }
        System.out.println("2*e1 = " + E1.mul(2));
        System.out.println("e2 + e3 = " + E2.add(E3));

        // dot and cross products
        for (R3 a : BASIS) {
            for (R3 b : BASIS) {
                System.out.println("a = " + a + "; b = " + b + "; a . b = " + a.dot(b) + "; a x b = " + a.cross(b));
            }
        }

        // verify that dot is bilinear, symmetric, and positive definite
        // first test on the basis, then add some randomly generated vectors

        System.out.println("Testing that dot is symmetric: a.b = b.a");
        for (R3 a : BASIS) {
            System.out.println("a = " + a);
            for (R3 b : BASIS) {
                boolean symmetric = a.dot(b) == b.dot(a);
                System.out.println("  b = " + b + "; symmetric: " + symmetric);
            }
        }

        Random random = new Random(new java.util.Random(), 1000000);
        for (int i = 0; i < 3; i++) {
            R3 a = random.next();
            boolean positiveDefinite = a.dot(a) > 0;
            System.out.println("a = " + a + "; positive definite: " + positiveDefinite);
            for (int j = 0; j < 3; j++) {
                R3 b = random.next();
                boolean symmetric = a.dot(b) == b.dot(a);
                System.out.println("  b = " + b + "; symmetric: " + symmetric);
            }
        }

        // verify the cross is bilinear, anti-symmetric, and Lie
    }

    /**
     * Generates random vectors.
     */
    public static class Random {

        // uniform distribution on the interval [0,1)
        private java.util.Random random;

        // scales coordinates to rangle [-scale, scale)
        private double scale;

        public Random(java.util.Random random, double scale) {

            if (scale <= 0) throw new IllegalArgumentException("scale must be positive: " + scale);

            this.random = random;
            this.scale = scale;
        }

        public R3 next() {

            double x = nextCoordinate();
            double y = nextCoordinate();
            double z = nextCoordinate();

            return new R3(x, y, z);
        }

        public double nextCoordinate() {

            return (2 * random.nextDouble() - 1) * scale;
        }

    }
}
