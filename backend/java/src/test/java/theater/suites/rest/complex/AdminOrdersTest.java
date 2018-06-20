package theater.suites.rest.complex;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import theater.suites.shared.SpringTestBase;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminOrdersTest extends SpringTestBase {
    public static final String url = "/api/complex/orders";

    @Test
    public void getTest() {
        try {
            var res = mockMvc.perform(MockMvcRequestBuilders.get(url)).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//            res.andExpect(content().json(mapper.writeValueAsString(expected)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
