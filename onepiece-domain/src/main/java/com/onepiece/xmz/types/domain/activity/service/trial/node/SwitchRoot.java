package com.onepiece.xmz.types.domain.activity.service.trial.node;

import com.onepiece.xmz.types.design.framework.tree.StrategyHandler;
import com.onepiece.xmz.types.domain.activity.model.entity.MarketProductEntity;
import com.onepiece.xmz.types.domain.activity.model.entity.TrialBalanceEntity;
import com.onepiece.xmz.types.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.onepiece.xmz.types.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/23
 * Time: 23:42
 * Description: 开关节点
 */
@Slf4j
@Service
public class SwitchRoot extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {
    @Override
    public TrialBalanceEntity apply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return null;
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return null;
    }
}
