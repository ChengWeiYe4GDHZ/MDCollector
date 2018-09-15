package com.yqqtp.laoyouqian.entity;

import java.io.Serializable;

import org.joda.time.DateTime;

public class Bar implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private DateTime starDatetime;
	private DateTime endDatetime;
	private Integer type;
	private Double openPrice;
	private Double closePrice;
	private Double highPrice;
	private Double lowPrice;
	private Integer volume;
	private String symbol;
	private Double openInterest;
	private Integer isClose=CLOSE_FALSE;
	
	public static final int TYPE_MIN = 1;
	public static final int TYPE_5MIN = 2;
	public static final int TYPE_15MIN = 3;
	public static final int TYPE_HOUR = 4;
	public static final int TYPE_DAY = 5;
	public static final int TYPE_WEEK = 6;
	
	public static final int CLOSE_TRUE = 1;
	public static final int CLOSE_FALSE = 0;
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public DateTime getStarDatetime() {
		return starDatetime;
	}
	public void setStarDatetime(DateTime starDatetime) {
		this.starDatetime = starDatetime;
	}
	public DateTime getEndDatetime() {
		return endDatetime;
	}
	public void setEndDatetime(DateTime endDatetime) {
		this.endDatetime = endDatetime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}
	public Double getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}
	public Double getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(Double highPrice) {
		this.highPrice = highPrice;
	}
	public Double getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Double getOpenInterest() {
		return openInterest;
	}
	public void setOpenInterest(Double openInterest) {
		this.openInterest = openInterest;
	}
	public Integer getIsClose() {
		return isClose;
	}
	public void setIsClose(Integer isClose) {
		this.isClose = isClose;
	}
	
	
	
	
	
	
}
