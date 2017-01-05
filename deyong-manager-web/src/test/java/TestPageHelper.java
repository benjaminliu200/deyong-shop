import com.deyong.mapper.TbItemMapper;
import com.deyong.pojo.TbItem;
import com.deyong.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by benjamin on 2017/1/4.
 */
public class TestPageHelper {
    @Test
    public void testPageHelper() throws Exception {
        // 1 获取mapper 代理对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
        // 2 设置分页
        PageHelper.startPage(1, 30);

        // 3 执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = tbItemMapper.selectByExample(example);

        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();
        System.out.println("总记录数" + total + ", 总页数" + pages);
    }

    @Test
    public void testSqlSession() {
        // 1 获取mapper 代理对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
        // 2 设置分页
        PageHelper.startPage(1, 30);

        // 3 执行查询
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(605616l);
        System.out.println(tbItem);
    }
}
