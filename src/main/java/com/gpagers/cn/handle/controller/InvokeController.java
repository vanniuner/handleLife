package com.gpagers.cn.handle.controller;

import com.gpagers.cn.handle.model.SimpleRsult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/invoke")
public class InvokeController {

    static Logger logger = LoggerFactory.getLogger(InvokeController.class);

    /**
     * 触发score导入的数据
     * @param date
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "score")
    public SimpleRsult score(String date) {
        SimpleRsult sr = new SimpleRsult();
        try {
            sr.setMessage(SimpleRsult.success);
        }catch (Exception e){
            logger.error("error",e);
            sr.setCode(500);
            sr.setMessage(SimpleRsult.systemError);
        }
        return sr;
    }

}
