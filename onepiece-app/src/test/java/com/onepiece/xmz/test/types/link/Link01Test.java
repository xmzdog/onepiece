package com.onepiece.xmz.test.types.link;


import com.alibaba.fastjson.JSON;
import com.onepiece.xmz.test.types.link.rule01.factory.Rule01TradeRuleFactory;
import com.onepiece.xmz.test.types.link.rule02.factory.Rule02TradeRuleFactory;
import com.onepiece.xmz.types.design.framework.link.model1.ILogicLink;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Link01Test {

    @Resource
    public Rule01TradeRuleFactory rule01TradeRuleFactory;

    @Test
    public void test_model01_01() throws Exception {
        ILogicLink<String, Rule02TradeRuleFactory.DynamicContext, String> logicLink = rule01TradeRuleFactory.openLogicLink();
        String logic = logicLink.apply("123", new Rule02TradeRuleFactory.DynamicContext());
        log.info("测试结果:{}", JSON.toJSONString(logic));
    }

    public void testxmz01(){
        Stack<Integer> integers1 = new Stack<>();
        Stack<Integer> integers2 = new Stack<>();

        Queue<Integer> integers = new LinkedList<>();
        Deque<Integer> integers3 = new LinkedList<>();

    }

}
