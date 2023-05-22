package mbeans;

import model.Point;

public interface CountHitsMBean {

    void addShot(Point point);
    int getCountShots();
    int getCountHits();

}
