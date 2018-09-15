package com.yqqtp.laoyouqian.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yqqtp.laoyouqian.entity.Bar;
import com.yqqtp.laoyouqian.entity.LimitQueue;
import com.yqqtp.laoyouqian.entity.Tick;
import com.yqqtp.laoyouqian.gateway.ctp.DataCache;

public class CreateBarTask implements Runnable{
	
	public Logger log = LoggerFactory.getLogger(CreateBarTask.class);

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				log.info("CreateBar待处理数量:"+DataCache.BARQUEUE.size());
				Tick tick = DataCache.BARQUEUE.take();
				//K线生成判断
				if(DataCache.MINBAR.get(tick.getSymbol())==null) {
					DataCache.MINBAR.put(tick.getSymbol(),new HashMap<String,List<Tick>>());
				}
				if(DataCache.MIN1BAR.get(tick.getSymbol())==null) {
					DataCache.MIN1BAR.put(tick.getSymbol(),new HashMap<String,Bar>());
				}
				if(DataCache.MIN5BAR.get(tick.getSymbol())==null) {
					DataCache.MIN5BAR.put(tick.getSymbol(),new HashMap<String,Bar>());
				}
				if(DataCache.MIN15BAR.get(tick.getSymbol())==null) {
					DataCache.MIN15BAR.put(tick.getSymbol(),new HashMap<String,Bar>());
				}
				if(DataCache.HOURBAR.get(tick.getSymbol())==null) {
					DataCache.HOURBAR.put(tick.getSymbol(),new HashMap<String,Bar>());
				}
				if(DataCache.DAYBAR.get(tick.getSymbol())==null) {
					DataCache.DAYBAR.put(tick.getSymbol(),new HashMap<String,Bar>());
				}
				if(DataCache.WEEKBAR.get(tick.getSymbol())==null) {
					DataCache.WEEKBAR.put(tick.getSymbol(),new HashMap<String,Bar>());
				}
				//1分钟
				long KBarId = tick.getDateTime().getMillis()/(60*1000);
				//5分钟
				long K5MBarId = tick.getDateTime().getMillis()/(5*60*1000);
				//15分钟
				long K15BarId = tick.getDateTime().getMillis()/(15*60*1000);
				//60分钟
				long KHBarId = tick.getDateTime().getMillis()/(60*60*1000);
				//24小时
				long KDBarId = tick.getDateTime().getMillis()/(24*60*60*1000);
				//1周
				long KDWBarId = tick.getDateTime().getMillis()/(7*24*60*60*1000);
				
				
				
				//不存在KBarId,说明有一个新的1分钟K线产生
				if(DataCache.MIN1BAR.get(tick.getSymbol()).get(KBarId+"")==null) {
					//创建1分钟K线
					createNewMinBar(KBarId+"",tick);
					List<String> keyShouldRemove = new ArrayList<>();
					for(String key:DataCache.MINBAR.get(tick.getSymbol()).keySet()) {
						if(!key.equals(KBarId+"")) {
							keyShouldRemove.add(key);
						}
					}
					for(String key:keyShouldRemove) {
						DataCache.MINBAR.get(tick.getSymbol()).remove(key);
					}
					
					//是否添加5分钟K线
					if(DataCache.MIN5BAR.get(tick.getSymbol()).get(K5MBarId+"")==null) {
						log.info("生成新5分钟线");
						closeBar(tick,Bar.TYPE_5MIN);
						createKBar(K5MBarId+"",tick,Bar.TYPE_5MIN);
					} 
					//是否添加15分钟K线
					if(DataCache.MIN15BAR.get(tick.getSymbol()).get(K15BarId+"")==null) {
						log.info("生成新15分钟线");
						closeBar(tick,Bar.TYPE_15MIN);
						createKBar(K15BarId+"",tick,Bar.TYPE_15MIN);
					} 
					//是否添加60分钟K线
					if(DataCache.HOURBAR.get(tick.getSymbol()).get(KHBarId+"")==null) {
						log.info("生成新60分钟线");
						closeBar(tick,Bar.TYPE_HOUR);
						createKBar(KHBarId+"",tick,Bar.TYPE_HOUR);
					} 
					//是否添加日K线
					if(DataCache.DAYBAR.get(tick.getSymbol()).get(KDBarId+"")==null) {
						log.info("生成新日K线");
						closeBar(tick,Bar.TYPE_DAY);
						createKBar(KDBarId+"",tick,Bar.TYPE_DAY);
					} 
					//是否添加周K线
					if(DataCache.WEEKBAR.get(tick.getSymbol()).get(KDWBarId+"")==null) {
						log.info("生成新周K线");
						closeBar(tick,Bar.TYPE_WEEK);
						createKBar(KDWBarId+"",tick,Bar.TYPE_WEEK);
					} 
					
				} 
				
