package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed {
    protected final LocalDateTime dateTime;

    protected final String description;

    protected final int calories;

    protected  String id;

    protected final boolean exceed;

    public UserMealWithExceed(String id, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
        this.id = id;
    }

    public String getDescription(){return description;}

    public boolean isExceed() {
        return exceed;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setID(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
