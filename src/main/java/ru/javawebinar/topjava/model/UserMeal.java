package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal {
    protected final LocalDateTime dateTime;
    protected  String id;

    protected final String description;

    protected final int calories;

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public UserMeal(String id, LocalDateTime dateTime, String description, int calories) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public void setID(String id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }

    public String getId() {
        return id;
    }
}
