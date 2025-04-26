package com.onepiece.xmz.types.domain.activity.service.discount;

import com.onepiece.xmz.types.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/26
 * Time: 14:09
 * Description: 折扣计算服务
 */
public interface IDiscountCalculateService {

    /**
     * 折扣计算
     *
     * @param userId           用户ID
     * @param originalPrice    商品原始价格
     * @param groupBuyDiscount 折扣计划配置
     * @return 商品优惠价格
     */
    BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);

}

