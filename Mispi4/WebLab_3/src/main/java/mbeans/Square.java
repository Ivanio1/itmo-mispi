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
    public double getArea() {
        return area;
    }

    @Override
    public double getR() {
        return r;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setR(double R) {
        this.r = R;
        calculateArea();
    }
}
