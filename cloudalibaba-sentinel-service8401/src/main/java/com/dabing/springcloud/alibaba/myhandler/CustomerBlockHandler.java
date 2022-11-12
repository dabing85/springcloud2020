package com.dabing.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dabing.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handleException(BlockException exception){
        return new CommonResult(2020,"自定义限流处理信息.... CustomerBlockHandler --- 1");
    }

    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(2020,"自定义限流处理信息.... CustomerBlockHandler --- 2");
    }

    public static CommonResult blockHandlerMethod(BlockException exception){
        return new CommonResult(444,"自定义降级规则处理信息.... blockHandlerMethod --- ");
    }

    public static CommonResult fallbackMethod(BlockException exception){
        return new CommonResult(444,"异常处理信息.... fallbackMethod --- ");
    }
}
