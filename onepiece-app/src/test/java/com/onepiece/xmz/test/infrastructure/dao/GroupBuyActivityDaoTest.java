package com.onepiece.xmz.test.infrastructure.dao;

import com.onepiece.xmz.Application;
import com.onepiece.xmz.types.dao.IGroupBuyActivityDao;
import com.onepiece.xmz.types.dao.po.GroupBuyActivity;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/23
 * Time: 17:23
 * Description: No Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class GroupBuyActivityDaoTest {

    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;

    @Test
    public void testInsertGroupBuyActivity() {
        List<GroupBuyActivity> groupBuyActivities = groupBuyActivityDao.queryGroupBuyActivityList();
        log.info("测试结果:{}", JSONArray.toJSONString(groupBuyActivities));
    }
}
