package com.onepiece.xmz.types.domain.activity.service.trial.node;


import com.alibaba.fastjson2.JSON;
import com.onepiece.xmz.types.design.framework.tree.StrategyHandler;
import com.onepiece.xmz.types.domain.activity.model.entity.MarketProductEntity;
import com.onepiece.xmz.types.domain.activity.model.entity.TrialBalanceEntity;
import com.onepiece.xmz.types.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.onepiece.xmz.types.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.onepiece.xmz.types.enums.ResponseCode;
import com.onepiece.xmz.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 异常节点处理；无营销、流程降级、超时调用等，都可以路由到 ErrorNode 节点统一处理
 * @create 2025-01-01 13:47
 */
@Slf4j
@Service
public class ErrorNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("拼团商品查询试算服务-NoMarketNode userId:{} requestParameter:{}", requestParameter.getUserId(), JSON.toJSONString(requestParameter));

        // 无营销配置
        if (null == dynamicContext.getGroupBuyActivityDiscountVO() || null == dynamicContext.getSkuVO()) {
            log.info("商品无拼团营销配置 {}", requestParameter.getGoodsId());
            throw new AppException(ResponseCode.E0002.getCode(), ResponseCode.E0002.getInfo());
        }

        return TrialBalanceEntity.builder().build();
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return defaultStrategyHandler;
    }

}
