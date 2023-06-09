package mbeans;


import model.Point;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;


public class CountHits extends NotificationBroadcasterSupport implements CountHitsMBean {

    private int countShots = 0;
    private int countHits = 0;
    private int countMisses = 0;
    private String message = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void addShot(Point point) {
        countShots++;
        if (!point.getResult()) {
            countMisses++;
            if (countMisses == 4) {
                Notification notification = new Notification("countOfMissedPointsEqualsFour",
                        this, countMisses, "Count of missed points equals 4");
                sendNotification(notification);
                message = "4 misses in a row!";
                countMisses = 0;

            } else message = "";
        } else {
            countHits++;
            countMisses = 0;
            message = "";
        }
    }


    @Override
    public int getCountShots() {
        return countShots;
    }

    @Override
    public int getCountHits() {
        return countHits;
    }

    public void setCountShots(int a) {
        this.countShots = a;
    }

    public void setCountHits(int a) {
        this.countHits = a;
    }


}
