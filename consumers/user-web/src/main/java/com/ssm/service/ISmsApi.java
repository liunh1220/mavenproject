package com.ssm.service;

import java.util.List;
import java.util.Map;

import com.ssm.entity.SmsState;
import com.ssm.entity.vo.SmsVo;

/**
 * 短信发送API
 *
 * @version  
 */
public interface ISmsApi {
    /**
     * 短信发送(实时发送)
     * <b>发送短信内容中需要添加已报备的签名，格式【中文汉字】</b>
     * @param smsVo
     * @return
     */
    public SmsState sendNow(SmsVo smsVo);
    
    /**
     * 短信延迟发送(延迟发送,写入数据库job发送)
     * @param smsVo
     * @return
     */
    public SmsState sendByJob(SmsVo smsVo);
    
    /**
     * 
     * @param map
     * @return Map<orderNo,Map<phone,smsState>>
     */
    public Map<String,Map<String,SmsState>> sendByJobBatch(List<SmsVo> smsVos);
    
    /**
     * 多任务、线程池的方式发送sms
     * @param smsVos
     * @return
     */
    public void sendSmsMultiTaskThreadPool(List<SmsVo> smsVos);

    
}