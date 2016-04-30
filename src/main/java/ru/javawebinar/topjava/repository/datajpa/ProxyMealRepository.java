package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Scorpa on 4/21/2016.
 */
@Transactional
public interface ProxyMealRepository extends JpaRepository<UserMeal, Integer> {

    @Override
    UserMeal save(UserMeal userMeal);

    @Modifying
    @Transactional
    @Query("DELETE from UserMeal um where um.id=:id and um.user.id=:userID")
    int delete(@Param("id") int mealId, @Param("userID") int userId);

    @Query("select um from UserMeal um where um.id=:id and um.user.id=:userId")
    UserMeal get(@Param("id") int id, @Param("userId") int userId);

    @Query("select um from UserMeal um where um.user.id=:userId order by um.dateTime DESC")
    List<UserMeal> getAll(@Param("userId") int userId);

    @SuppressWarnings("JpaQlInspection")
    @Query("select um from UserMeal um where um.user.id=:userId and um.dateTime between :startDate and :endDate order by um.dateTime desc ")
    List<UserMeal> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

}
