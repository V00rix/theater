package theater.controllers;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import theater.repositories.TheaterRepository;
import theater.utility.Dummy;
import theater.utility.RESTTestBase;

public class TheaterControllerTest extends RESTTestBase implements RESTTest {
    private static final String url = "/api/theater/";

    @Autowired
    TheaterRepository theaterRepository;

    @Override
    @Test
    public void getTest() throws Exception {
        RESTTest.defaultGetTest(theaterRepository, Dummy::theater, mockMvc, url);
    }

    @Override
    @Test
    public void postTest() throws Exception {
        RESTTest.defaultPostTest(theaterRepository, Dummy::theater, mockMvc, url + "new");
    }

    @Test
    public void updateTest() throws Exception {
        RESTTest.defaultUpdateTest(theaterRepository, Dummy::theater, () -> {
            var newTheater = Dummy.theater();
            newTheater.name = "Second Name";
            newTheater.address = "1 Box 2 Left 10 Above the Skies";
            return newTheater;
        }, mockMvc, url);
    }
}