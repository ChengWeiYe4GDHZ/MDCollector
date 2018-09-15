package com.yqqtp.laoyouqian.task;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yqqtp.laoyouqian.engine.DataEngine;
import com.yqqtp.laoyouqian.entity.Bar;
import com.yqqtp.laoyouqian.gateway.ctp.DataCache;
import com.yqqtp.laoyouqian.utils.MongoDBUtil;

public class SaveBarTask implements Runnable
{
	public Logger log = LoggerFactory.getLogger(SaveBarTask.class);
	@Autowired
	private DataEngine dataEngine ;

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		while(true) {
			try {
				log.info("Bar——Save待处理行情数量:"+DataCache.SAVEQUEUE.size());
				Bar bar  = DataCache.SAVEQUEUE.take();
				Document document = MongoDBUtil.beanToDocument(bar);
				switch (bar.getType())
				{
					case Bar.TYPE_MIN:
						dataEngine.getMdDBClient().insert("MDCollector", "BarMin", document);
						break;
					case Bar.TYPE_5MIN:
						dataEngine.getMdDBClient().insert("MDCollector", "Bar5Min", document);
						break;
					case Bar.TYPE_15MIN:
						dataEngine.getMdDBClient().insert("MDCollector", "Bar15Min", document);
						break;
					case Bar.TYPE_DAY:
						dataEngine.getMdDBClient().insert("MDCollector", "BarDay", document);
						break;
					case Bar.TYPE_HOUR:
						dataEngine.getMdDBClient().insert("MDCollector", "BarMHour", document);
						break;
					case Bar.TYPE_WEEK:
						dataEngine.getMdDBClient().insert("MDCollector", "BarWEEK", document);
						break;
					default:
						break;
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
