package com.gpagers.cn.handle.controller;

import com.github.pagehelper.PageHelper;
import com.gpagers.cn.handle.dao.TbTingsMajorMapper;
import com.gpagers.cn.handle.model.SimpleRsult;
import com.gpagers.cn.handle.model.TbTingsMajor;
import com.gpagers.cn.handle.model.TbTingsMajorExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/tings")
public class InvokeController {

    static Logger logger = LoggerFactory.getLogger(InvokeController.class);

    @Autowired
    TbTingsMajorMapper majorMapper;

    static int pageSize = 15;

    /**
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "search")
    public SimpleRsult score(@RequestParam("search") String search,@RequestParam("pageNum") Integer pageNum) {
        SimpleRsult sr = new SimpleRsult();
        try {
            sr.setMessage(SimpleRsult.success);
            TbTingsMajorExample example = new TbTingsMajorExample();
            TbTingsMajorExample.Criteria criteria = example.createCriteria();
            criteria.andTitleLike(search+"%");
            PageHelper.startPage(pageNum, pageSize);
            sr.setData(majorMapper.selectByExample(example));
            sr.setCode(200);
        }catch (Exception e){
            logger.error("error",e);
            sr.setCode(500);
            sr.setMessage(SimpleRsult.systemError);
        }
        return sr;
    }

    /**
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "content")
    public SimpleRsult content(HttpServletResponse response,@RequestParam("id") Integer id) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        SimpleRsult sr = new SimpleRsult();
        sr.setCode(200);
        try{
            TbTingsMajor major = majorMapper.selectByPrimaryKey(id);
            sr.setData(major);
            sr.setMessage("操作成功");
        }catch (Exception e){
            sr.setCode(500);
            sr.setMessage(SimpleRsult.systemError);
        }
        return sr;
    }

    /**
     * @param major
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "doc/update")
    public SimpleRsult update(HttpServletResponse response, TbTingsMajor major) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        SimpleRsult sr = new SimpleRsult();
        sr.setCode(200);

        try {
            if(major.getId()==null){
                majorMapper.insert(major);
            }else {
                majorMapper.updateByPrimaryKeySelective(major);
            }
            sr.setMessage("操作成功");
        }catch (Exception e){
            sr.setCode(500);
            sr.setMessage(SimpleRsult.systemError);
        }
        return sr;
    }



}
