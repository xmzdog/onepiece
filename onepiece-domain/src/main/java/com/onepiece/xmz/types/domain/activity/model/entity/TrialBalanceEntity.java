package com.onepiece.xmz.types.domain.activity.model.entity;

import com.onepiece.xmz.types.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/23
 * Time: 23:26
 * Description: 试算结果实体对象（给用户展示拼团可获得的优惠信息）
 * 试算：在用户真正下单/参与拼团之前，先给出一份预估的优惠结果或者模拟计算结果，帮助用户提前了解参与这个拼团活动能享受什么样的优惠。
 *
 * Trial：表示试算、预估、模拟。
 * Balance：这里不指财务结算，而是“拼团优惠的计算结果”。
 * Entity：实体类，用于封装一组与业务相关的数据。
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrialBalanceEntity {

    /** 商品ID */
    private String goodsId;
    /** 商品名称 */
    private String goodsName;
    /** 原始价格 */
    private BigDecimal originalPrice;
    /** 折扣价格 */
    private BigDecimal deductionPrice;
    /** 拼团目标数量 */
    private Integer targetCount;
    // 支付金额
    private BigDecimal payPrice;
    /** 拼团开始时间 */
    private Date startTime;
    /** 拼团结束时间 */
    private Date endTime;
    /** 是否可见拼团 */
    private Boolean isVisible;
    /** 是否可参与进团 */
    private Boolean isEnable;
    /** 活动配置信息 */
    private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;


}
