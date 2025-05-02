package com.onepiece.xmz.test.types.link.rule01.logic;


import com.onepiece.xmz.test.types.link.rule02.factory.Rule02TradeRuleFactory;
import com.onepiece.xmz.types.design.framework.link.model1.AbstractLogicLink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RuleLogic101 extends AbstractLogicLink<String, Rule02TradeRuleFactory.DynamicContext, String> {

    @Override
    public String apply(String requestParameter, Rule02TradeRuleFactory.DynamicContext dynamicContext) throws Exception {

        log.info("link model01 RuleLogic101");

        return next(requestParameter, dynamicContext);
    }

}
