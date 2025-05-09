package com.onepiece.xmz.types.dao;

import com.onepiece.xmz.types.dao.po.GroupBuyDiscount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/23
 * Time: 11:29
 * Description: No Description
 */
@Mapper
public interface IGroupBuyDiscountDao {

    List<GroupBuyDiscount> queryGroupBuyDiscountList();

    GroupBuyDiscount queryGroupBuyActivityDiscountByDiscountId(String discountId);
}
