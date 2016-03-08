package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Scorpa on 3/5/2016.
 */
public class InMemoryUserMealRepository implements UserMealRepository{
    private Map<String, UserMeal> repository = new ConcurrentHashMap<>();

    {
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));

        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 600));
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if(userMeal.isNew()){
            userMeal.setID(UUID.randomUUID().toString());
        }
        return repository.put(userMeal.getId(), userMeal);
    }

    @Override
    public void delete(String id) {
        repository.remove(id);
    }

    @Override
    public UserMeal get(String id) {
        return repository.get(id);
    }

    @Override
    public Collection<UserMeal> getAll() {
        return repository.values();
    }
}
