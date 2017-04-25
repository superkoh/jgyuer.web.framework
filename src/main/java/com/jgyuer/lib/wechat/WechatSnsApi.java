package com.jgyuer.lib.wechat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.jgyuer.lib.wechat.bean.WechatError;
import com.jgyuer.lib.wechat.bean.WechatSession;
import com.jgyuer.lib.wechat.bean.WechatUserInfo;
import com.jgyuer.lib.wechat.exception.WechatSnsException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by KOH on 2017/4/24.
 * <p>
 * webFramework
 */
public class WechatSnsApi {

    private static final Logger logger = LoggerFactory.getLogger(WechatSnsApi.class);

    private static final String JSCODE2SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    private String appId;
    private String secret;
    private OkHttpClient httpClient;
    private ObjectMapper objectMapper;

    public WechatSnsApi(String appId, String secret) {
        this.appId = appId;
        this.secret = secret;
    }

    public WechatSession jscode2session(String code) throws WechatSnsException {
        Request request = new Request.Builder()
                .url(String.format(JSCODE2SESSION_URL, appId, secret, code))
                .get()
                .build();
        try {
            Response response = getHttpClient().newCall(request).execute();
            String resStr = response.body().string();
            try {
                WechatError error = getObjectMapper().readValue(resStr, WechatError.class);
                if (null != error.getErrcode()) {
                    throw new WechatSnsException(error.getErrcode(), error.getErrmsg());
                }
            } catch (IOException ignored) {
            }
            return getObjectMapper()
                    .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                    .readValue(resStr, WechatSession.class);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new WechatSnsException(-1, e.getMessage());
        }
    }

    public WechatUserInfo decryptUserInfo(String encryptedData, String iv, String sessionKey) {
        byte[] res = WechatAESUtils.decrypt(Base64.decodeBase64(encryptedData),
                Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
        if (null != res && res.length > 0) {
            try {
                String resStr = new String(res, "UTF8");
                return getObjectMapper()
                        .setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
                        .readValue(resStr, WechatUserInfo.class);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    private OkHttpClient getHttpClient() {
        if (null == httpClient) {
            httpClient = new OkHttpClient();
        }
        return httpClient;
    }

    private ObjectMapper getObjectMapper() {
        if (null == objectMapper) {
            objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return objectMapper;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
