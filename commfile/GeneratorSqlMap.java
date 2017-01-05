import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 2017/1/4.
 */
public class GeneratorSqlMap {

    public void generator() throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
//        File configFile = new File(String.valueOf(getClass().getClassLoader().getResourceAsStream("sqlmap.xml")));
        File configFile = new File("E:\\javaIDE\\ideawork\\xbb\\deyong-manager-pojo\\src\\main\\generatorConfig.xml");

        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
    public static void main(String[] args) throws Exception {

        GeneratorSqlMap sqlMap = new GeneratorSqlMap();
        sqlMap.generator();
    }
}
