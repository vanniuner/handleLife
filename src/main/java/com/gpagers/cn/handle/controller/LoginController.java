package com.gpagers.cn.handle.controller;

import com.gpagers.cn.handle.dao.TbTingsMajorMapper;
import com.gpagers.cn.handle.model.SimpleRsult;
import com.gpagers.cn.handle.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public SimpleRsult login(@RequestParam("code") String code) {
        SimpleRsult sr = new SimpleRsult();
        try {
            String url = String.format(code2session, appId,secret,code);
            String response = HttpClientUtil.doGet(url);
            sr.setData(response);
            sr.setCode(200);
        }catch (Exception e){
            logger.error("error",e);
            sr.setCode(500);
            sr.setMessage(SimpleRsult.systemError);
        }
        return sr;
    }




}