				//更新K线
				updateMinBar(tick);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void createNewMinBar(String id,Tick tick) {
		log.info("生成新1分钟线");
		List<Tick> cacheList = new ArrayList<Tick>();
		cacheList.add(tick);
		DataCache.MINBAR.get(tick.getSymbol()).put(id+"", cacheList);
		DateTime startDate = cacheList.get(0).getDateTime();
		DateTime endDate = cacheList.get(cacheList.size()-1).getDateTime();
		Bar minBar = new Bar();
		minBar.setClosePrice(tick.getLastPrice());
		minBar.setEndDatetime(endDate);
		minBar.setHighPrice(tick.getLastPrice());
		minBar.setId(id);
		minBar.setLowPrice(tick.getLastPrice());
		minBar.setOpenInterest(tick.getOpenInterest());
		minBar.setOpenPrice(tick.getLastPrice());
		minBar.setStarDatetime(startDate);
		minBar.setSymbol(tick.getSymbol());
		minBar.setType(Bar.TYPE_MIN);
		minBar.setVolume(tick.getLastVolume());
		DataCache.MIN1BAR.get(tick.getSymbol()).put(id,minBar);
		try
		{
			DataCache.SAVEQUEUE.put(minBar);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeBar(tick,Bar.TYPE_MIN);
	}
	
	public void updateMinBar(Tick tick) {
		//1分钟
		long KBarId = tick.getDateTime().getMillis()/(60*1000);
		//5分钟
		long K5MBarId = tick.getDateTime().getMillis()/(5*60*1000);
		//15分钟
		long K15BarId = tick.getDateTime().getMillis()/(15*60*1000);
		//60分钟
		long KHBarId = tick.getDateTime().getMillis()/(60*60*1000);
		//24小时
		long KDBarId = tick.getDateTime().getMillis()/(24*60*60*1000);
		//1周
		long KDWBarId = tick.getDateTime().getMillis()/(7*24*60*60*1000);
		
		updateKBar(KBarId+"",tick,Bar.TYPE_MIN);
		updateKBar(K5MBarId+"",tick,Bar.TYPE_5MIN);
		updateKBar(K15BarId+"",tick,Bar.TYPE_15MIN);
		updateKBar(KHBarId+"",tick,Bar.TYPE_HOUR);
		updateKBar(KDBarId+"",tick,Bar.TYPE_DAY);
		updateKBar(KDWBarId+"",tick,Bar.TYPE_WEEK);
	}
	
	public void createKBar(String id,Tick tick,int type) {
		Bar bar = new Bar();
		bar.setClosePrice(tick.getLastPrice());
		bar.setEndDatetime(tick.getDateTime());
		bar.setHighPrice(tick.getLastPrice());
		bar.setOpenPrice(tick.getLastPrice());
		bar.setId(id);
		bar.setLowPrice(tick.getLastPrice());
		bar.setOpenInterest(tick.getOpenInterest());
		bar.setStarDatetime(tick.getDateTime());
		bar.setSymbol(tick.getSymbol());
		bar.setType(type);
		bar.setVolume(tick.getLastVolume());
		switch(type) {
			case Bar.TYPE_MIN:
				DataCache.MIN1BAR.get(tick.getSymbol()).put(id,bar);
				break;
			case Bar.TYPE_5MIN:
				DataCache.MIN5BAR.get(tick.getSymbol()).put(id,bar);
				break;
			case Bar.TYPE_15MIN:
				DataCache.MIN15BAR.get(tick.getSymbol()).put(id,bar);
				break;
			case Bar.TYPE_HOUR:
				DataCache.HOURBAR.get(tick.getSymbol()).put(id,bar);
				break;
			case Bar.TYPE_DAY:
				DataCache.DAYBAR.get(tick.getSymbol()).put(id,bar);
				break;
			case Bar.TYPE_WEEK:
				DataCache.WEEKBAR.get(tick.getSymbol()).put(id,bar);
				break;
			default:
		}
		try
		{
			DataCache.SAVEQUEUE.put(bar);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateKBar(String id,Tick tick,int type) {
		Bar bar = null;
		switch(type) {
			case Bar.TYPE_MIN:
				bar = DataCache.MIN1BAR.get(tick.getSymbol()).get(id);
				break;
			case Bar.TYPE_5MIN:
				bar = DataCache.MIN5BAR.get(tick.getSymbol()).get(id);
				break;
			case Bar.TYPE_15MIN:
				bar = DataCache.MIN15BAR.get(tick.getSymbol()).get(id);
				break;
			case Bar.TYPE_HOUR:
				bar = DataCache.HOURBAR.get(tick.getSymbol()).get(id);
				break;
			case Bar.TYPE_DAY:
				bar = DataCache.DAYBAR.get(tick.getSymbol()).get(id);
				break;
			case Bar.TYPE_WEEK:
				bar = DataCache.WEEKBAR.get(tick.getSymbol()).get(id);
				break;
			default:
		}
		if(bar==null) {
			return;
		}
		if(bar.getLowPrice()>tick.getLastPrice()) {
			bar.setLowPrice(tick.getLastPrice());
		}
		if(bar.getHighPrice()<tick.getLastPrice()) {
			bar.setHighPrice(tick.getLastPrice());
		}
		long oldtime = bar.getEndDatetime().getMillis();
		if(bar.getEndDatetime().getMillis()<tick.getDateTime().getMillis()) {
			if(tick.getDateTime().getMillis()-bar.getEndDatetime().getMillis()>=10000) {
				bar.setEndDatetime(tick.getDateTime());
			}
		}
		bar.setClosePrice(tick.getLastPrice());
		bar.setVolume(bar.getVolume()+tick.getLastVolume());
		switch(type) {
			case Bar.TYPE_MIN:
				DataCache.MIN1BAR.get(tick.getSymbol()).put(id,bar);
				break;
			case Bar.TYPE_5MIN:
				DataCache.MIN5BAR.get(tick.getSymbol()).put(id,bar);
				break;
			case Bar.TYPE_15MIN:
				DataCache.MIN15BAR.get(tick.getSymbol()).put(id,bar);
				break;
			case Bar.TYPE_HOUR:
				DataCache.HOURBAR.get(tick.getSymbol()).put(id,bar);
				break;
			case Bar.TYPE_DAY:
				DataCache.DAYBAR.get(tick.getSymbol()).put(id,bar);
				break;
			case Bar.TYPE_WEEK:
				DataCache.WEEKBAR.get(tick.getSymbol()).put(id,bar);
				break;
			default:
		}
		try
		{
			//10秒数据间隔更新一次
			if(tick.getDateTime().getMillis()-oldtime>=10000) {
				DataCache.UPDATEQUEUE.put(bar);
			}
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void  closeBar(Tick tick,int type) {
		Map<String,Bar> bar = null;
		String id = "";
		long keyId ;
		Map<String,LimitQueue<Bar>> queue = null;
		switch(type) {
			case Bar.TYPE_MIN:
				bar = DataCache.MIN1BAR.get(tick.getSymbol());
				keyId = tick.getDateTime().getMillis()/(60*1000);
				id = keyId +"";
				queue = DataCache.minKTick;
				break;
			case Bar.TYPE_5MIN:
				bar = DataCache.MIN5BAR.get(tick.getSymbol());
				keyId = tick.getDateTime().getMillis()/(5*60*1000);
				id= keyId+"";
				queue = DataCache.minK5Tick;
				break;
			case Bar.TYPE_15MIN:
				bar = DataCache.MIN15BAR.get(tick.getSymbol());
				keyId = tick.getDateTime().getMillis()/(15*60*1000);
				id = keyId+"";
				queue = DataCache.minK15Tick;
				break;
			case Bar.TYPE_HOUR:
				bar = DataCache.HOURBAR.get(tick.getSymbol());
				keyId = tick.getDateTime().getMillis()/(60*60*1000);
				id= keyId+"";
				queue = DataCache.hourKTick;
				break;
			case Bar.TYPE_DAY:
				bar = DataCache.DAYBAR.get(tick.getSymbol());
				keyId = tick.getDateTime().getMillis()/(24*60*60*1000);
				id = keyId +"";
				queue = DataCache.dayKTick;
				break;
			case Bar.TYPE_WEEK:
				bar = DataCache.WEEKBAR.get(tick.getSymbol());
				keyId = tick.getDateTime().getMillis()/(7*24*60*60*1000);
				id = keyId +"";
				queue = DataCache.weekKTick;
				break;
			default:
		}
		if(bar==null) {
			return;
		}
		List<String> keyShouldRemove = new ArrayList<String>();
		for(String key:bar.keySet()) {
			if(!id.equals(key)&&bar.get(key)!=null) {
				Bar oldBar = bar.get(key);
				if(oldBar.getLowPrice()>tick.getLastPrice()) {
					oldBar.setLowPrice(tick.getLastPrice());
				}
				if(oldBar.getHighPrice()<tick.getLastPrice()) {
					oldBar.setHighPrice(tick.getLastPrice());
				}
				oldBar.setEndDatetime(tick.getDateTime());
				oldBar.setClosePrice(tick.getLastPrice());
				oldBar.setVolume(oldBar.getVolume()+tick.getVolume());
				
				if(queue.get(tick.getSymbol())==null) {
					queue.put(tick.getSymbol(), new LimitQueue<>(200));
				}
				oldBar.setIsClose(Bar.CLOSE_TRUE);
				queue.get(tick.getSymbol()).offer(oldBar);
				try
				{
					DataCache.UPDATEQUEUE.put(oldBar);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		for(String key:keyShouldRemove) {
			bar.remove(key);
		}
	}

}
