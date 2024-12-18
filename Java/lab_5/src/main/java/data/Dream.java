package data;

import com.mysql.cj.Session;

import javax.swing.text.html.HTML;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dream {
    private final String name;
    private final String date;
    private final boolean dream;
    private final String description;
    private final int quality;

    public Dream(String name, String date, int quality, boolean dream, String description) throws ParseException {

        this.name = name;
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.date = date;
        this.quality = quality;
        this.dream = dream;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public boolean getDream() {
        return dream;
    }

    public String getDescription() {
        return description;
    }

    public int getQuality() {
        return quality;
    }
}
