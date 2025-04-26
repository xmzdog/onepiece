package com.onepiece.xmz.types.dao;

import com.onepiece.xmz.types.dao.po.CrowdTagsDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 人群标签明细
 */
@Mapper
public interface ICrowdTagsDetailDao {

    void addCrowdTagsUserId(CrowdTagsDetail crowdTagsDetailReq);

}
