package com.onepiece.xmz.types.domain.activity.service.trial.factory;

import com.onepiece.xmz.types.design.framework.tree.StrategyHandler;
import com.onepiece.xmz.types.domain.activity.model.entity.MarketProductEntity;
import com.onepiece.xmz.types.domain.activity.model.entity.TrialBalanceEntity;
import com.onepiece.xmz.types.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.onepiece.xmz.types.domain.activity.model.valobj.SkuVO;
import com.onepiece.xmz.types.domain.activity.service.trial.node.RootNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/23
 * Time: 23:39
 * Description: 活动策略工厂
 */
@Service
public class DefaultActivityStrategyFactory {


    private final RootNode rootNode;

    public DefaultActivityStrategyFactory(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    public StrategyHandler<MarketProductEntity, DynamicContext, TrialBalanceEntity> strategyHandler() {
        return rootNode;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        // 拼团活动营销配置值对象
        private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;
        // 商品信息
        private SkuVO skuVO;
        // 折扣价格
        private BigDecimal deductionPrice;
        // 支付金额
        private BigDecimal payPrice;
        // 活动可见限制
        private boolean isVisible;

        private boolean isEnable;

    }

}
