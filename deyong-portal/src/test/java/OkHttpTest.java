import okhttp3.*;
import org.junit.Test;

/**
 * Created by benjamin on 2017/1/16.
 */
public class OkHttpTest {
//    @Test
    public void doGet() throws Exception {
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
        System.out.println(response.headers().names());
    }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

//    @Test
    public void doPostParam() throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, "{\"name\": \"zhangsan\"}");
        Request request = new Request.Builder()
                .url("http://localhost:8082/httpclient/post.html")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
