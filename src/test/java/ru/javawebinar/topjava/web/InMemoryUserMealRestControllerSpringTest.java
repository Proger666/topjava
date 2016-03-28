package ru.javawebinar.topjava.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by Scorpa on 3/26/2016.
 */

@ContextConfiguration({
        "classpath:spring/spring-test.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class InMemoryUserMealRestControllerSpringTest {


    @Autowired
    private UserMealRestController controller;

    @Autowired
    private UserMealRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        userRepository.getAll().forEach(u -> userRepository.delete(u.getId()));
        userRepository.save(USER);
        userRepository.save(ADMIN);

        repository.delete(ADMIN_ID);
        repository.delete(USER_ID);

        repository.save(MEAL_FIRST, USER_ID);
        repository.save(MEAL_SECOND, ADMIN_ID);
    }

    @Test
    public void testDelete() throws Exception{
        controller.delete(MealTestData.MEAL_ID_FIRST);
        List<UserMealWithExceed> meal = controller.getAll();
        Assert.assertEquals(meal.size(), 1);

    }
}
