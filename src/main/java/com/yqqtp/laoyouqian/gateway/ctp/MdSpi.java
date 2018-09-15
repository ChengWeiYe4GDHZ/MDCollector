package com.yqqtp.laoyouqian.gateway.ctp;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yqqtp.laoyouqian.entity.RtConstant;
import com.yqqtp.laoyouqian.jctp.CThostFtdcDepthMarketDataField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcForQuoteRspField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcMdApi;
import com.yqqtp.laoyouqian.jctp.CThostFtdcMdSpi;
import com.yqqtp.laoyouqian.jctp.CThostFtdcReqUserLoginField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcRspInfoField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcRspUserLoginField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcSpecificInstrumentField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcUserLogoutField;




public class MdSpi extends CThostFtdcMdSpi {

	Logger log = LoggerFactory.getLogger(MdSpi.class);

	private CtpGateway ctpGateway;
	private String mdAddress;
	// private String tdAddress;
	private String brokerID;
	private String userID;
	private String password;
	// private String autoCode;
	// private String userProductInfo;
	private String gatewayLogInfo;
	private String gatewayID;
	// private String gatewayDisplayName;

	private String tradingDayStr;

	private HashMap<String, String> contractExchangeMap;
	// private HashMap<String, Integer> contractSizeMap;

	MdSpi(CtpGateway ctpGateway) {

		this.ctpGateway = ctpGateway;
		this.mdAddress = ctpGateway.getGatewaySetting().getMdAddress();
		// this.tdAddress = ctpGateway.getGatewaySetting().getTdAddress();
		this.brokerID = ctpGateway.getGatewaySetting().getBrokerID();
		this.userID = ctpGateway.getGatewaySetting().getUserID();
		this.password = ctpGateway.getGatewaySetting().getPassword();
		// this.autoCode = ctpGateway.getGatewaySetting().getAuthCode();
		this.gatewayLogInfo = ctpGateway.getGatewayLogInfo();
		this.gatewayID = ctpGateway.getGatewayID();
		// this.gatewayDisplayName = ctpGateway.getGatewayDisplayName();

		this.contractExchangeMap = ctpGateway.getContractExchangeMap();
		// this.contractSizeMap = ctpGateway.getContractSizeMap();

	}

	private CThostFtdcMdApi cThostFtdcMdApi;

	private boolean connectProcessStatus = false; // 避免重复调用
	private boolean connectionStatus = false; // 前置机连接状态
	private boolean loginStatus = false; // 登陆状态

	/**
	 * 连接
	 */
	public synchronized void connect() {
		String logContent;
		if (isConnected() || connectProcessStatus) {
			return;
		}

		if (connectionStatus) {
			login();
			return;
		}
		if (cThostFtdcMdApi != null) {
			cThostFtdcMdApi.RegisterSpi(null);
			cThostFtdcMdApi.Release();
			connectionStatus = false;
			loginStatus = false;

		}
		String envTmpDir = System.getProperty("java.io.tmpdir");
		String tempFilePath = envTmpDir + File.separator + "com" + File.separator + "yqqtp" + File.separator + "api"
				+ File.separator + "jctp" + File.separator + "TEMP_CTP" + File.separator + "MD_"
				+ ctpGateway.getGatewayID() + "_";
		File tempFile = new File(tempFilePath);
		if (!tempFile.getParentFile().exists()) {
			try {
				FileUtils.forceMkdirParent(tempFile);
				logContent = gatewayLogInfo + "创建临时文件夹" + tempFile.getParentFile().getAbsolutePath();
				log.info(logContent);
			} catch (IOException e) {
				logContent = gatewayLogInfo + "创建临时文件夹失败" + tempFile.getParentFile().getAbsolutePath();
				log.error(logContent);
			}
		}
		logContent = gatewayLogInfo + "使用临时文件夹" + tempFile.getParentFile().getAbsolutePath();
		log.info(logContent);

		cThostFtdcMdApi = CThostFtdcMdApi.CreateFtdcMdApi(tempFile.getAbsolutePath());
		cThostFtdcMdApi.RegisterSpi(this);
		cThostFtdcMdApi.RegisterFront(mdAddress);
		cThostFtdcMdApi.Init();
		connectProcessStatus = true;

	}

