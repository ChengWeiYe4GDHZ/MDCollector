package com.yqqtp.laoyouqian.task;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yqqtp.laoyouqian.engine.DataEngine;
import com.yqqtp.laoyouqian.entity.Tick;
import com.yqqtp.laoyouqian.gateway.ctp.DataCache;
import com.yqqtp.laoyouqian.utils.MongoDBUtil;

public class SaveTickTask implements Runnable{

	public Logger log = LoggerFactory.getLogger(SaveTickTask.class);
	
	@Autowired
	private DataEngine dataEngine;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				log.info("Tick——Save待处理行情数量:"+DataCache.TICKQUEUE.size());
				Tick tick  = DataCache.TICKQUEUE.take();
				DataCache.LASTTICK.put(tick.getSymbol(), tick);
				Document document = MongoDBUtil.beanToDocument(tick);
				dataEngine.getMdDBClient().insert("MDCollector", "TICK", document);
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
