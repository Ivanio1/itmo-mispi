package mbeans;

public interface SquareMBean {
    double calculateArea();
    void clearArea();
    void clearRadius();
    double getArea();
    double getRadius();
    void setRadius(double R);
}
