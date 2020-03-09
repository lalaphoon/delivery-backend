package utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


//tut: https://mkyong.com/java/how-to-get-current-timestamps-in-java/
public class TimeStamp {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    public static String getCurrentTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new String(sdf.format(timestamp));
    }
}
