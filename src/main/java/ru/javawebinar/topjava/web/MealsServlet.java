package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.repository.InMemoryUserMealRepository;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;
import sun.rmi.runtime.Log;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Scorpa on 3/5/2016.
 */
public class MealsServlet extends HttpServlet {

    private static final Logger LOG = getLogger(UserServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryUserMealRepository();
    }

    private UserMealRepository repository;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        UserMeal userMeal = new UserMeal(id.isEmpty() ? UUID.randomUUID().toString() : id,
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        LOG.info(userMeal.isNew() ? "Create {}" : "Update {]", userMeal);
        repository.save(userMeal);
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            LOG.debug("No action submited");
            showAllMeals(request, response);
        } else {
            LOG.debug("redirect to meaList");
            switch (action) {
                case "create":
                    LOG.info("Creating new meal");
                    final UserMeal meal = new UserMeal(LocalDateTime.now(), "created", 1000);
                    request.setAttribute("meal", meal);
                    request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
                    break;
                case "delete":
                    String id = getId(request);
                    LOG.info("Delete {}", id);
                    repository.delete(id);
                    response.sendRedirect("meals");
                    break;
                case "update":
                    LOG.info("Creating new meal");
                    final UserMeal editMeal = repository.get(getId(request));
                    request.setAttribute("meal", editMeal);
                    request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
                    break;
                default:
                    repository.get(getId(request));
                    showAllMeals(request, response);
            }
        }
    }

        public void showAllMeals(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            LOG.info("getALL");
        request.setAttribute("mealsList",
                UserMealsUtil.getWithExceeded(repository.getAll(), 2000));
        request.getRequestDispatcher("/mealist.jsp").forward(request, response);
    }
    private String getId(HttpServletRequest request) {
        return  Objects.requireNonNull(request.getParameter("id"));
    }
}
