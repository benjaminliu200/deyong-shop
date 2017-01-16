import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

/**
 * Created by benjamin on 2017/1/16.
 */
public class OkHttpTest {
    @Test
    public void doGet() throws Exception{
        // okHttp3
        OkHttpClient client = new OkHttpClient();
        // 创建Request对象

        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .build();
        Response response = client.newCall(request).execute();
        // 执行请求
        // 取响应结果
        System.out.println(response.body().string());
    }
}
