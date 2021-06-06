import kr.hopae.baekseok.repository.BUAuthRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml",
})
public class BUAuthAPITest {

    BUAuthRepository repository = new BUAuthRepository();

    @Test
    public void createInvitation() throws Exception {
        boolean test1 = repository.requestLogin(1, "20161234", "12345678");
        boolean test2 = repository.requestLoginInfo().isLogined;
    }

}
