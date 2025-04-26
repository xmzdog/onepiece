package com.onepiece.xmz.types.domain.activity.service.trial.thread;



import com.onepiece.xmz.types.domain.activity.adapter.repository.IActivityRepository;
import com.onepiece.xmz.types.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.onepiece.xmz.types.domain.activity.model.valobj.SCSkuActivityVO;
import com.onepiece.xmz.types.domain.activity.model.valobj.SkuVO;

import java.util.concurrent.Callable;

/**
 * @description 查询营销配置任务
 */
public class QueryGroupBuyActivityDiscountVOThreadTask implements Callable<GroupBuyActivityDiscountVO> {

    /**
     * 来源
     */
    private final String source;

    /**
     * 渠道
     */
    private final String channel;

    /** 商品ID */
    private String goodsId;

    /**
     * 活动仓储
     */
    private final IActivityRepository activityRepository;

    public QueryGroupBuyActivityDiscountVOThreadTask(String source, String channel,String goodsId, IActivityRepository activityRepository) {
        this.source = source;
        this.channel = channel;
        this.goodsId = goodsId;
        this.activityRepository = activityRepository;
    }

    @Override
    public GroupBuyActivityDiscountVO call() throws Exception {
        // 1. 通过商品的id ，查询出他的活动id
        SCSkuActivityVO scSkuActivityVO = activityRepository.querySCSkuActivityBySCGoodsId(source, channel, goodsId);
        if (null == scSkuActivityVO) return null;
        return activityRepository.queryGroupBuyActivityDiscountVO(scSkuActivityVO.getActivityId());
    }


}
