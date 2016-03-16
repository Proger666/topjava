package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Component
public class UserMealRestController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMealService service;

    public Collection<UserMeal> getAll(int userId, LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime) {
        LOG.info("getAll meal");
        return service.getAll(userId, fromDate, toDate, fromTime, toTime);
    }

    public UserMeal get(int userId, int mealId) {
        LOG.info("get meal of User {] " + "and meal ID is", userId, mealId);
        return service.get(userId, mealId);
    }

    public UserMeal create(int userId, UserMeal userMeal) {
        LOG.info("create meal for user {] ", userId);
        return service.save(userId, userMeal);
    }

    public void delete(int userId, int mealId) {
        LOG.info("delete user Id {] meal {] ", userId, mealId);
        service.delete(userId, mealId);
    }

    public void update(int userId, UserMeal userMeal) {
        LOG.info("update meal for user {}", userId);
        service.update(userId, userMeal);
    }


}
