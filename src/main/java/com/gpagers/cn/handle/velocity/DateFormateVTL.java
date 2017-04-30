package com.gpagers.cn.handle.velocity;

import org.apache.velocity.tools.config.DefaultKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@DefaultKey("date")
public class DateFormateVTL {
	
	public String formateDate(Date date){
		if(date==null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public String formateDateDay(Date date){
		if(date==null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
}
