package controller;

import model.Point;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;


@ApplicationScoped
public class InputValidator {
    public static boolean isPointValid(Point point) {
        return isXValid(point.getX()) &&
                isYValid(point.getY()) &&
                isRValid(point.getR())
                ;
    }

    public static boolean isYValid(double y) {
        return y >= -3 &&
                y <= 5;
    }

    public static boolean isXValid(double x) {
        return x >= -5 &&
                x <= 5;
    }

    public static boolean isRValid(double r) {
        return (!(r < 2)) && (!(r > 5));
    }
}

