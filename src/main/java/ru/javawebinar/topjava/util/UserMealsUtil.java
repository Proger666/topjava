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

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
      List<UserMealWithExceed> a = getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

        a.stream().
                map(UserMealWithExceed::getDescription)
                .forEach(System.out::println);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream()
                .collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)));


        return mealList.stream()
                .filter(st -> TimeUtil.isBetween(st.getDateTime().toLocalTime(), startTime, endTime))
                .filter(st -> caloriesSumByDate.get(st.getDateTime().toLocalDate()) >= caloriesPerDay)
                .map(userMeal ->  new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), true))
                .collect(Collectors.toList());
    }
}
