package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    private final static Logger LOG = LoggerFactory.getLogger(UserMealServiceImpl.class);

    @Autowired
    private UserMealRepository repository;


    @Override
    public UserMeal save(int id, UserMeal userMeal) {
        LOG.info("saving meal {} for user id {]", userMeal, id);
        return repository.save(id,userMeal);
    }

    public void delete(int id, int mealId) {
        LOG.info("deleting meal id: {} for user id: {]", mealId, id);
        ExceptionUtil.check(repository.delete(id, mealId), id);
    }

    public UserMeal get(int id, int mealId) throws NotFoundException {
        LOG. info("getting meal id: {} for user id: {]", mealId, id);
        return ExceptionUtil.check(repository.get(id, mealId), id );
    }

    @Override
     public List<UserMeal> getAll(int userId, LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime) {
        LOG.info("getting all meal");
        return new ArrayList<>(repository.getAll(userId, fromDate, toDate, fromTime, toTime));
    }

    @Override
    public void update(int userId, UserMeal userMeal) {
        LOG.info("Updated User with ID: {}", userId);
        repository.save(userId, userMeal);
    }

}
