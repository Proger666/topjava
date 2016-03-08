package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by Scorpa on 3/5/2016.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal);

    void delete(String id);
    UserMeal get(String id);

    Collection<UserMeal> getAll();
}
