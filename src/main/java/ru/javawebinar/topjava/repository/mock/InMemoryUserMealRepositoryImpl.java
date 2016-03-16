package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer, Map<Integer, UserMeal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);



    @Override
    public UserMeal save(Integer userId, UserMeal userMeal) {
        if (userMeal.isNew()) {
           userMeal.setId(counter.incrementAndGet());
        }
        repository.get(userId).put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int userId,int mealId) {
        try {
            repository.get(userId).remove(mealId);
        } catch (NullPointerException notFound) {
            return false;
        }
        return true;

        //remove(id);
    }

    @Override
    public UserMeal get(int userId,int mealId) {
         try{
            return repository.get(userId).get(mealId);
         } catch (NullPointerException notFound) {
             return null;
         }
    //    return true;
    }

    @Override
    public Collection<UserMeal> getAll(int userId, LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime) {
        return repository.get(userId).values().stream()
                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalDate(), fromDate, toDate))
                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(),fromTime,toTime))
                .sorted(Comparator.comparing(UserMeal::getDateTime))
                .collect(Collectors.toList());
    }
}

