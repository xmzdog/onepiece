package com.onepiece.xmz.types.dao;

import com.onepiece.xmz.types.dao.po.CrowdTagsJob;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 人群标签任务
 */
@Mapper
public interface ICrowdTagsJobDao {

    CrowdTagsJob queryCrowdTagsJob(CrowdTagsJob crowdTagsJobReq);

}
