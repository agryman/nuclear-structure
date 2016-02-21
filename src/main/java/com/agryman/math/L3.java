package com.agryman.math;

/**
 * Created by arthurryman on 2016-02-20.
 *
 * Linear transformations of R3.
 */
public class L3 {

    // a linear transformation L is defined by its action on the standard basis
    // L(e1) = f1
    // L(e2) = f2
    // L(e3) = f3
    private R3 f1, f2, f3;

    public L3(R3 f1, R3 f2, R3 f3) {
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
    }

    /**
     * Maps an input vector v to a new vector w = L(v)
     *
     * @param v the input vector
     * @return a new vector L(v)
     */
    public R3 map(R3 v) {

        // v = x*e1 + y*e2 + z*e3
        // w = L(v) = x*L(e1) + y*L(e2) + z*L(e3)
        //   = x*f1 + y*f2 + z*f3

        R3 w = f1.mul(v.getX());
        w.translate(f2.mul(v.getY()));
        w.translate(f3.mul(v.getZ()));

        return w;
    }
}
