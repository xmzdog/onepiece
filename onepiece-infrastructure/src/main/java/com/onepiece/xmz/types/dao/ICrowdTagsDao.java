package com.onepiece.xmz.types.dao;


import com.onepiece.xmz.types.dao.po.CrowdTags;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 人群标签
 */
@Mapper
public interface ICrowdTagsDao {

    void updateCrowdTagsStatistics(CrowdTags crowdTagsReq);

}
