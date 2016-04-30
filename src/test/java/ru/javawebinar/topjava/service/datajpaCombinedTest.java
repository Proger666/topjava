package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by Scorpa on 4/23/2016.
 */
@ActiveProfiles(Profiles.DATAJPA)
public class datajpaCombinedTest extends UserServiceTest {
}
