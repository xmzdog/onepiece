package com.onepiece.xmz.types.domain.activity.service.impl;

import com.onepiece.xmz.types.design.framework.tree.StrategyHandler;
import com.onepiece.xmz.types.domain.activity.model.entity.MarketProductEntity;
import com.onepiece.xmz.types.domain.activity.model.entity.TrialBalanceEntity;
import com.onepiece.xmz.types.domain.activity.service.IIndexGroupBuyMarketService;
import com.onepiece.xmz.types.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/23
 * Time: 23:51
 * Description: 首页营销服务
 */
@Service
public class IndexGroupBuyMarketServiceImpl implements IIndexGroupBuyMarketService {
    @Resource
    private DefaultActivityStrategyFactory defaultActivityStrategyFactory;

    @Override
    public TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception {

        StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> strategyHandler = defaultActivityStrategyFactory.strategyHandler();

        TrialBalanceEntity trialBalanceEntity = strategyHandler.apply(marketProductEntity, new DefaultActivityStrategyFactory.DynamicContext());

        return trialBalanceEntity;
    }

}
