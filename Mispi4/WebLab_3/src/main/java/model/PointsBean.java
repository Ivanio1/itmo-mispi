package model;

import controller.AreaResultChecker;
import controller.DatabaseManager;
import controller.InputValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import mbeans.CountHits;
import mbeans.Square;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.management.*;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ManagedBean(name = "pointsBean")
@ApplicationScoped
public class PointsBean implements Serializable {

    private List<Point> pointsCollection = new ArrayList<>();
    private int offset;
    private Point pointField = new Point();
    private Point pointGraphic = new Point();
    private int hits = 0;
    private int shots = 0;
    private String square = "0";
    private String message="";
    private static CountHits countHits;
    private static Square squareCalc;

    static {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName result = new ObjectName("mbeans:type=CountHits");
            ObjectName square = new ObjectName("mbeans:type=Square");
            countHits = new CountHits();
            squareCalc = new Square();
            mbs.registerMBean(countHits, result);
            mbs.registerMBean(squareCalc, square);
        } catch (MalformedObjectNameException | NotCompliantMBeanException | InstanceAlreadyExistsException | MBeanRegistrationException e) {
            e.printStackTrace();
        }
    }

    public void uploadPoints() {
        pointsCollection = DatabaseManager.getInstance().getCollectionFromDataBase();
        shots = pointsCollection.size();
        countHits.setCountShots(pointsCollection.size());
        hits = 0;
        for (Point point : pointsCollection) {
            if (point.getResult()) {
                hits++;
            }
        }
        countHits.setCountHits(hits);

        if (pointField.getR() != null) {
            squareCalc.setR(pointField.getR());
        } else {
            squareCalc.setR(0);
        }
        DecimalFormat df = new DecimalFormat("#.###");
        square = df.format(squareCalc.calculateArea());
        message = countHits.getMessage();

    }

    public void submitFieldPoints() {
        if (InputValidator.isPointValid(pointField))
            addPointWithCalculatedResultToDatabase(pointField);
    }

    public void submitGraphicPoints() {
        pointGraphic.setR(pointField.getR());
        try {
            double R = pointGraphic.getR();
            if (InputValidator.isRValid(R)) {
                addPointWithCalculatedResultToDatabase(pointGraphic);
            }
        } catch (NullPointerException e) {
            // System.out.println("Okay");
        }
    }

    public void submitR() {
        if (InputValidator.isPointValid(pointField)) {
            //skip
        }
    }

    public String getDateOffset(int offset, String date) {
        String time1 = Instant.parse(date)
                .atOffset(ZoneOffset.ofHours(offset * (-1) / 60))
                .format(
                        DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")
                );

        return time1;
    }

    public void clear() {
        DatabaseManager.getInstance().removeAllPoints();
    }

    @SneakyThrows
    private void addPointWithCalculatedResultToDatabase(Point point) {
        long startTime = (System.nanoTime());
        long ss = (long) (System.nanoTime() - startTime + Math.random() * 100);
        try {
            String elatedTime = String.valueOf(ss).substring(0, 3);
            point.setEx_time(elatedTime);
        } catch (StringIndexOutOfBoundsException e) {
            String elatedTime = String.valueOf(ss);
            point.setEx_time(elatedTime);
        }
        point.setResult(AreaResultChecker.isPointInArea(point));
        DatabaseManager.getInstance().addPoint((Point) point.clone());
        countHits.addShot(point);
        message = countHits.getMessage();
    }
}