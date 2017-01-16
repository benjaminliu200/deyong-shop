import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by benjamin on 2017/1/16.
 */
public class HttpClientTest {

    @Test
    public void testGet() throws Exception{
        // 获得httpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建Get对象
        HttpGet get = new HttpGet("http://www.baidu.com");
        // 执行请求
        CloseableHttpResponse response = client.execute(get);
        // 取响应结果
        HttpEntity entity = response.getEntity();
        Header[] allHeaders = response.getAllHeaders();
        Arrays.toString(allHeaders);
        String s = EntityUtils.toString(entity);
        System.out.println(s);
        // 关闭httpClient
        client.close();
    }
}
