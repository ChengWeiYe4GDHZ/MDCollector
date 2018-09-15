package com.yqqtp.laoyouqian.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.yqqtp.laoyouqian.entity.Bar;
import com.yqqtp.laoyouqian.entity.LimitQueue;
import com.yqqtp.laoyouqian.gateway.ctp.DataCache;
import com.yqqtp.laoyouqian.utils.MongoDBUtil;

public class InitData
{
	public static final String dbName="MDCollector";
	public static final String minCollection = "BarMin";
	public static final String min5Collection = "Bar5Min";
	public static final String min15Collection = "Bar15Min";
	public static final String hourCollection = "BarMHour";
	public static final String dayCollection = "BarDay";
	public static final String weekCollection = "BarWEEK";
	@Autowired
	private DataEngine dataEngine;
	
	public void initData() {
		String[] symbols = DataCache.SYMBOLS;
		//将未关闭的Bar加载到缓存中
		for(String symbol:symbols) {
			Document unCloseFilter = new Document();
			unCloseFilter.put("symbol", symbol);
			unCloseFilter.put("isClose", Bar.CLOSE_FALSE);
			List<Document> unCloseMinBar = dataEngine.getMdDBClient().find(dbName, minCollection, unCloseFilter);
			List<Document> unClose5MinBar = dataEngine.getMdDBClient().find(dbName, min5Collection, unCloseFilter);
			List<Document> unClose15MinBar = dataEngine.getMdDBClient().find(dbName, min15Collection, unCloseFilter);
			List<Document> unCloseHourBar = dataEngine.getMdDBClient().find(dbName, hourCollection, unCloseFilter);
			List<Document> unCloseDayBar = dataEngine.getMdDBClient().find(dbName, dayCollection, unCloseFilter);
			List<Document> unCloseWeekBar = dataEngine.getMdDBClient().find(dbName, weekCollection, unCloseFilter);
			
			loadUnLockBar(unCloseMinBar,DataCache.MIN1BAR,symbol);
			loadUnLockBar(unClose5MinBar,DataCache.MIN5BAR,symbol);
			loadUnLockBar(unClose15MinBar,DataCache.MIN15BAR,symbol);
			loadUnLockBar(unCloseHourBar,DataCache.HOURBAR,symbol);
			loadUnLockBar(unCloseDayBar,DataCache.DAYBAR,symbol);
			loadUnLockBar(unCloseWeekBar,DataCache.WEEKBAR,symbol);
		}
		
		//将历史数据加载到缓存当中
		BasicDBObject sort = new BasicDBObject();
		//mongodb中按字段倒序查询（-1是倒序，1是正序）
		sort.put("endDatetime", -1);
		for(String symbol:symbols) {
			Document filter = new Document();
			filter.put("symbol", symbol);
			filter.put("isClose", Bar.CLOSE_TRUE);
			List<Document> minList = dataEngine.getMdDBClient().findByPage(dbName, minCollection, filter, sort, 1, 200);
			List<Document> min5List = dataEngine.getMdDBClient().findByPage(dbName, min5Collection, filter, sort, 1, 200);
			List<Document> min15List = dataEngine.getMdDBClient().findByPage(dbName, min15Collection, filter, sort, 1, 200);
			List<Document> hourList = dataEngine.getMdDBClient().findByPage(dbName, hourCollection, filter, sort, 1, 200);
			List<Document> dayList = dataEngine.getMdDBClient().findByPage(dbName, dayCollection, filter, sort, 1, 200);
			List<Document> weekList = dataEngine.getMdDBClient().findByPage(dbName, weekCollection, filter, sort, 1, 200);
			loadBar(minList,DataCache.minKTick,symbol);
			loadBar(min5List, DataCache.minK5Tick,symbol);
			loadBar(min15List, DataCache.minK15Tick,symbol);
			loadBar(hourList, DataCache.hourKTick,symbol);
			loadBar(dayList, DataCache.dayKTick,symbol);
			loadBar(weekList, DataCache.weekKTick,symbol);
		}
		
	}
	
	public void loadBar(List<Document> list,Map<String, LimitQueue<Bar>> map,String symbol) {
		if(map.get(symbol)==null) {
			map.put(symbol, new LimitQueue<Bar>(200));
		}
		for(int i=list.size()-1;i>=0;i--) {
			Document doc = list.get(i);
			 Bar bar = new Bar();
			 try
			{
				bar = MongoDBUtil.documentToBean(doc, bar);
				map.get(symbol).offer(bar);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void loadUnLockBar(List<Document> list,Map<String, Map<String, Bar>> map,String symbol) {
		if(map.get(symbol)==null) {
			map.put(symbol, new HashMap<String,Bar>());
		}
		for(Document doc:list) {
			 Bar bar = new Bar();
			 try
			{
				bar = MongoDBUtil.documentToBean(doc, bar);
				map.get(symbol).put(bar.getId(), bar);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
