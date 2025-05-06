package com.onepiece.xmz.types.domain.trade.service.lock.filter;


import com.onepiece.xmz.types.design.framework.link.model2.handler.ILogicHandler;
import com.onepiece.xmz.types.domain.trade.adapter.repository.ITradeRepository;
import com.onepiece.xmz.types.domain.trade.model.entity.GroupBuyActivityEntity;
import com.onepiece.xmz.types.domain.trade.model.entity.TradeLockRuleCommandEntity;
import com.onepiece.xmz.types.domain.trade.model.entity.TradeLockRuleFilterBackEntity;
import com.onepiece.xmz.types.domain.trade.service.lock.factory.TradeLockRuleFilterFactory;
import com.onepiece.xmz.types.enums.ResponseCode;
import com.onepiece.xmz.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description 用户参与限制，规则过滤
 */
@Slf4j
@Service
public class UserTakeLimitRuleFilter implements ILogicHandler<TradeLockRuleCommandEntity, TradeLockRuleFilterFactory.DynamicContext, TradeLockRuleFilterBackEntity> {

    @Resource
    private ITradeRepository repository;

    @Override
    public TradeLockRuleFilterBackEntity apply(TradeLockRuleCommandEntity requestParameter, TradeLockRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("交易规则过滤-用户参与次数校验{} activityId:{}", requestParameter.getUserId(), requestParameter.getActivityId());

        GroupBuyActivityEntity groupBuyActivity = dynamicContext.getGroupBuyActivity();

        // 查询用户在一个拼团活动上参与的次数
        Integer count = repository.queryOrderCountByActivityId(requestParameter.getActivityId(), requestParameter.getUserId());

        if (null != groupBuyActivity.getTakeLimitCount() && count >= groupBuyActivity.getTakeLimitCount()) {
            log.info("用户参与次数校验，已达可参与上限 activityId:{}", requestParameter.getActivityId());
            throw new AppException(ResponseCode.E0103);
        }

        return TradeLockRuleFilterBackEntity.builder()
                .userTakeOrderCount(count)
                .build();
    }

}
