import com.ldy.common.util.SftpUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class SftpUtilTest {
    public static void main(String[] args) {
//        testLogin();
//        testMakeDir();
//        testDelFile();
//        testDelEmptyDir();
//        testDir();
//        testLs();
//        testParamLs();
//        testChangeDir();
//        testExist();
//        testParamExist();
//        testUploadFile();
//        testUploadFile2();
//        testDownload();
    }

    public static void testLogin(){ //OK
        SftpUtil SftpUtil = getSftpUtil();
        
        SftpUtil.login();
        System.out.println("登陆成功");
        SftpUtil.logout();
    }
    
    public static void testMakeDir(){ //OK
        SftpUtil SftpUtil = getSftpUtil();
        SftpUtil.login();
        SftpUtil.makeDir("test2");
        SftpUtil.changeDir("test2");
        SftpUtil.makeDir("/test2/test2_1");
        SftpUtil.logout();
    }
    
    public static void testDelFile(){ //OK
        SftpUtil SftpUtil = getSftpUtil();
        SftpUtil.login();
        SftpUtil.delFile("file1.txt");
        SftpUtil.logout();
    }
    
    public static void testDelEmptyDir(){ //OK
        SftpUtil SftpUtil = getSftpUtil();
        SftpUtil.login();
        SftpUtil.delDir("test3");
        SftpUtil.logout();
    }
    
    public static void testDir(){ //OK
        SftpUtil SftpUtil = getSftpUtil();
        SftpUtil.login();
        SftpUtil.delDir("test4");
        SftpUtil.logout();
    }
    
    public static void testLs(){ //OK
        SftpUtil SftpUtil = getSftpUtil();
        SftpUtil.login();
        System.out.println(Arrays.toString(SftpUtil.ls()));
        System.out.println(Arrays.toString(SftpUtil.lsFiles()));
        System.out.println(Arrays.toString(SftpUtil.lsDirs()));
        SftpUtil.logout();
    }
    
    public static void testParamLs(){ //OK
        SftpUtil SftpUtil = getSftpUtil();
        SftpUtil.login();
        System.out.println(Arrays.toString(SftpUtil.ls("test1/test4")));
        System.out.println(Arrays.toString(SftpUtil.lsFiles("test1/test4")));
        System.out.println(Arrays.toString(SftpUtil.lsDirs("test1/test4")));
        SftpUtil.logout();
    }
    
    public static void testChangeDir(){ //OK
        SftpUtil SftpUtil = getSftpUtil();
        SftpUtil.login();
        SftpUtil.changeDir("test1");
        SftpUtil.changeDir("/test1/test4");
        SftpUtil.changeToParentDir();
        SftpUtil.changeToHomeDir();
        SftpUtil.logout();
    }
    
    public static void testExist(){ //OK
        SftpUtil SftpUtil = getSftpUtil();
        SftpUtil.login();
        System.out.println(SftpUtil.exist("2fs.docx"));
        System.out.println(SftpUtil.exist("test1"));
        System.out.println(SftpUtil.existDir("test2"));
        System.out.println(SftpUtil.existDir("2sfs.txt"));
        System.out.println(SftpUtil.existFile("2sfs.txt"));
        System.out.println(SftpUtil.existFile("test2"));
        SftpUtil.logout();
    }
    
    public static void testParamExist(){ //OK
        SftpUtil SftpUtil = getSftpUtil();
        SftpUtil.login();
        System.out.println(SftpUtil.exist("test1","test4"));
        System.out.println(SftpUtil.exist("test1","test_bak.jpg"));
        System.out.println(SftpUtil.existDir("/test1","test3"));
        System.out.println(SftpUtil.existDir("/test1","test_bak.jpg"));
        System.out.println(SftpUtil.existFile("test1","test_bak.jpg"));
        System.out.println(SftpUtil.existFile("test1","test2"));
        SftpUtil.logout();
    }
    
    
    public static void testUploadFile(){ //OK
        SftpUtil SftpUtil = getSftpUtil();
        SftpUtil.login();
        SftpUtil.uploadFile("/test1/test3", "test_bak2.jpg", "D:\\test.jpg");
        try {
            SftpUtil.uploadFile("/test1/test2", "test_bak3.jpg", new FileInputStream("E:\\test.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SftpUtil.logout();
    }
    
    public static void testUploadFile2(){ //OK
        SftpUtil SftpUtil = getSftpUtil();
        SftpUtil.login();
        SftpUtil.uploadFile("test1/test3", "test_bak2.jpg", "D:\\test.jpg");
        try {
            SftpUtil.uploadFile("test1/test2", "test_bak3.jpg", new FileInputStream("D:\\test.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SftpUtil.logout();
    }
    
    public static void testDownload(){ //OK
        SftpUtil SftpUtil = getSftpUtil();
        SftpUtil.login();
        SftpUtil.downloadFile("test1", "test_bak.jpg", "D:\\testdown");
        SftpUtil.downloadFile("/", "2fs.docx", "D:\\testdown");
        SftpUtil.logout();
    }
    
    private static SftpUtil getSftpUtil(){

        String host = "192.168.0.220";
        int port = 22;
        int timeout = 60000;
        String username = "root";
        String password = "1234";
        
        SftpUtil SftpUtil = new SftpUtil(host, port, timeout, username, password);
        
        return SftpUtil;
    }
}