	/**
	 * 关闭
	 */
	public synchronized void close() {
		if (!isConnected()) {
			return;
		}

		if (cThostFtdcMdApi != null) {
			cThostFtdcMdApi.RegisterSpi(null);
			cThostFtdcMdApi.Release();
			connectionStatus = false;
			loginStatus = false;
			connectProcessStatus = false;
		}

	}

	/**
	 * 返回接口状态
	 * 
	 * @return
	 */
	public boolean isConnected() {
		return connectionStatus && loginStatus;
	}

	/**
	 * 获取交易日
	 * 
	 * @return
	 */
	public String getTradingDay() {
		return tradingDayStr;
	}

	/**
	 * 订阅行情
	 * 
	 * @param rtSymbol
	 */
	public void subscribe(String symbol) {
		if (isConnected()) {
			String[] symbolArray = new String[1];
			symbolArray[0] = symbol;
			cThostFtdcMdApi.SubscribeMarketData(symbolArray, 1);
		} else {
			String logContent = gatewayLogInfo + "无法订阅行情,行情服务器尚未连接成功";
			log.warn(logContent);
		}
	}
	
	public void subscribeDupl(String[] symbol) {
		if (isConnected()) {
			cThostFtdcMdApi.SubscribeMarketData(symbol, symbol.length);
		} else {
			String logContent = gatewayLogInfo + "无法订阅行情,行情服务器尚未连接成功";
			log.warn(logContent);
		}
	}
	

	/**
	 * 退订行情
	 */
	public void unSubscribe(String rtSymbol) {
		if (isConnected()) {
			String[] rtSymbolArray = new String[1];
			rtSymbolArray[0] = rtSymbol;
			cThostFtdcMdApi.UnSubscribeMarketData(rtSymbolArray, 1);
		} else {
			String logContent = gatewayLogInfo + "退订无效,行情服务器尚未连接成功";
			log.warn(logContent);
		}
	}

	private void login() {
		if (StringUtils.isEmpty(brokerID) || StringUtils.isEmpty(userID) || StringUtils.isEmpty(password)) {
			String logContent = gatewayLogInfo + "BrokerID UserID Password不允许为空";
			log.error(logContent);
			return;
		}
		// 登录
		CThostFtdcReqUserLoginField userLoginField = new CThostFtdcReqUserLoginField();
		userLoginField.setBrokerID(brokerID);
		userLoginField.setUserID(userID);
		userLoginField.setPassword(password);
		cThostFtdcMdApi.ReqUserLogin(userLoginField, 0);
	}

	// 前置机联机回报
	public void OnFrontConnected() {
		String logContent = gatewayLogInfo + "行情接口前置机已连接";
		log.info(logContent);
		// 修改前置机连接状态为true
		connectionStatus = true;
		connectProcessStatus = false;
		login();
	}

	// 前置机断开回报
	public void OnFrontDisconnected(int nReason) {
		String logContent = gatewayLogInfo + "行情接口前置机已断开,Reason:" + nReason;
		log.info(logContent);
		this.connectionStatus = false;
	}

	// 登录回报
	public void OnRspUserLogin(CThostFtdcRspUserLoginField pRspUserLogin, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		if (pRspInfo.getErrorID() == 0) {
			log.info("{}OnRspUserLogin! TradingDay:{},SessionID:{},BrokerID:{},UserID:{}", gatewayLogInfo,
					pRspUserLogin.getTradingDay(), pRspUserLogin.getSessionID(), pRspUserLogin.getBrokerID(),
					pRspUserLogin.getUserID());
			// 修改登录状态为true
			this.loginStatus = true;
			tradingDayStr = pRspUserLogin.getTradingDay();
			log.info("{}获取到的交易日为{}", gatewayLogInfo, tradingDayStr);
			// 重新订阅之前的合约
			if (!ctpGateway.getSubscribedSymbols().isEmpty()) {
				String[] subscribedSymbolsArray = ctpGateway.getSubscribedSymbols()
						.toArray(new String[ctpGateway.getSubscribedSymbols().size()]);
				cThostFtdcMdApi.SubscribeMarketData(subscribedSymbolsArray, subscribedSymbolsArray.length + 1);
			}else {
				//默认订阅配置文件内的行情
				
			}
		} else {
			log.warn("{}行情接口登录回报错误! ErrorID:{},ErrorMsg:{}", gatewayLogInfo, pRspInfo.getErrorID(),
					pRspInfo.getErrorMsg());
		}

	}

