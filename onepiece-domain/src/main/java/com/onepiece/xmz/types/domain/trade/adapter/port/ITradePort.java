package com.onepiece.xmz.types.domain.trade.adapter.port;


import com.onepiece.xmz.types.domain.trade.model.entity.NotifyTaskEntity;

/**
 * @description 交易接口服务接口
 */
public interface ITradePort {

    String groupBuyNotify(NotifyTaskEntity notifyTask) throws Exception;

}
