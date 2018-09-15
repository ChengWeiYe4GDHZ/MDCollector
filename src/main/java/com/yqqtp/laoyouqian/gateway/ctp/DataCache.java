package com.yqqtp.laoyouqian.gateway.ctp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import com.yqqtp.laoyouqian.entity.Bar;
import com.yqqtp.laoyouqian.entity.LimitQueue;
import com.yqqtp.laoyouqian.entity.Tick;

public class DataCache {
	//订阅的行情
	public static String[] SYMBOLS;
	//最新Tick缓存
	public static Map<String,Tick> LASTTICK = new HashMap<String,Tick>();
	//Tick缓存数组 存放过去200个Tick数据
 	public static Map<String,LimitQueue<Tick>> TICKCACHE = new HashMap<String,LimitQueue<Tick>>();  
	//TickList K线缓存数组  完成上一个K线制作之后自动删除
	public static Map<String,Map<String,List<Tick>>> MINBAR = new  HashMap<String,Map<String,List<Tick>>>();
	public static Map<String,Map<String,List<Tick>>> MINTICK = new  HashMap<String,Map<String,List<Tick>>>();
	public static Map<String,Map<String,Bar>> MIN1BAR = new  HashMap<String,Map<String,Bar>>();
	public static Map<String,Map<String,Bar>> MIN5BAR = new  HashMap<String,Map<String,Bar>>();
	public static Map<String,Map<String,Bar>> MIN15BAR = new  HashMap<String,Map<String,Bar>>();
	public static Map<String,Map<String,Bar>> HOURBAR = new  HashMap<String,Map<String,Bar>>();
	public static Map<String,Map<String,Bar>> DAYBAR = new  HashMap<String,Map<String,Bar>>();
	public static Map<String,Map<String,Bar>> WEEKBAR = new  HashMap<String,Map<String,Bar>>();
	//一分钟Tick
	public static Map<String,LimitQueue<Tick>> minTick = new HashMap<String,LimitQueue<Tick>>();
	
	//一分钟K线
	public static Map<String,LimitQueue<Bar>> minKTick = new HashMap<String,LimitQueue<Bar>>();
	
	//5分钟
	public static Map<String,LimitQueue<Bar>> minK5Tick = new HashMap<String,LimitQueue<Bar>>();
	
	//15分钟
	public static Map<String,LimitQueue<Bar>> minK15Tick = new HashMap<String,LimitQueue<Bar>>();
	
	//60分钟
	public static Map<String,LimitQueue<Bar>> hourKTick = new HashMap<String,LimitQueue<Bar>>();
	
	//日K
	public static Map<String,LimitQueue<Bar>> dayKTick = new HashMap<String,LimitQueue<Bar>>();
	
	//周K
	public static Map<String,LimitQueue<Bar>> weekKTick = new HashMap<String,LimitQueue<Bar>>();
	
	//异步存储Tick数据队列
	public static LinkedBlockingQueue<Tick> TICKQUEUE = new LinkedBlockingQueue<Tick>();
	//异步处理行情
	public static LinkedBlockingQueue<Tick> BARQUEUE = new LinkedBlockingQueue<Tick>();
	//异步存储Bar数据队列
	public static LinkedBlockingQueue<Bar> SAVEQUEUE = new LinkedBlockingQueue<Bar>();
	//异步更新Bar数据队列
	public static LinkedBlockingQueue<Bar> UPDATEQUEUE = new LinkedBlockingQueue<Bar>();
	
}
