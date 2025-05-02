package com.onepiece.xmz.types.domain.trade.service.factory;


import com.onepiece.xmz.types.design.framework.link.model2.LinkArmory;
import com.onepiece.xmz.types.design.framework.link.model2.chain.BusinessLinkedList;
import com.onepiece.xmz.types.domain.trade.model.entity.GroupBuyActivityEntity;
import com.onepiece.xmz.types.domain.trade.model.entity.TradeRuleCommandEntity;
import com.onepiece.xmz.types.domain.trade.model.entity.TradeRuleFilterBackEntity;
import com.onepiece.xmz.types.domain.trade.service.filter.ActivityUsabilityRuleFilter;
import com.onepiece.xmz.types.domain.trade.service.filter.UserTakeLimitRuleFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @description 交易规则过滤工厂
 */
@Slf4j
@Service
public class TradeRuleFilterFactory {

    @Bean("tradeRuleFilter")
    public BusinessLinkedList<TradeRuleCommandEntity, DynamicContext, TradeRuleFilterBackEntity> tradeRuleFilter(ActivityUsabilityRuleFilter activityUsabilityRuleFilter, UserTakeLimitRuleFilter userTakeLimitRuleFilter) {
        // 组装链
        LinkArmory<TradeRuleCommandEntity, DynamicContext, TradeRuleFilterBackEntity> linkArmory =
                new LinkArmory<>("交易规则过滤链", activityUsabilityRuleFilter, userTakeLimitRuleFilter);

        // 链对象
        return linkArmory.getLogicLink();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {

        private GroupBuyActivityEntity groupBuyActivity;

    }

}
