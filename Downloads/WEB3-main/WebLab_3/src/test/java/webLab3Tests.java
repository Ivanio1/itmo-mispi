import controller.AreaResultChecker;
import controller.InputValidator;
import model.Point;
import model.PointsBean;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Area;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class webLab3Tests {
    private Point point;
    private PointsBean pointsBean;

    @Before
    public void create() {
        point = new Point();

        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point((long) 100, 1.0, 2.0, 3.0, true, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")), "100"));
        points.add(new Point((long) 200, -1.0, 3.0, 5.0, true, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")), "200"));
        points.add(new Point((long) 300, -1.0, -2.0, 3.0, false, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")), "300"));
        DataBaseMock dataBaseMock = new DataBaseMock(points);
        pointsBean = new PointsBean(dataBaseMock);
    }

    @After
    public void resetPoint() {
        point = null;
    }

    @Test
    public void checkPointCreating() {
        Point newPoint = new Point((long) 100, 1.0, 2.0, 3.0, true, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")), "100");
        point.setId(100L);
        point.setX(1.0);
        point.setY(2.0);
        point.setR(3.0);
        point.setResult(true);
        point.setCurr_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")));
        point.setEx_time("100");

        Assert.assertEquals(newPoint.getId(), point.getId());
        Assert.assertEquals(newPoint.getX(), point.getX());
        Assert.assertEquals(newPoint.getY(), point.getY());
        Assert.assertEquals(newPoint.getR(), point.getR());
        Assert.assertEquals(newPoint.getResult(), point.getResult());
        Assert.assertEquals(newPoint.getCurr_time(), point.getCurr_time());
        Assert.assertEquals(newPoint.getEx_time(), point.getEx_time());
    }

    @Test
    public void checkFirstQuadrant() {
        point.setR(3.0);
        for (double i = 0; i <= 20; i += 0.01) {
            for (double j = 0; j <= 20; j += 0.01) {
                point.setX(i);
                point.setY(j);
                point.setResult(AreaResultChecker.isPointInArea(point));
                if (i * i + j * j <= 9) {
                    Assert.assertEquals(true, point.getResult());
                } else {
                    Assert.assertEquals(false, point.getResult());
                }
            }
        }
    }

    @Test
    public void checkSecondQuadrant() {
        point.setR(5.0);
        for (double i = -20; i < 0; i += 0.01) {
            for (double j = 0.001; j <= 20; j += 0.01) {
                point.setX(i);
                point.setY(j);
                point.setResult(AreaResultChecker.isPointInArea(point));
                if (i >= -2.5 && j <= 5.0) {
                    Assert.assertEquals(true, point.getResult());
                } else {

                    Assert.assertEquals(false, point.getResult());
                }
            }
        }
    }

    @Test
    public void checkThirdQuadrant() {
        point.setR(4.0);
        for (double i = -20; i <= 0; i += 0.01) {
            for (double j = -20; j <= 0; j += 0.01) {
                point.setX(i);
                point.setY(j);
                point.setResult(AreaResultChecker.isPointInArea(point));
                if (-i - j <= 4) {
                    Assert.assertEquals(true, point.getResult());
                } else {
                    Assert.assertEquals(false, point.getResult());
                }
            }
        }
    }

    @Test
    public void checkFourthQuadrant() {
        point.setR(6.0);
        for (double i = 0.001; i <= 20; i += 0.01) {
            for (double j = -20; j <= -0.001; j += 0.01) {
                point.setX(i);
                point.setY(j);
                point.setResult(AreaResultChecker.isPointInArea(point));
                Assert.assertEquals(false, point.getResult());
            }
        }
    }

    @Test
    public void randomPoint() {
        boolean flag;
        double x, y, r;
        for (int i = 0; i < 1000; i++) {
            x = (double) (Math.random() * 50);
            y = (double) (Math.random() * 50);
            r = (double) (Math.random() * 100 + 1);
            point.setX(x);
            point.setY(y);
            point.setR(r);
            if ((x <= 0 && y <= 0 && r >= x * (-1) + y * (-1)) ||
                    (x <= 0 && y >= 0 && x >= -r / 2 && y <= r) ||
                    (x >= 0 && y >= 0 && x * x + y * y <= r * r)) {
                flag = true;
            } else {
                flag = false;
            }

            point.setResult(AreaResultChecker.isPointInArea(point));
            Assert.assertEquals(flag, point.getResult());
        }
    }
    @Test
    public void testAxis() {
        double[] xValues = {0, 0, -1, 2, 3};
        double[] yValues = {-1.5, 0.7, 0, 0, -1};
        double[] rValues = {2, 2.5, 3, 2.5, 3};

        boolean[] expected = {true, true, true, true, false};
        boolean[] result = new boolean[5];

        for (int i = 0; i < xValues.length; i++) {
            result[i] = AreaResultChecker.isCoordinatesInArea(xValues[i], yValues[i], rValues[i]);
        }
        Assert.assertArrayEquals(expected, result);
    }

    @Test()
    public void nullPointFields() {
        Assert.assertNull(point.getId());
        Assert.assertNull(point.getX());
        Assert.assertNull(point.getY());
        Assert.assertNull(point.getR());
        Assert.assertNull(point.getCurr_time());
        Assert.assertNull(point.getEx_time());
        Assert.assertNull(point.getResult());

    }

    @Test
    public void checkValidPoint() {
        double[] xValues = {1, 2, 6, -5, -10};
        double[] yValues = {2, 3, 2, -1, -10};
        double[] rValues = {3, 6, 2, 3, -10};

        boolean[] expected = {true, false, false, true, false};
        boolean[] result = new boolean[5];

        for (int i = 0; i < xValues.length; i++) {
            point.setX(xValues[i]);
            point.setY(yValues[i]);
            point.setR(rValues[i]);
            result[i] = InputValidator.isPointValid(point);
        }
        Assert.assertArrayEquals(expected, result);
    }


    @Test
    public void getPoints() {
        List<Point> newPoints = new ArrayList<>();
        newPoints.add(new Point((long) 100, 1.0, 2.0, 3.0, true, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")), "100"));
        newPoints.add(new Point((long) 200, -1.0, 3.0, 5.0, true, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")), "200"));
        newPoints.add(new Point((long) 300, -1.0, -2.0, 3.0, false, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")), "300"));
        pointsBean.uploadPoints();
        Assert.assertEquals(newPoints.toString(), pointsBean.getPointsCollection().toString());
    }

    @Test
    public void removePoints() {
        pointsBean.clear();
        Assert.assertEquals(pointsBean.getPointsCollection(), new ArrayList<>());
    }

    @Test
    public void addPoint() {
        List<Point> newPoints = new ArrayList<>();
        Point pointToAdd = new Point((long) 666, 0.0, 0.0, 3.0, true, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")), "300");

        newPoints.add(new Point((long) 100, 1.0, 2.0, 3.0, true, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")), "100"));
        newPoints.add(new Point((long) 200, -1.0, 3.0, 5.0, true, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")), "200"));
        newPoints.add(new Point((long) 300, -1.0, -2.0, 3.0, false, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")), "300"));
        newPoints.add(new Point((long) 666, 0.0, 0.0, 3.0, true, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")), "666"));

        pointsBean.addPointWithCalculatedResultToDatabase(pointToAdd, "666");
        pointsBean.uploadPoints();
        Assert.assertEquals(newPoints.toString(), pointsBean.getPointsCollection().toString());
    }


    static class DataBaseMock implements model.DatabaseManager {
        private final ArrayList<Point> list;

        DataBaseMock(ArrayList<Point> points) {
            this.list = points;
        }

        @Override
        public List<Point> getCollectionFromDataBase() {
            return list;
        }

        @Override
        public void addPoint(Point point) {
            list.add(point);

        }

        @Override
        public void removeAllPoints() {
            list.clear();
        }
    }
}