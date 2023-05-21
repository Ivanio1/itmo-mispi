package mbeans;


public class Square implements SquareMBean {
    private double area=0;
    private double r=0;


    @Override
    public double calculateArea() {
        double rectangle = r * (r / 2);
        double triangle = (1.0 / 2.0) * r * r;
        double circle = (Math.PI * Math.pow(r, 2)) / 4;
        this.area=(rectangle + triangle + circle);
        return rectangle + triangle + circle;
    }

    @Override
    public void clearArea() {
        area=0;
    }

    @Override
    public void clearRadius() {
        r=0;
    }

    @Override
    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public double getRadius() {
        return r;
    }

    @Override
    public void setRadius(double R) {
        this.r = R;
        calculateArea();
    }
}
