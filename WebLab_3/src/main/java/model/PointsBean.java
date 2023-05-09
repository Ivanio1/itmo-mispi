package model;

import controller.AreaResultChecker;
import controller.DatabaseManager;
import controller.InputValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
import java.util.*;

@Getter
@Setter
@ManagedBean(name = "pointsBean")
@ApplicationScoped
@NoArgsConstructor
public class PointsBean implements Serializable {

    private List<Point> pointsCollection = new ArrayList<>();

    private int offset;
    private Point pointField = new Point();
    private Point pointGraphic = new Point();

    private model.DatabaseManager databaseManager= new model.DatabaseManager() {
        @Override
        public List<Point> getCollectionFromDataBase() {
            controller.DatabaseManager databaseManager1=new controller.DatabaseManager();
            return databaseManager1.getCollectionFromDataBase() ;
        }

        @Override
        public void addPoint(Point point) {
            controller.DatabaseManager databaseManager1=new controller.DatabaseManager();
            databaseManager1.addPoint(point);
        }

        @Override
        public void removeAllPoints() {
            controller.DatabaseManager databaseManager1=new controller.DatabaseManager();
            databaseManager1.removeAllPoints();
        }
    };


    public PointsBean(model.DatabaseManager databaseManager) {
        this.databaseManager=databaseManager;
    }


    public void uploadPoints() {
        pointsCollection = databaseManager.getCollectionFromDataBase();
    }

    public void submitFieldPoints() {
        if (InputValidator.isPointValid(pointField))
            addPointWithCalculatedResultToDatabase(pointField,null);
    }

    public void submitGraphicPoints() {
        pointGraphic.setR(pointField.getR());
        try {
            double R = pointGraphic.getR();
            if (InputValidator.isRValid(R)) {
                addPointWithCalculatedResultToDatabase(pointGraphic,null);
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
    System.out.println(date);
        String time1= Instant.parse(date)
                .atOffset(ZoneOffset.ofHours(offset*(-1)/60))
                .format(
                        DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss" )
                );

        return time1;
    }
    public void clear() {
        databaseManager.removeAllPoints();
    }

    @SneakyThrows
    public void addPointWithCalculatedResultToDatabase(Point point, String extime) {
        if(extime==null){
            long startTime = (System.nanoTime());
            long ss = (long) (System.nanoTime() - startTime);
            try{
                String elatedTime = String.valueOf(ss).substring(0, 3);
                point.setEx_time(elatedTime);
            }catch (StringIndexOutOfBoundsException e){
                String elatedTime = String.valueOf(ss);
                point.setEx_time(elatedTime);
            }
        }
        else{
            point.setEx_time(extime);
        }

        point.setResult(AreaResultChecker.isPointInArea(point));
        databaseManager.addPoint((Point) point.clone());
    }
}
