import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.Test;

/**
 * Created by benjamin on 2017/1/16.
 */
public class OkHttpTest {
    @Test
    public void doGet() throws Exception{
        // okHttp3
        OkHttpClient client = new OkHttpClient();
        // 创建Get对象

        client.newCall()
        // 执行请求
        // 取响应结果
    }
}
