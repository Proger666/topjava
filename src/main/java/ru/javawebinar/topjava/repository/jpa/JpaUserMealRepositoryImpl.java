package ru.javawebinar.topjava.repository.jpa;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
        UserMeal ref = em.getReference(UserMeal.class, userId);
        if (userMeal.isNew()) {
                em.persist(ref);
            userMeal.setId(ref.getId());
                return userMeal;
            } else {
                return em.merge(ref);
            }
        }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(UserMeal.DELETE).setParameter("id", id).setParameter("user_id", userId).executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
try {
    return em.createQuery("select m from UserMeal m where m.id=:id and m.user.id=:userId", UserMeal.class).setParameter("id", id)
            .setParameter("userId", userId).getSingleResult();

} catch (Exception e) {
    return null;
}
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        try {
            return em.createQuery("select m from UserMeal m where m.user.id=:userId", UserMeal.class)
                    .setParameter("userId", userId).getResultList();

        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}