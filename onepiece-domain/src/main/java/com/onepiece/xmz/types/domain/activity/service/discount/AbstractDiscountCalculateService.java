package com.onepiece.xmz.types.domain.activity.service.discount;


import com.onepiece.xmz.types.domain.activity.model.valobj.DiscountTypeEnum;
import com.onepiece.xmz.types.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/26
 * Time: 14:09
 * Description: 则扣计算服务抽象类
 */
public abstract class AbstractDiscountCalculateService implements IDiscountCalculateService {

    @Override
    public BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        // 1. 人群标签过滤
        if (DiscountTypeEnum.TAG.equals(groupBuyDiscount.getDiscountType())){
            boolean isCrowdRange = filterTagId(userId, groupBuyDiscount.getTagId());
            if (!isCrowdRange) return originalPrice;
        }
        // 2. 折扣优惠计算
        return doCalculate(originalPrice, groupBuyDiscount);
    }

    // 人群过滤 - 限定人群优惠
    private boolean filterTagId(String userId, String tagId) {
        // todo xiaofuge 后续开发这部分
        return true;
    }

    protected abstract BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);

}
