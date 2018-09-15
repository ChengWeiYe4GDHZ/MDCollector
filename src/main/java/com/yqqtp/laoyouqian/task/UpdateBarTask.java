package com.yqqtp.laoyouqian.task;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yqqtp.laoyouqian.engine.DataEngine;
import com.yqqtp.laoyouqian.entity.Bar;
import com.yqqtp.laoyouqian.gateway.ctp.DataCache;
import com.yqqtp.laoyouqian.utils.MongoDBUtil;

public class UpdateBarTask implements Runnable
{
	public Logger log = LoggerFactory.getLogger(UpdateBarTask.class);
	@Autowired
	private DataEngine dataEngine ;

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		while(true) {
			try {
				log.info("Bar--Update待处理行情数量:"+DataCache.UPDATEQUEUE.size());
				Bar bar  = DataCache.UPDATEQUEUE.take();
				Document document = MongoDBUtil.beanToDocument(bar);
				Document filter = new Document(); 
				filter.append("id", bar.getId());
				filter.append("symbol", bar.getSymbol());
				filter.append("isClose", 0);
				switch (bar.getType())
				{
					case Bar.TYPE_MIN:
						dataEngine.getMdDBClient().updateOne("MDCollector", "BarMin", filter, document);
						break;
					case Bar.TYPE_5MIN:
						dataEngine.getMdDBClient().updateOne("MDCollector", "Bar5Min", filter, document);
						break;
					case Bar.TYPE_15MIN:
						dataEngine.getMdDBClient().updateOne("MDCollector", "Bar15Min", filter, document);
						break;
					case Bar.TYPE_DAY:
						dataEngine.getMdDBClient().updateOne("MDCollector", "BarDay", filter, document);
						break;
					case Bar.TYPE_HOUR:
						dataEngine.getMdDBClient().updateOne("MDCollector", "BarMHour", filter, document);
						break;
					case Bar.TYPE_WEEK:
						dataEngine.getMdDBClient().updateOne("MDCollector", "BarWEEK", filter, document);
						break;
					default:
						break;
				}
				
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
}
