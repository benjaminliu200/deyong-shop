import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by benjamin on 2017/1/4.
 */
public class TestPageHelper {
    @Test
    public void testPageHelper() throws Exception {
        // 1 获取mapper 代理对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/springContext-*.xml");


    }
}
