package model;

import controller.AreaResultChecker;
import controller.DatabaseManager;
import controller.InputValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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


    public void uploadPoints() {
        pointsCollection = DatabaseManager.getInstance().getCollectionFromDataBase();
    }

    public void submitFieldPoints() {
        //pointField.setCurr_time(pointGraphic.getCurr_time());
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
        if (InputValidator.isPointValid(pointField))
            System.out.println("nice");
    }

    public String getDateOffset(int offset,String date){

        String time1= Instant.parse(date)
                .atOffset(ZoneOffset.ofHours(offset*(-1)/60))
                .format(
                        DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss" )
                );

        return time1;
    }
    public void clear() {
        DatabaseManager.getInstance().removeAllPoints();
    }

    @SneakyThrows
    private void addPointWithCalculatedResultToDatabase(Point point) {
        long startTime = (System.nanoTime());
        long ss = (long) (System.nanoTime() - startTime);
        try{
            String elatedTime = String.valueOf(ss).substring(0, 3);
            point.setEx_time(elatedTime);
        }catch (StringIndexOutOfBoundsException e){
            String elatedTime = String.valueOf(ss);
            point.setEx_time(elatedTime);
        }
        point.setResult(AreaResultChecker.isPointInArea(point));
        DatabaseManager.getInstance().addPoint((Point) point.clone());
    }
}