	// 心跳警告
	public void OnHeartBeatWarning(int nTimeLapse) {
		String logContent = gatewayLogInfo + "行情接口心跳警告 nTimeLapse:" + nTimeLapse;
		log.info(logContent);
	}

	// 登出回报
	public void OnRspUserLogout(CThostFtdcUserLogoutField pUserLogout, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
		if (pRspInfo.getErrorID() != 0) {
			log.info("{}OnRspUserLogout!ErrorID:{},ErrorMsg:{}", gatewayLogInfo, pRspInfo.getErrorID(),
					pRspInfo.getErrorMsg());
		} else {
			log.info("{}OnRspUserLogout!BrokerID:{},UserID:{}", gatewayLogInfo, pUserLogout.getBrokerID(),
					pUserLogout.getUserID());

		}
		this.loginStatus = false;
	}

	// 错误回报
	public void OnRspError(CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		String logContent = MessageFormat.format("{0}行情接口错误回报!ErrorID:{1},ErrorMsg:{2},RequestID:{3},isLast{4}",
				gatewayLogInfo, pRspInfo.getErrorID(), pRspInfo.getErrorMsg(), nRequestID, bIsLast);
		log.info(logContent);
	}

	// 订阅合约回报
	public void OnRspSubMarketData(CThostFtdcSpecificInstrumentField pSpecificInstrument,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		if (pRspInfo.getErrorID() == 0) {
			String logContent = gatewayLogInfo + "OnRspSubMarketData! 订阅合约成功:" + pSpecificInstrument.getInstrumentID();
			log.info(logContent);
		} else {

			String logContent = gatewayLogInfo + "OnRspSubMarketData! 订阅合约失败,ErrorID：" + pRspInfo.getErrorID()
					+ "ErrorMsg:" + pRspInfo.getErrorMsg();
			log.warn(logContent);
		}
	}

	// 退订合约回报
	public void OnRspUnSubMarketData(CThostFtdcSpecificInstrumentField pSpecificInstrument,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		if (pRspInfo.getErrorID() == 0) {
			String logContent = gatewayLogInfo + "OnRspUnSubMarketData! 退订合约成功:"
					+ pSpecificInstrument.getInstrumentID();
			log.info(logContent);
		} else {
			String logContent = gatewayLogInfo + "OnRspUnSubMarketData! 退订合约失败,ErrorID：" + pRspInfo.getErrorID()
					+ "ErrorMsg:" + pRspInfo.getErrorMsg();
			log.warn(logContent);
		}
	}

