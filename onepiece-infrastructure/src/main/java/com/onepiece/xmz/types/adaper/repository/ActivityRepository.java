package com.onepiece.xmz.types.adaper.repository;


import com.onepiece.xmz.types.dao.IGroupBuyActivityDao;
import com.onepiece.xmz.types.dao.IGroupBuyDiscountDao;
import com.onepiece.xmz.types.dao.ISCSkuActivityDao;
import com.onepiece.xmz.types.dao.ISkuDao;
import com.onepiece.xmz.types.dao.po.GroupBuyActivity;
import com.onepiece.xmz.types.dao.po.GroupBuyDiscount;
import com.onepiece.xmz.types.dao.po.SCSkuActivity;
import com.onepiece.xmz.types.dao.po.Sku;
import com.onepiece.xmz.types.dcc.DCCService;
import com.onepiece.xmz.types.domain.activity.adapter.repository.IActivityRepository;
import com.onepiece.xmz.types.domain.activity.model.valobj.DiscountTypeEnum;
import com.onepiece.xmz.types.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.onepiece.xmz.types.domain.activity.model.valobj.SCSkuActivityVO;
import com.onepiece.xmz.types.domain.activity.model.valobj.SkuVO;
import com.onepiece.xmz.types.redis.IRedisService;
import org.redisson.api.RBitSet;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @description 活动仓储
 */
@Repository
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;
    @Resource
    private IGroupBuyDiscountDao groupBuyDiscountDao;
    @Resource
    private ISCSkuActivityDao iscSkuActivityDao;
    @Resource
    private DCCService dccService;


    @Resource
    private ISkuDao skuDao;

    @Resource
    private IRedisService redisService;

    @Override
    public GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId) {
        // 根据SC渠道值查询配置中最新的1个有效的活动
//        GroupBuyActivity groupBuyActivityReq = new GroupBuyActivity();
//        groupBuyActivityReq.setActivityId(activityId);
//        groupBuyActivityReq.setSource(source);
//        groupBuyActivityReq.setChannel(channel);
        GroupBuyActivity groupBuyActivityRes = groupBuyActivityDao.queryValidGroupBuyActivityId(activityId);

        String discountId = groupBuyActivityRes.getDiscountId();

        GroupBuyDiscount groupBuyDiscountRes = groupBuyDiscountDao.queryGroupBuyActivityDiscountByDiscountId(discountId);
        GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount = GroupBuyActivityDiscountVO.GroupBuyDiscount.builder()
                .discountName(groupBuyDiscountRes.getDiscountName())
                .discountDesc(groupBuyDiscountRes.getDiscountDesc())
                .discountType(DiscountTypeEnum.get(groupBuyDiscountRes.getDiscountType()))
                .marketPlan(groupBuyDiscountRes.getMarketPlan())
                .marketExpr(groupBuyDiscountRes.getMarketExpr())
                .tagId(groupBuyDiscountRes.getTagId())
                .build();

        return GroupBuyActivityDiscountVO.builder()
                .activityId(groupBuyActivityRes.getActivityId())
                .activityName(groupBuyActivityRes.getActivityName())
                .groupBuyDiscount(groupBuyDiscount)
                .groupType(groupBuyActivityRes.getGroupType())
                .takeLimitCount(groupBuyActivityRes.getTakeLimitCount())
                .target(groupBuyActivityRes.getTarget())
                .validTime(groupBuyActivityRes.getValidTime())
                .status(groupBuyActivityRes.getStatus())
                .startTime(groupBuyActivityRes.getStartTime())
                .endTime(groupBuyActivityRes.getEndTime())
                .tagId(groupBuyActivityRes.getTagId())
                .tagScope(groupBuyActivityRes.getTagScope())
                .build();
    }

    @Override
    public SkuVO querySkuByGoodsId(String goodsId) {
        Sku sku = skuDao.querySkuByGoodsId(goodsId);
        if (null == sku) return null;
        return SkuVO.builder()
                .goodsId(sku.getGoodsId())
                .goodsName(sku.getGoodsName())
                .originalPrice(sku.getOriginalPrice())
                .build();
    }

    @Override
    public SCSkuActivityVO querySCSkuActivityBySCGoodsId(String source, String channel, String goodsId) {
        SCSkuActivity scSkuActivityVO = SCSkuActivity.builder()
                .goodsId(goodsId).build();
        SCSkuActivity scSkuActivity = iscSkuActivityDao.querySCSkuActivityBySCGoodsId(scSkuActivityVO);
        if (null == scSkuActivity) return null;
        return SCSkuActivityVO.builder()
                .goodsId(scSkuActivity.getGoodsId())
                .chanel(scSkuActivityVO.getChannel())
                .activityId(scSkuActivity.getActivityId())
                .source(scSkuActivity.getSource()).build();
    }

    @Override
    public boolean isTagCrowdRange(String tagId, String userId) {
        RBitSet bitSet = redisService.getBitSet(tagId);
        if (!bitSet.isExists() ) return false;
        return bitSet.get(redisService.getIndexFromUserId(userId));
    }

    @Override
    public boolean downgradeSwitch() {
        return dccService.isDowngradeSwitch();
    }

    @Override
    public boolean cutRange(String userId) {
        return dccService.isCutRange(userId);
    }

}
