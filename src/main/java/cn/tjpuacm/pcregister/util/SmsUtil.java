package cn.tjpuacm.pcregister.util;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 短信服务工具
 *
 * @author ningxy
 * @date 2018-10-23 20:21
 */
@Slf4j
@Configuration
public class SmsUtil {
    private static int appId;
    private static String appKey;

    /**
     * 发送短信（单用户）
     *
     * @param templateId  模板编号
     * @param smsSign     签名
     * @param phoneNumber 目标手机号
     * @param params      参数列表，具体的元素个数和模板中变量个数必须一致
     * @return 状态，正常为"OK"
     */
    public static String sendSingleSMS(int templateId, String smsSign, String phoneNumber, ArrayList<String> params) {
        String resultString;
        try {
            SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
            SmsSingleSenderResult result = ssender.sendWithParam(
                    "86",
                    phoneNumber,
                    templateId,
                    params,
                    smsSign,
                    "",
                    ""
            );
            resultString = result.errMsg;
        } catch (HTTPException e) {
            // HTTP响应码错误
            log.error(e.getReason());
            resultString = "HTTPException";
        } catch (JSONException e) {
            // json解析错误
            log.error(e.getMessage());
            resultString = "JSONException";
        } catch (IOException e) {
            // 网络IO错误
            log.error(e.getMessage());
            resultString = "IOException";
        }
        return resultString;
    }

    public static String sendSingleSMS(int templateId, String smsSign, String phoneNumber, String... params) {
        return sendSingleSMS(templateId, smsSign, phoneNumber, new ArrayList<String>(Arrays.asList(params)));
    }

    @Value("${smsService.appid}")
    public void setAppId(int appId) {
        SmsUtil.appId = appId;
    }

    @Value("${smsService.appkey}")
    public void setAppKey(String appKey) {
        SmsUtil.appKey = appKey;
    }
}
