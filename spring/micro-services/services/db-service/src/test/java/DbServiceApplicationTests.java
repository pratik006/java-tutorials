import com.prapps.tutorial.spring.netflix.db.DBServiceStarter;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {DBServiceStarter.class})
public class DbServiceApplicationTests {

	//@BeforeClass
	public static void setUp() {
		System.setProperty("logging.baseloc", "/tmp");
	}

	//@Test
	public void contextLoads() {
	}

}
