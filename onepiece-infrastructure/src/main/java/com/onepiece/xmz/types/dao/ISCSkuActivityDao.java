package com.onepiece.xmz.types.dao;

import com.onepiece.xmz.types.dao.po.SCSkuActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 渠道商品活动配置关联表Dao
 */
@Mapper
public interface ISCSkuActivityDao {

    SCSkuActivity querySCSkuActivityBySCGoodsId(SCSkuActivity scSkuActivity);

}
