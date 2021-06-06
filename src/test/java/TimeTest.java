import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml",
})
public class TimeTest {

    @Test
    public void testTime() throws Exception {
        long time = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0)).atZone(ZoneId.systemDefault()).toEpochSecond();
    }

}