	// 合约行情推送
	public void OnRtnDepthMarketData(CThostFtdcDepthMarketDataField pDepthMarketData) {
		if (pDepthMarketData != null) {

			String symbol = pDepthMarketData.getInstrumentID();

			// 上期所 郑商所正常,大商所错误
			// TODO 大商所时间修正
			Long updateTime = Long.valueOf(pDepthMarketData.getUpdateTime().replaceAll(":", ""));
			Long updateMillisec = (long) pDepthMarketData.getUpdateMillisec();
			Long actionDay = Long.valueOf(pDepthMarketData.getActionDay());

			String updateDateTimeWithMS = (actionDay * 100 * 100 * 100 * 1000 + updateTime * 1000 + updateMillisec)
					+ "";

			DateTime dateTime;
			try {
				dateTime = RtConstant.DT_FORMAT_WITH_MS_INT_FORMATTER.parseDateTime(updateDateTimeWithMS);
			} catch (Exception e) {
				log.error("{}解析日期发生异常", gatewayLogInfo, e);
				return;
			}

			String exchange = contractExchangeMap.get(symbol);
			String rtSymbol = symbol + "." + exchange;
			String tradingDay = tradingDayStr;
			String actionDayStr = pDepthMarketData.getActionDay();
			String actionTime = dateTime.toString(RtConstant.T_FORMAT_WITH_MS_INT_FORMATTER);
			Integer status = 0;
			Double lastPrice = pDepthMarketData.getLastPrice();
			Integer lastVolume = 0;
			Integer volume = pDepthMarketData.getVolume();
			Double openInterest = pDepthMarketData.getOpenInterest();
			Long preOpenInterest = 0L;
			Double preClosePrice = pDepthMarketData.getPreClosePrice();
			Double preSettlePrice = pDepthMarketData.getPreSettlementPrice();
			Double openPrice = pDepthMarketData.getOpenPrice();
			Double highPrice = pDepthMarketData.getHighestPrice();
			Double lowPrice = pDepthMarketData.getLowestPrice();
			Double upperLimit = pDepthMarketData.getUpperLimitPrice();
			Double lowerLimit = pDepthMarketData.getLowerLimitPrice();
			Double bidPrice1 = pDepthMarketData.getBidPrice1();
			Double bidPrice2 = pDepthMarketData.getBidPrice2();
			Double bidPrice3 = pDepthMarketData.getBidPrice3();
			Double bidPrice4 = pDepthMarketData.getBidPrice4();
			Double bidPrice5 = pDepthMarketData.getBidPrice5();
			Double bidPrice6 = 0d;
			Double bidPrice7 = 0d;
			Double bidPrice8 = 0d;
			Double bidPrice9 = 0d;
			Double bidPrice10 = 0d;
			Double askPrice1 = pDepthMarketData.getAskPrice1();
			Double askPrice2 = pDepthMarketData.getAskPrice2();
			Double askPrice3 = pDepthMarketData.getAskPrice3();
			Double askPrice4 = pDepthMarketData.getAskPrice4();
			Double askPrice5 = pDepthMarketData.getAskPrice5();
			Double askPrice6 = 0d;
			Double askPrice7 = 0d;
			Double askPrice8 = 0d;
			Double askPrice9 = 0d;
			Double askPrice10 = 0d;
			Integer bidVolume1 = pDepthMarketData.getBidVolume1();
			Integer bidVolume2 = pDepthMarketData.getBidVolume2();
			Integer bidVolume3 = pDepthMarketData.getBidVolume3();
			Integer bidVolume4 = pDepthMarketData.getBidVolume4();
			Integer bidVolume5 = pDepthMarketData.getBidVolume5();
			Integer bidVolume6 = 0;
			Integer bidVolume7 = 0;
			Integer bidVolume8 = 0;
			Integer bidVolume9 = 0;
			Integer bidVolume10 = 0;
			Integer askVolume1 = pDepthMarketData.getAskVolume1();
			Integer askVolume2 = pDepthMarketData.getAskVolume2();
			Integer askVolume3 = pDepthMarketData.getAskVolume3();
			Integer askVolume4 = pDepthMarketData.getAskVolume4();
			Integer askVolume5 = pDepthMarketData.getAskVolume5();
			Integer askVolume6 = 0;
			Integer askVolume7 = 0;
			Integer askVolume8 = 0;
			Integer askVolume9 = 0;
			Integer askVolume10 = 0;

			ctpGateway.emitTick(gatewayID, symbol, exchange, rtSymbol, tradingDay, actionDayStr, actionTime, dateTime,
					status, lastPrice, lastVolume, volume, openInterest, preOpenInterest, preClosePrice, preSettlePrice,
					openPrice, highPrice, lowPrice, upperLimit, lowerLimit, bidPrice1, bidPrice2, bidPrice3, bidPrice4,
					bidPrice5, bidPrice6, bidPrice7, bidPrice8, bidPrice9, bidPrice10, askPrice1, askPrice2, askPrice3,
					askPrice4, askPrice5, askPrice6, askPrice7, askPrice8, askPrice9, askPrice10, bidVolume1,
					bidVolume2, bidVolume3, bidVolume4, bidVolume5, bidVolume6, bidVolume7, bidVolume8, bidVolume9,
					bidVolume10, askVolume1, askVolume2, askVolume3, askVolume4, askVolume5, askVolume6, askVolume7,
					askVolume8, askVolume9, askVolume10);
			
			log.info("收到合约"+":"+symbol+" lastprice:"+lastPrice+"  timestamp:"+actionTime);

		} else {
			log.warn("{}OnRtnDepthMarketData! 收到行情信息为空", gatewayLogInfo);
		}
	}

	// 订阅期权询价
	public void OnRspSubForQuoteRsp(CThostFtdcSpecificInstrumentField pSpecificInstrument,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		log.info("{}OnRspSubForQuoteRsp!", gatewayLogInfo);
	}

	// 退订期权询价
	public void OnRspUnSubForQuoteRsp(CThostFtdcSpecificInstrumentField pSpecificInstrument,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		log.info("{}OnRspUnSubForQuoteRsp!", gatewayLogInfo);
	}

	// 期权询价推送
	public void OnRtnForQuoteRsp(CThostFtdcForQuoteRspField pForQuoteRsp) {
		log.info("{}OnRspUnSubForQuoteRsp!", gatewayLogInfo);
	}

}