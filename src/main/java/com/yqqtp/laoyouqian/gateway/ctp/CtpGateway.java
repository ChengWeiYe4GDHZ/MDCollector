package com.yqqtp.laoyouqian.gateway.ctp;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yqqtp.laoyouqian.entity.Account;
import com.yqqtp.laoyouqian.entity.Contract;
import com.yqqtp.laoyouqian.entity.Position;
import com.yqqtp.laoyouqian.entity.Tick;
import com.yqqtp.laoyouqian.utils.CommonUtil;




public class CtpGateway {
	
	private static Logger log = LoggerFactory.getLogger(CtpGateway.class);

	static {
		try {
			if (System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {

				String envTmpDir = System.getProperty("java.io.tmpdir");
				String tempLibPath = envTmpDir + File.separator + "com" + File.separator + "yqqqtp" + File.separator + "api"
						+ File.separator + "jctp" + File.separator + "lib";
				CommonUtil.copyURLToFileForTmp(tempLibPath, CtpGateway.class.getResource("/assembly/libiconv.dll"));
				CommonUtil.copyURLToFileForTmp(tempLibPath, CtpGateway.class.getResource("/assembly/thostmduserapi.dll"));
				CommonUtil.copyURLToFileForTmp(tempLibPath, CtpGateway.class.getResource("/assembly/thosttraderapi.dll"));
				CommonUtil.copyURLToFileForTmp(tempLibPath, CtpGateway.class.getResource("/assembly/ctpapiv6v3v11.dll"));
				
				System.load(tempLibPath + File.separator + "libiconv.dll");
				System.load(tempLibPath + File.separator + "thostmduserapi.dll");
				System.load(tempLibPath + File.separator + "thosttraderapi.dll");
				System.load(tempLibPath + File.separator + "ctpapiv6v3v11.dll");
			} else {

				String envTmpDir = "/tmp";
				String tempLibPath = envTmpDir + File.separator + "com" + File.separator + "yqqtp" + File.separator + "api"
						+ File.separator + "jctp" + File.separator + "lib";
				
				CommonUtil.copyURLToFileForTmp(tempLibPath, CtpGateway.class.getResource("/assembly/libthostmduserapi.so"));
				CommonUtil.copyURLToFileForTmp(tempLibPath, CtpGateway.class.getResource("/assembly/libthosttraderapi.so"));
				CommonUtil.copyURLToFileForTmp(tempLibPath, CtpGateway.class.getResource("/assembly/libjctpmdapiv6v3v11x64.so"));
				CommonUtil.copyURLToFileForTmp(tempLibPath, CtpGateway.class.getResource("/assembly/libjctptraderapiv6v3v11x64.so"));

				System.load(tempLibPath + File.separator + "libthostmduserapi.so");
				System.load(tempLibPath + File.separator + "ctpapiv6v3v11.so");
				System.load(tempLibPath + File.separator + "libthosttraderapi.so");
			}
		} catch (Exception e) {
			log.error("复制库失败!", e);
		}

	}
	
	public static CtpGateway getInstance() {
		GatewaySetting gatewaySetting = new GatewaySetting();
		CompositeConfiguration config = new CompositeConfiguration();
		try {
			FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(
					PropertiesConfiguration.class)
							.configure(new Parameters().properties().setFileName("CTPConfig.properties")
									.setThrowExceptionOnMissing(true)
									.setListDelimiterHandler(new DefaultListDelimiterHandler(';'))
									.setIncludesAllowed(false));
			PropertiesConfiguration propertiesConfiguration = builder.getConfiguration();
			config.addConfiguration(propertiesConfiguration);
		} catch (ConfigurationException e) {
			log.error("MongoDBClientSingleton配置读取失败", e);
		}
		gatewaySetting.setAuthCode(config.getString("AuthCode"));
		gatewaySetting.setBrokerID(config.getString("BrokerID"));
		gatewaySetting.setGatewayDisplayName(config.getString("GatewayDisplayName"));
		gatewaySetting.setGatewayClassName(config.getString("GatewayClassName"));
		gatewaySetting.setGatewayID(config.getString("GatewayID"));
		gatewaySetting.setMdAddress(config.getString("MdAddress"));
		gatewaySetting.setPassword(config.getString("Password"));
		gatewaySetting.setRuntimeStatus(false);
		gatewaySetting.setTdAddress(config.getString("TdAddress"));
		gatewaySetting.setUserID(config.getString("UserID"));
		gatewaySetting.setUserProductInfo(config.getString("UserProductInfo"));
		return new CtpGateway(gatewaySetting);
	}

	private HashMap<String, String> contractExchangeMap = new HashMap<>();
	private HashMap<String, Integer> contractSizeMap = new HashMap<>();

	private MdSpi mdSpi;
	private TdSpi tdSpi;

	protected String gatewayID;
	protected String gatewayDisplayName;
	protected String gatewayLogInfo;
	protected GatewaySetting gatewaySetting;
	protected HashSet<String> subscribedSymbols = new HashSet<>();
	Timer timer = new Timer();
	
	public GatewaySetting getGatewaySetting() {
		return gatewaySetting;
	}
	public String getGatewayLogInfo() {
		return this.gatewayLogInfo;
	}
	public String getGatewayID() {
		return gatewayID;
	}
	public HashSet<String> getSubscribedSymbols() {
		return subscribedSymbols;
	}
	
	public CtpGateway(GatewaySetting gatewaySetting) {
		this.gatewaySetting = gatewaySetting;
		this.gatewayID = gatewaySetting.getGatewayID();
		this.gatewayDisplayName = gatewaySetting.getGatewayDisplayName();
		this.gatewayLogInfo = "接口ID-[" + gatewayID + "] 名称-[" + gatewayDisplayName+"] >>> ";
		String logContent = gatewayLogInfo+"开始初始化";
		log.info(logContent);
		mdSpi = new MdSpi(this);
		tdSpi = new TdSpi(this);
		timer.schedule(new QueryTimerTask(), new Date(), 1000);
	}

	public HashMap<String, String> getContractExchangeMap() {
		return contractExchangeMap;
	}

	public HashMap<String, Integer> getContractSizeMap() {
		return contractSizeMap;
	}

	public void subscribe(String rtSymbol) {
		subscribedSymbols.add(rtSymbol);
		if (mdSpi != null) {
			mdSpi.subscribe(rtSymbol);
		}
	}
	
	public void subscribeDupl(String[] rtSymbol) {
		for(String s:rtSymbol) {
			subscribedSymbols.add(s);
		}
		if (mdSpi != null) {
			mdSpi.subscribeDupl(rtSymbol);
		}
	}

	public void unSubscribe(String rtSymbol) {
		subscribedSymbols.remove(rtSymbol);
		if (mdSpi != null) {
			mdSpi.unSubscribe(rtSymbol);
		}
	}

	public void connect() {
		if (tdSpi != null) {
			tdSpi.connect();
		}
		if (mdSpi != null) {
			mdSpi.connect();
		}
	}

	public void close() {
		if (tdSpi != null) {
			tdSpi.close();
		}
		if (mdSpi != null) {
			mdSpi.close();
		}

	}


	public void queryAccount() {
		if (tdSpi != null) {
			tdSpi.queryAccount();
		}
	}

	public void queryPosition() {
		if (tdSpi != null) {
			tdSpi.queryPosition();
		}
	}

	public boolean isConnected() {
		return tdSpi != null && mdSpi != null && tdSpi.isConnected() && mdSpi.isConnected();
	}
	
	class QueryTimerTask extends TimerTask{

	    @Override
	    public void run() {
	    	try {
		    	if(isConnected()) {
			        queryAccount();
		    	}
			    Thread.sleep(1250);
			    if(isConnected()) {
				    queryPosition();
			    }
			    Thread.sleep(1250);
	    	}catch (Exception e) {
	    		String logContent = gatewayLogInfo+"定时查询发生异常";
				log.error(logContent,e);
			}
	    }
	}
	
	
	public void emitTick(String gatewayID, String symbol, String exchange, String rtSymbol, String tradingDay, String actionDay,
			String actionTime, DateTime dateTime, Integer status, Double lastPrice, Integer lastVolume, Integer volume,
			Double openInterest, Long preOpenInterest, Double preClosePrice, Double preSettlePrice, Double openPrice,
			Double highPrice, Double lowPrice, Double upperLimit, Double lowerLimit, Double bidPrice1, Double bidPrice2,
			Double bidPrice3, Double bidPrice4, Double bidPrice5, Double bidPrice6, Double bidPrice7, Double bidPrice8,
			Double bidPrice9, Double bidPrice10, Double askPrice1, Double askPrice2, Double askPrice3, Double askPrice4,
			Double askPrice5, Double askPrice6, Double askPrice7, Double askPrice8, Double askPrice9, Double askPrice10,
			Integer bidVolume1, Integer bidVolume2, Integer bidVolume3, Integer bidVolume4, Integer bidVolume5,
			Integer bidVolume6, Integer bidVolume7, Integer bidVolume8, Integer bidVolume9, Integer bidVolume10,
			Integer askVolume1, Integer askVolume2, Integer askVolume3, Integer askVolume4, Integer askVolume5,
			Integer askVolume6, Integer askVolume7, Integer askVolume8, Integer askVolume9, Integer askVolume10) {
		

		try {
			Tick tick = new Tick();
			tick.setAllValue(gatewayID, symbol, exchange, rtSymbol, tradingDay, actionDay, actionTime, dateTime, status, lastPrice, lastVolume, volume, openInterest, preOpenInterest, preClosePrice, preSettlePrice, openPrice, highPrice, lowPrice, upperLimit, lowerLimit, bidPrice1, bidPrice2, bidPrice3, bidPrice4, bidPrice5, bidPrice6, bidPrice7, bidPrice8, bidPrice9, bidPrice10, askPrice1, askPrice2, askPrice3, askPrice4, askPrice5, askPrice6, askPrice7, askPrice8, askPrice9, askPrice10, bidVolume1, bidVolume2, bidVolume3, bidVolume4, bidVolume5, bidVolume6, bidVolume7, bidVolume8, bidVolume9, bidVolume10, askVolume1, askVolume2, askVolume3, askVolume4, askVolume5, askVolume6, askVolume7, askVolume8, askVolume9, askVolume10);
			//存入MongoDB
			try {
				DataCache.TICKQUEUE.put(tick);
				DataCache.BARQUEUE.put(tick);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	public void emitTrade(String gatewayID, String symbol, String exchange, String rtSymbol, String tradeID, String rtTradeID,
			String orderID, String rtOrderID, String direction, String offset, double price, int volume,
			String tradingDay, String tradeDate, String tradeTime, DateTime dateTime) {
		// 发送特定合约成交事件
		
	}

	public void emitOrder(String gatewayID, String symbol, String exchange, String rtSymbol, String orderID, String rtOrderID,
			String direction, String offset, double price, int totalVolume, int tradedVolume, String status,
			String tradingDay, String orderDate, String orderTime, String cancelTime, String activeTime,
			String updateTime, int frontID, int sessionID) {
		
	}
	
	public void emitPositon(Position position) {
		
	}

	public void emitAccount(Account account) {
		
	}

	public void emitContract(Contract contract) {
		
	}

}
