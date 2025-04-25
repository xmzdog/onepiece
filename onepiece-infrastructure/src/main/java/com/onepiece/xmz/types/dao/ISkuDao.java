package com.onepiece.xmz.types.dao;

import com.onepiece.xmz.types.dao.po.Sku;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 商品查询
 */
@Mapper
public interface ISkuDao {

    Sku querySkuByGoodsId(String goodsId);

}
