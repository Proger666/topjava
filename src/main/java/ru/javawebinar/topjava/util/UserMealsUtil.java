package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public final static List<UserMeal> MEAL_LIST = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),

                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 600)
        );

    public static List<UserMealWithExceed> getWithExceeded(Collection<UserMeal> mealList, int calories){
        return getFilteredMealsWithExceeded(mealList, LocalTime.MIN, LocalTime.MAX, calories);
    }

    public static UserMealWithExceed createWithExceeded(UserMeal um, boolean exceeded){
        return new UserMealWithExceed(um.getId(),um.getDateTime(),um.getDescription(), um.getCalories(), exceeded);
    }

    private static List<UserMealWithExceed>  getFilteredMealsWithExceeded(Collection<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

            Map<LocalDate, Integer> caloriesByDate = mealList
                    .stream()
                    .collect(Collectors.groupingBy(
                            userMeal -> userMeal.getDateTime().toLocalDate(),
                            Collectors.summingInt(UserMeal::getCalories)));

            return mealList
                    .stream()
                    .filter(userMeal -> TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                    .map(userMeal -> createWithExceeded(
                            userMeal,
                            caloriesByDate.get(userMeal.getDateTime().toLocalDate())> caloriesPerDay)
                    )

                    .collect(Collectors.toList());
    }
}
