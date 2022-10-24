package com.dabing.springcloud.controller;

import com.dabing.springcloud.entities.CommonResult;
import com.dabing.springcloud.entities.Payment;
import com.dabing.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j  //日志
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    /**
     * 端口号
     * 查看负载均衡效果
     */
    @Value("${server.port}")
    private String serverPort;

    /**
     * 服务发现
     * 获取服务信息
     */
    @Resource
    private DiscoveryClient discoveryClient;  //springframework的DiscoveryClient（不要导错包了）

    @GetMapping("/payment/discovery")
    public Object discovery(){
        //获取服务列表的信息
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*******element：" + element);
        }

        //获取CLOUD-PAYMENT-SERVICE服务的所有具体实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            //getServiceId服务器id getHost主机名称 getPort端口号  getUri地址
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }


    //前后端分离，所以不能直接返回对象，数据要先经过CommonResult封装再返回
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("******插入的数据为：" + payment);
        log.info("******插入结果：" + result);

        if(result > 0){
            //插入成功
            return new CommonResult(200, "插入数据库成功,serverPort:"+serverPort, result);
        }else{
            return new CommonResult(444, "插入数据库失败,serverPort:"+serverPort);
        }
    }


    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询结果：" + payment);

        if(payment != null){
            //查询成功
            return new CommonResult(200, "查询成功,serverPort:"+serverPort, payment);
        }else{
            return new CommonResult(444, "没有对应记录，查询ID："+ id +"  serverPort:"+serverPort);
        }
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        System.out.println("serverPort = " + serverPort);
        try { TimeUnit.SECONDS.sleep(3); }catch (Exception e) {e.printStackTrace();} //单位秒
        return "测试feign超时，端口："+serverPort;
    }
}
