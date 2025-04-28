package com.onepiece.xmz.types.dcc;

import com.onepiece.xmz.types.annotations.DCCValue;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/28
 * Time: 22:07
 * Description:  是一个典型的动态控制开关+灰度流量控制服务类，结合了注解、反射、哈希分桶等机制，可以用来做降级保护、灰度发布、A/B实验等场景。
 * 根据DCC配置，判断一些系统开关。      根据用户ID的hash值，决定是否命中某种“抽样降级”逻辑
 */
@Service
public class DCCService {

    /**
     * 默认0 标识降级开关
     */
    @DCCValue("downgradeSwitch:0")
    private String downgradeSwitch;

    /**
     * 默认值100 ，通过userid 进行哈希分流，这里表示所任人都覆盖（符合）
     */
    @DCCValue("cutRange:100")
    private String cutRange;

    public boolean isDowngradeSwitch() {
        return "1".equals(downgradeSwitch);
    }

    /**
     * 判断当前用户是否在这个分流的范围内
     * @param userId
     * @return
     */
    public boolean isCutRange(String userId) {
        int hashcode = Math.abs(userId.hashCode());
        int lastTwoDigits = hashcode % 100;

        return lastTwoDigits <= Integer.parseInt(cutRange);
    }
}
