package mbeans;

import model.Point;

public interface CountHitsMBean {

    void addShot(Point point);
    void clearShot();
    int getCountShots();
    int getCountHits();
    void setCountShots(int a);
    void setCountHits(int a);
}
