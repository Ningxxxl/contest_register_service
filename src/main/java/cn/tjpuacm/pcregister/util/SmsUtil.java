package cn.tjpuacm.pcregister.util;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 短信服务工具
 *
 * @author ningxy
 * @date 2018-10-23 20:21
 */
@Configuration
public class SmsUtil {
    private static int appId;
    private static String appKey;

    /**
     * 发送短信（单用户）
     *
     * @param params      参数列表，具体的元素个数和模板中变量个数必须一致
     * @param phoneNumber 目标手机号
     * @param templateId  模板编号
     * @param smsSign 签名
     * @return 状态，正常为"OK"
     */
    public static String sendSingleSMS(ArrayList<String> params, String phoneNumber, int templateId, String smsSign) {
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
            e.printStackTrace();
            resultString = "HTTPException";
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
            resultString = "JSONException";
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
            resultString = "IOException";
        }
        return resultString;
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
