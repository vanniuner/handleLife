package com.gpagers.cn.handle.velocity;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.ToolContext;
import org.apache.velocity.tools.ToolManager;

import java.io.StringWriter;
import java.util.Map;

/**
 * Created by fred on 16/9/14.
 */
public class MergeUtil {

    public static String parseTempUseContext(VelocityEngine velocityEngine,
                                             String temp, Map<String,Object> param) throws Exception {
        temp = "../views/"+temp;
        ToolManager manager = new ToolManager();
        manager.setVelocityEngine(velocityEngine);
        manager.autoConfigure(false);
        ToolContext context = manager.createContext();
        context.put("fmt", Class.forName("com.ximalaya.ops.rms.velocity.FormatVTL").newInstance());
        context.put("date", Class.forName("com.ximalaya.ops.rms.velocity.DateFormateVTL").newInstance());

        for (String key : param.keySet()) {
            context.put(key, param.get(key));
        }
        StringWriter writer = new StringWriter();
        velocityEngine.setProperty("file.resource.loader.cache",true);
        velocityEngine.setProperty("file.resource.loader.path", System.getProperty("user.dir"));
        velocityEngine.mergeTemplate(temp, "utf-8", context, writer);
        return writer.toString();
    }
}
