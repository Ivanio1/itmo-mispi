package controller;

import model.Point;

public class AreaResultChecker {
	private AreaResultChecker() {

	}
	
	public static boolean isPointInArea(Point point) {
		return isCoordinatesInArea(point.getX(), point.getY(), point.getR());
	}
	
	public static boolean isCoordinatesInArea(double x, double y, double r) {
		return inTraingle(x, y, r) ||
                inRect(x, y, r) ||
                inCircle(x, y, r)
				;
	}
	
	private static boolean inTraingle(double x, double y, double r) {
		return x <= 0 &&
				y <= 0 &&
				r>=x*(-1)+y*(-1)
				;
	}
	
	private static boolean inRect(double x, double y, double r) {
		return x <= 0 &&
				y >= 0 &&
				x>=-r/2&&
				y<=r;
	}
	
	private static boolean inCircle(double x, double y, double r) {
		return x >= 0 &&
                y >= 0 &&
                x * x + y * y <= r * r
				;
	}
}
