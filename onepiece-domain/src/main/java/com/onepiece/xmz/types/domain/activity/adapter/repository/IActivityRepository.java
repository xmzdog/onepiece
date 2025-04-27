package com.onepiece.xmz.types.domain.activity.adapter.repository;

import com.onepiece.xmz.types.domain.activity.model.entity.UserGroupBuyOrderDetailEntity;
import com.onepiece.xmz.types.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.onepiece.xmz.types.domain.activity.model.valobj.SCSkuActivityVO;
import com.onepiece.xmz.types.domain.activity.model.valobj.SkuVO;
import com.onepiece.xmz.types.domain.activity.model.valobj.TeamStatisticVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/24
 * Time: 20:58
 * Description: 活动仓储
 */
public interface IActivityRepository {


    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId);
//    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(String source, String channel);

    SkuVO querySkuByGoodsId(String goodsId);

    SCSkuActivityVO querySCSkuActivityBySCGoodsId(String source, String channel, String goodsId);
//
    boolean isTagCrowdRange(String tagId, String userId);
//
//    boolean downgradeSwitch();
//
//    boolean cutRange(String userId);
//
//    List<UserGroupBuyOrderDetailEntity> queryInProgressUserGroupBuyOrderDetailListByOwner(Long activityId, String userId, Integer ownerCount);
//
//    List<UserGroupBuyOrderDetailEntity> queryInProgressUserGroupBuyOrderDetailListByRandom(Long activityId, String userId, Integer randomCount);
//
//    TeamStatisticVO queryTeamStatisticByActivityId(Long activityId);


}
