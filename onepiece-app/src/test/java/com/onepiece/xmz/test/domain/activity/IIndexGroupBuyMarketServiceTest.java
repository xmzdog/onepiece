package com.onepiece.xmz.test.domain.activity;

import com.alibaba.fastjson2.JSON;
import com.onepiece.xmz.types.Application;
import com.onepiece.xmz.types.domain.activity.model.entity.MarketProductEntity;
import com.onepiece.xmz.types.domain.activity.model.entity.TrialBalanceEntity;
import com.onepiece.xmz.types.domain.activity.service.IIndexGroupBuyMarketService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.internal.Classes;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class IIndexGroupBuyMarketServiceTest {

    @Resource
    private IIndexGroupBuyMarketService indexGroupBuyMarketService;

//    @Test
//    public void test_indexMarketTrial() throws Exception {
//        MarketProductEntity marketProductEntity = new MarketProductEntity();
//        marketProductEntity.setUserId("xiaofuge");
//        marketProductEntity.setSource("s01");
//        marketProductEntity.setChannel("c01");
//        marketProductEntity.setGoodsId("9890001");
//
//        TrialBalanceEntity trialBalanceEntity = indexGroupBuyMarketService.indexMarketTrial(marketProductEntity);
//        log.info("请求参数:{}", JSON.toJSONString(marketProductEntity));
//        log.info("返回结果:{}", JSON.toJSONString(trialBalanceEntity));
//    }

    @Test
    public void test_indexMarketTrial() throws Exception {
        MarketProductEntity marketProductEntity = new MarketProductEntity();
        marketProductEntity.setUserId("xiaofuge");
        marketProductEntity.setSource("s01");
        marketProductEntity.setChannel("c01");
        marketProductEntity.setGoodsId("9890001");

        TrialBalanceEntity trialBalanceEntity = indexGroupBuyMarketService.indexMarketTrial(marketProductEntity);
        log.info("请求参数:{}", JSON.toJSONString(marketProductEntity));
        log.info("返回结果:{}", JSON.toJSONString(trialBalanceEntity));
    }

    @Test
    public void test_indexMarketTrial_error() throws Exception {
        MarketProductEntity marketProductEntity = new MarketProductEntity();
        marketProductEntity.setUserId("xiaofuge");
        marketProductEntity.setSource("s01");
        marketProductEntity.setChannel("c01");
        marketProductEntity.setGoodsId("9890002");

        TrialBalanceEntity trialBalanceEntity = indexGroupBuyMarketService.indexMarketTrial(marketProductEntity);
        log.info("请求参数:{}", JSON.toJSONString(marketProductEntity));
        log.info("返回结果:{}", JSON.toJSONString(trialBalanceEntity));
    }


}