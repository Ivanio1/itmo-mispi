package model;

import java.util.List;

public interface DatabaseManager {
    public List<Point> getCollectionFromDataBase();

    public void addPoint(Point point);

    public void removeAllPoints();
}
