package com.gpagers.cn.handle.controller;

import com.gpagers.cn.handle.dao.TbTingsMajorMapper;
import com.gpagers.cn.handle.model.SimpleRsult;
import com.gpagers.cn.handle.model.TbTingsMajorExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/tings")
public class InvokeController {

    static Logger logger = LoggerFactory.getLogger(InvokeController.class);

    @Autowired
    TbTingsMajorMapper majorMapper;

    /**
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "search")
    public SimpleRsult score(@RequestParam("search") String search) {
        SimpleRsult sr = new SimpleRsult();
        try {
            sr.setMessage(SimpleRsult.success);
            TbTingsMajorExample example = new TbTingsMajorExample();
            TbTingsMajorExample.Criteria criteria = example.createCriteria();
            criteria.andTitleLike(search+"%");
            sr.setData(majorMapper.selectByExample(example));
        }catch (Exception e){
            logger.error("error",e);
            sr.setCode(500);
            sr.setMessage(SimpleRsult.systemError);
        }
        return sr;
    }

}
