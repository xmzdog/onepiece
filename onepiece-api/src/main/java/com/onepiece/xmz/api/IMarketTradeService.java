package com.onepiece.xmz.api;


import com.onepiece.xmz.api.dto.LockMarketPayOrderRequestDTO;
import com.onepiece.xmz.api.dto.LockMarketPayOrderResponseDTO;
import com.onepiece.xmz.api.dto.SettlementMarketPayOrderRequestDTO;
import com.onepiece.xmz.api.dto.SettlementMarketPayOrderResponseDTO;
import com.onepiece.xmz.api.response.Response;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 营销交易服务接口
 * @create 2025-01-11 13:49
 */
public interface IMarketTradeService {

    /**
     * 营销锁单
     *
     * @param requestDTO 锁单商品信息
     * @return 锁单结果信息
     */
    Response<LockMarketPayOrderResponseDTO> lockMarketPayOrder(LockMarketPayOrderRequestDTO requestDTO);

    /**
     * 营销结算
     *
     * @param requestDTO 结算商品信息
     * @return 结算结果信息
     */
    Response<SettlementMarketPayOrderResponseDTO> settlementMarketPayOrder(SettlementMarketPayOrderRequestDTO requestDTO);

}
