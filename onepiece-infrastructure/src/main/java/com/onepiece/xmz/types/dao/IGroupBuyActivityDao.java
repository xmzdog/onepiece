package com.onepiece.xmz.types.dao;

import com.onepiece.xmz.types.dao.po.GroupBuyActivity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/23
 * Time: 11:22
 * Description: No Description
 */
@Mapper
public interface IGroupBuyActivityDao {

    List<GroupBuyActivity> queryGroupBuyActivityList();
    GroupBuyActivity queryValidGroupBuyActivity(GroupBuyActivity groupBuyActivity);
    GroupBuyActivity queryValidGroupBuyActivityId(Long activityId);
    GroupBuyActivity queryGroupBuyActivityByActivityId(Long activityId);
}
