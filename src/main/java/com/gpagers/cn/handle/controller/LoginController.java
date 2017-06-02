package com.gpagers.cn.handle.controller;

import com.alibaba.fastjson.JSONObject;
import com.gpagers.cn.handle.dao.TbTingsMajorMapper;
import com.gpagers.cn.handle.model.SimpleRsult;
import com.gpagers.cn.handle.util.GlobalCache;
import com.gpagers.cn.handle.util.HttpClientUtil;
import com.gpagers.cn.handle.util.RandomStr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/wx")
public class LoginController {

    static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    TbTingsMajorMapper majorMapper;

    static final String code2session = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Value("${wx.appId}")
    String appId;

    @Value("${wx.secret}")
    String secret;

    /**
     * 微信小程序静默登录
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login")
    public SimpleRsult login(HttpServletRequest request, @RequestParam("code") String code,
        @RequestParam(value = "sessionId",required = false) String sessionId) {
        SimpleRsult sr = new SimpleRsult();
        try {
            logger.info("sessionId:" + sessionId);
            if(sessionId==null || GlobalCache.getLoginData(sessionId)==null) {
                if(sessionId==null) {
                    sessionId = RandomStr.randomStr(12);
                    while(GlobalCache.getLoginData(sessionId)!=null){
                        sessionId = RandomStr.randomStr(12);
                    }
                }
                String url = String.format(code2session, appId,secret,code);
                String response = HttpClientUtil.doGet(url);
                String openid = JSONObject.parseObject(response).getString("openid");
                GlobalCache.putLoginData(request.getSession().getId(), openid);
            }
            sr.setData(sessionId);
            sr.setCode(200);
            sr.setMessage(SimpleRsult.success);
        }catch (Exception e){
            logger.error("error",e);
            sr.setCode(500);
            sr.setMessage(SimpleRsult.systemError);
        }
        return sr;
    }




}
