package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository {

    private static final BeanPropertyRowMapper<UserMeal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(UserMeal.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertMeal;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate;}

    @Autowired
    public JdbcUserMealRepositoryImpl(DataSource dataSource) {
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("MEALS")
                .usingGeneratedKeyColumns("meal_id");
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("meal_id", userMeal.getId())
                .addValue("description", userMeal.getDescription())
                .addValue("calories",userMeal.getCalories())
                .addValue("dateTime", userMeal.getDateTime());
        if(userMeal.isNew()){
            Number newKey = insertMeal.executeAndReturnKey(map);
            userMeal.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE meals SET description=:description, calories=:calories, datetime=:dateTime," +
                            "WHERE meal_id=:meal_id", map);
        }
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id=?", id) != 0;
    }

    @Override
    public boolean delete(int userId){
        return jdbcTemplate.update("DELETE FROM meals WHERE id=?", userId) !=0;
    }
    @Override
    public UserMeal get(int id, int userId) {
        List<UserMeal> meals = jdbcTemplate.query("SELECT * FROM meals WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM meals ORDER BY dateTime ", ROW_MAPPER);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("id", userId);
        return namedParameterJdbcTemplate.queryForList(
                "UPDATE meals SET description=:description, calories=:calories, datetime=:dateTime," +
                        "WHERE meal_id=:meal_id", map, UserMeal.class);


    }
}