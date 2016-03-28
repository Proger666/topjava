package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

    public static final int MEAL_ID_FIRST = START_SEQ;
    public static final int MEAL_ID_SECOND = START_SEQ + 1;

    public static final UserMeal MEAL_FIRST = new UserMeal(MEAL_ID_FIRST,LocalDateTime.now(), "slon", 2000);
    public static final UserMeal MEAL_SECOND = new UserMeal(MEAL_ID_SECOND,LocalDateTime.now(), "ovca", 2000);

    public static class TestMeal extends UserMeal {

        public TestMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
            super(id, dateTime, description, calories);
        }

        public TestMeal(UserMeal um) {
            this(um.getId(), um.getDateTime(), um.getDescription(), um.getCalories());
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            TestMeal that = (TestMeal) o;

            return Objects.equals(that.getId(), that.getId())
                    && Objects.equals(that.getCalories(), that.getCalories())
                    && Objects.equals(that.getDateTime() , that.getDateTime())
                    && Objects.equals(that.getDescription(), that.getDescription());
        }
    }
}
