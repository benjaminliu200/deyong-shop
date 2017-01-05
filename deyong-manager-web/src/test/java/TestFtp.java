import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by benjamin on 2017/1/5.
 */
public class TestFtp {

    @Test
    public void testFtpClient() throws IOException {
        // 创建一个 FTP 客户端
        FTPClient client = new FTPClient();

        // 连接主机
        client.connect("192.168.0.220", 22);

        // 登陆账号密码
        client.login("root", "1234");

        // 需要上传的文件
        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\benjamin\\Desktop\\图片\\搞笑图片\\3022b74543a98226c45c0cf38282b9014890ebaf.jpg"));

        // 设置上传文件的类型
        client.setFileType(FTP.BINARY_FILE_TYPE);

        // 改变文件上传的目录
        client.changeWorkingDirectory("/home/ldy/images/");

        // 上传的文件
        client.storeFile("hello1.jpg", inputStream);

        // 退出客户端
        client.logout();
    }
}
