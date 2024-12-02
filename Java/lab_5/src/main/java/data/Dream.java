package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dream {
    private String name;
    private Date date;
    private Boolean dream;
    private String description;
    private int quality;

    public Dream(String name, Date date, int quality, Boolean dream, String description) throws ParseException {
        this.name = name;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.date = dateFormat.parse(String.valueOf(date));
        this.quality = quality;
        this.dream = dream;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Boolean getDream() {
        return dream;
    }

    public String getDescription() {
        return description;
    }

    public int getQuality() {
        return quality;
    }
}
