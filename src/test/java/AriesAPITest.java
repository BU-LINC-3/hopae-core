import kr.hopae.aries.repository.AriesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml",
})
public class AriesAPITest {

    AriesRepository repository = new AriesRepository();

    @Test
    public void createInvitation() throws Exception {

        System.out.println(repository.createInvitation("Invitation-TEST", true).connectionId);
    }

}
