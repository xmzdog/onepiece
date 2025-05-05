package com.onepiece.xmz.types.dao;

import com.onepiece.xmz.types.dao.po.NotifyTask;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 回调任务
 * @create 2025-01-26 18:23
 */
@Mapper
public interface INotifyTaskDao {

    void insert(NotifyTask notifyTask);

}
