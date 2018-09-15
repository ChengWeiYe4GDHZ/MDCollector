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

import com.yqqtp.laoyouqian.api.CtpConstant;
import com.yqqtp.laoyouqian.entity.Account;
import com.yqqtp.laoyouqian.entity.CancelOrderReq;
import com.yqqtp.laoyouqian.entity.Contract;
import com.yqqtp.laoyouqian.entity.OrderReq;
import com.yqqtp.laoyouqian.entity.Position;
import com.yqqtp.laoyouqian.entity.RtConstant;
import com.yqqtp.laoyouqian.entity.Trade;
import com.yqqtp.laoyouqian.jctp.CThostFtdcAccountregisterField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcBatchOrderActionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcBrokerTradingAlgosField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcBrokerTradingParamsField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcBulletinField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcCFMMCTradingAccountKeyField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcCFMMCTradingAccountTokenField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcCancelAccountField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcChangeAccountField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcCombActionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcCombInstrumentGuardField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcContractBankField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcDepthMarketDataField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcEWarrantOffsetField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcErrorConditionalOrderField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcExchangeField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcExchangeMarginRateAdjustField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcExchangeMarginRateField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcExchangeRateField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcExecOrderActionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcExecOrderField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcForQuoteField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcForQuoteRspField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInputBatchOrderActionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInputCombActionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInputExecOrderActionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInputExecOrderField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInputForQuoteField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInputOptionSelfCloseActionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInputOptionSelfCloseField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInputOrderActionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInputOrderField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInputQuoteActionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInputQuoteField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInstrumentCommissionRateField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInstrumentField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInstrumentMarginRateField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInstrumentOrderCommRateField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInstrumentStatusField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInvestUnitField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInvestorField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInvestorPositionCombineDetailField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInvestorPositionDetailField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInvestorPositionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcInvestorProductGroupMarginField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcMMInstrumentCommissionRateField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcMMOptionInstrCommRateField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcNoticeField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcNotifyQueryAccountField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcOpenAccountField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcOptionInstrCommRateField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcOptionInstrTradeCostField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcOptionSelfCloseActionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcOptionSelfCloseField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcOrderActionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcOrderField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcParkedOrderActionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcParkedOrderField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcProductExchRateField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcProductField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcProductGroupField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcQryInstrumentField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcQryInvestorPositionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcQryTradingAccountField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcQueryCFMMCTradingAccountTokenField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcQueryMaxOrderVolumeField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcQuoteActionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcQuoteField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcRemoveParkedOrderActionField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcRemoveParkedOrderField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcReqAuthenticateField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcReqQueryAccountField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcReqRepealField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcReqTransferField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcReqUserLoginField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcRspAuthenticateField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcRspInfoField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcRspRepealField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcRspTransferField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcRspUserLoginField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcSecAgentACIDMapField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcSecAgentCheckModeField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcSettlementInfoConfirmField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcSettlementInfoField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcTradeField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcTraderApi;
import com.yqqtp.laoyouqian.jctp.CThostFtdcTraderSpi;
import com.yqqtp.laoyouqian.jctp.CThostFtdcTradingAccountField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcTradingAccountPasswordUpdateField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcTradingCodeField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcTradingNoticeField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcTradingNoticeInfoField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcTransferBankField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcTransferSerialField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcUserLogoutField;
import com.yqqtp.laoyouqian.jctp.CThostFtdcUserPasswordUpdateField;
import com.yqqtp.laoyouqian.jctp.ctpapiv6v3v11Constants;





public class TdSpi extends CThostFtdcTraderSpi {

	private Logger log = LoggerFactory.getLogger(TdSpi.class);
	private CtpGateway ctpGateway;
	// private String mdAddress;
	private String tdAddress;
	private String brokerID;
	private String userID;
	private String password;
	private String autoCode;
	private String userProductInfo;
	private String gatewayLogInfo;
	private String gatewayID;
	// private String gatewayDisplayName;

	private HashMap<String, Position> positionMap = new HashMap<>();

	private HashMap<String, String> contractExchangeMap;
	private HashMap<String, Integer> contractSizeMap;

	TdSpi(CtpGateway ctpGateway) {

		this.ctpGateway = ctpGateway;
		// this.mdAddress = ctpGateway.getGatewaySetting().getMdAddress();
		this.tdAddress = ctpGateway.getGatewaySetting().getTdAddress();
		this.brokerID = ctpGateway.getGatewaySetting().getBrokerID();
		this.userID = ctpGateway.getGatewaySetting().getUserID();
		this.password = ctpGateway.getGatewaySetting().getPassword();
		this.autoCode = ctpGateway.getGatewaySetting().getAuthCode();
		this.gatewayLogInfo = ctpGateway.getGatewayLogInfo();
		this.gatewayID = ctpGateway.getGatewayID();
		// this.gatewayDisplayName = ctpGateway.getGatewayDisplayName();

		this.contractExchangeMap = ctpGateway.getContractExchangeMap();
		this.contractSizeMap = ctpGateway.getContractSizeMap();

	}

	private CThostFtdcTraderApi cThostFtdcTraderApi;

	private boolean connectProcessStatus = false; // 避免重复调用
	private boolean connectionStatus = false; // 前置机连接状态
	private boolean loginStatus = false; // 登陆状态
	private String tradingDayStr;

	private volatile int reqID = 0; // 操作请求编号
	private volatile int orderRef = 0; // 订单编号

	private boolean authStatus = false; // 验证状态
	private boolean loginFailed = false; // 是否已经使用错误的信息尝试登录过

	private int frontID = 0; // 前置机编号
	private int sessionID = 0; // 会话编号

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
		if (cThostFtdcTraderApi != null) {
			cThostFtdcTraderApi.RegisterSpi(null);
			cThostFtdcTraderApi.Release();
			connectionStatus = false;
			loginStatus = false;

		}
		String envTmpDir = System.getProperty("java.io.tmpdir");
		String tempFilePath = envTmpDir + File.separator + "com" + File.separator + "yqqtp" + File.separator + "api"
				+ File.separator + "jctp" + File.separator + "TEMP_CTP" + File.separator + "TD_"
				+ ctpGateway.getGatewayID() + "_";
		File tempFile = new File(tempFilePath);
		if (!tempFile.getParentFile().exists()) {
			try {
				FileUtils.forceMkdirParent(tempFile);
				logContent = gatewayLogInfo + "创建临时文件夹" + tempFile.getParentFile().getAbsolutePath();
				log.info(logContent);
			} catch (IOException e) {
				logContent = gatewayLogInfo + "创建临时文件夹失败" + tempFile.getParentFile().getAbsolutePath();
				log.error(logContent, e);
			}
		}
		logContent = gatewayLogInfo + "使用临时文件夹" + tempFile.getParentFile().getAbsolutePath();
		log.info(logContent);
		cThostFtdcTraderApi = CThostFtdcTraderApi.CreateFtdcTraderApi(tempFile.getAbsolutePath());
		cThostFtdcTraderApi.RegisterSpi(this);
		cThostFtdcTraderApi.RegisterFront(tdAddress);
		cThostFtdcTraderApi.Init();
		connectProcessStatus = true;
		log.info("连接成功");
	}

	/**
	 * 关闭
	 */
	public synchronized void close() {
		if (!isConnected()) {
			return;
		}

		if (cThostFtdcTraderApi != null) {
			cThostFtdcTraderApi.RegisterSpi(null);
			cThostFtdcTraderApi.Release();
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
	public String getTradingDayDay() {
		return tradingDayStr;
	}

	/**
	 * 查询账户
	 */
	public void queryAccount() {
		if (cThostFtdcTraderApi == null) {
			log.info("{}尚未初始化,无法查询账户", gatewayLogInfo);
			return;
		}
		CThostFtdcQryTradingAccountField cThostFtdcQryTradingAccountField = new CThostFtdcQryTradingAccountField();
		reqID += 1;
		cThostFtdcTraderApi.ReqQryTradingAccount(cThostFtdcQryTradingAccountField, reqID);
	}

	/**
	 * 查询持仓
	 */
	public void queryPosition() {
		if (cThostFtdcTraderApi == null) {
			log.info("{}尚未初始化,无法查询持仓", gatewayLogInfo);
			return;
		}
		reqID += 1;

		CThostFtdcQryInvestorPositionField cThostFtdcQryInvestorPositionField = new CThostFtdcQryInvestorPositionField();
		// log.info("查询持仓");
		cThostFtdcQryInvestorPositionField.setBrokerID(brokerID);
		cThostFtdcQryInvestorPositionField.setInvestorID(userID);
		cThostFtdcTraderApi.ReqQryInvestorPosition(cThostFtdcQryInvestorPositionField, reqID);
	}

	/**
	 * 发单
	 * 
	 * @param orderReq
	 * @return
	 */
	public String sendOrder(OrderReq orderReq) {
		if (cThostFtdcTraderApi == null) {
			log.info("{}尚未初始化,无法发单", gatewayLogInfo);
			return null;
		}
		reqID += 1;
		orderRef += 1;
		CThostFtdcInputOrderField cThostFtdcInputOrderField = new CThostFtdcInputOrderField();

		cThostFtdcInputOrderField.setInstrumentID(orderReq.getSymbol());
		cThostFtdcInputOrderField.setLimitPrice(orderReq.getPrice());
		cThostFtdcInputOrderField.setVolumeTotalOriginal(orderReq.getVolume());

		cThostFtdcInputOrderField.setOrderPriceType(
				CtpConstant.priceTypeMap.getOrDefault(orderReq.getPriceType(), Character.valueOf('\0')));
		cThostFtdcInputOrderField
				.setDirection(CtpConstant.directionMap.getOrDefault(orderReq.getDirection(), Character.valueOf('\0')));
		cThostFtdcInputOrderField.setCombOffsetFlag(
				String.valueOf(CtpConstant.offsetMap.getOrDefault(orderReq.getOffset(), Character.valueOf('\0'))));
		cThostFtdcInputOrderField.setOrderRef(orderRef + "");
		cThostFtdcInputOrderField.setInvestorID(userID);
		cThostFtdcInputOrderField.setUserID(userID);
		cThostFtdcInputOrderField.setBrokerID(brokerID);

		cThostFtdcInputOrderField
				.setCombHedgeFlag(String.valueOf(ctpapiv6v3v11Constants.THOST_FTDC_HF_Speculation));
		cThostFtdcInputOrderField.setContingentCondition(ctpapiv6v3v11Constants.THOST_FTDC_CC_Immediately);
		cThostFtdcInputOrderField.setForceCloseReason(ctpapiv6v3v11Constants.THOST_FTDC_FCC_NotForceClose);
		cThostFtdcInputOrderField.setIsAutoSuspend(0);
		cThostFtdcInputOrderField.setTimeCondition(ctpapiv6v3v11Constants.THOST_FTDC_TC_GFD);
		cThostFtdcInputOrderField.setVolumeCondition(ctpapiv6v3v11Constants.THOST_FTDC_VC_AV);
		cThostFtdcInputOrderField.setMinVolume(1);

		// 判断FAK FOK市价单
		if (RtConstant.PRICETYPE_FAK.equals(orderReq.getPriceType())) {
			cThostFtdcInputOrderField.setOrderPriceType(ctpapiv6v3v11Constants.THOST_FTDC_OPT_LimitPrice);
			cThostFtdcInputOrderField.setTimeCondition(ctpapiv6v3v11Constants.THOST_FTDC_TC_IOC);
			cThostFtdcInputOrderField.setVolumeCondition(ctpapiv6v3v11Constants.THOST_FTDC_VC_AV);
		} else if (RtConstant.PRICETYPE_FOK.equals(orderReq.getPriceType())) {
			cThostFtdcInputOrderField.setOrderPriceType(ctpapiv6v3v11Constants.THOST_FTDC_OPT_LimitPrice);
			cThostFtdcInputOrderField.setTimeCondition(ctpapiv6v3v11Constants.THOST_FTDC_TC_IOC);
			cThostFtdcInputOrderField.setVolumeCondition(ctpapiv6v3v11Constants.THOST_FTDC_VC_CV);
		}
		
//		if("IH1805".equals(orderReq.getSymbol())) {
//			System.out.println("T2T-OrderBefore-"+System.nanoTime());
//		}
		cThostFtdcTraderApi.ReqOrderInsert(cThostFtdcInputOrderField, reqID);
//		if("IH1805".equals(orderReq.getSymbol())) {
//			System.out.println("T2T-Order-"+System.nanoTime());
//		}
		String rtOrderID = gatewayID + "." + orderRef;

		return rtOrderID;
	}

	// 撤单
	public void cancelOrder(CancelOrderReq cancelOrderReq) {

		if (cThostFtdcTraderApi == null) {
			log.info("{}尚未初始化,无法撤单", gatewayLogInfo);
			return;
		}
		reqID += 1;
		CThostFtdcInputOrderActionField cThostFtdcInputOrderActionField = new CThostFtdcInputOrderActionField();

		cThostFtdcInputOrderActionField.setInstrumentID(cancelOrderReq.getSymbol());
		cThostFtdcInputOrderActionField.setExchangeID(cancelOrderReq.getExchange());
		cThostFtdcInputOrderActionField.setOrderRef(cancelOrderReq.getOrderID());
		cThostFtdcInputOrderActionField.setFrontID(frontID);
		cThostFtdcInputOrderActionField.setSessionID(sessionID);

		cThostFtdcInputOrderActionField.setActionFlag(ctpapiv6v3v11Constants.THOST_FTDC_AF_Delete);
		cThostFtdcInputOrderActionField.setBrokerID(brokerID);
		cThostFtdcInputOrderActionField.setInvestorID(userID);

		cThostFtdcTraderApi.ReqOrderAction(cThostFtdcInputOrderActionField, reqID);
	}

	private void login() {
		if (loginFailed) {
			String logContent = gatewayLogInfo + "交易接口登录曾发生错误,不再登录,以防被锁";
			log.warn(logContent);
		}

		if (StringUtils.isEmpty(brokerID) || StringUtils.isEmpty(userID) || StringUtils.isEmpty(password)) {
			String logContent = gatewayLogInfo + "BrokerID UserID Password不允许为空";
			log.error(logContent);
			return;
		}

		if (!StringUtils.isEmpty(autoCode) && !authStatus) {
			// 验证
			CThostFtdcReqAuthenticateField authenticateField = new CThostFtdcReqAuthenticateField();
			authenticateField.setAuthCode(autoCode);
			authenticateField.setUserID(userID);
			authenticateField.setBrokerID(brokerID);
			authenticateField.setUserProductInfo(userProductInfo);
			reqID += 1;
			cThostFtdcTraderApi.ReqAuthenticate(authenticateField, reqID);
		} else {
			// 登录
			CThostFtdcReqUserLoginField userLoginField = new CThostFtdcReqUserLoginField();
			userLoginField.setBrokerID(brokerID);
			userLoginField.setUserID(userID);
			userLoginField.setPassword(password);
			cThostFtdcTraderApi.ReqUserLogin(userLoginField, 0);
		}
	}

	// 前置机联机回报
	public void OnFrontConnected() {
		String logContent = gatewayLogInfo + "交易接口前置机已连接";
		log.info(logContent);
		// 修改前置机连接状态为true
		connectionStatus = true;
		connectProcessStatus = false;
		login();
	}

	// 前置机断开回报
	public void OnFrontDisconnected(int nReason) {
		String logContent = gatewayLogInfo + "交易接口前置机已断开,Reason:" + nReason;
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
			this.sessionID = pRspUserLogin.getSessionID();
			this.frontID = pRspUserLogin.getFrontID();
			// 修改登录状态为true
			this.loginStatus = true;
			tradingDayStr = pRspUserLogin.getTradingDay();
			log.info("{}交易接口获取到的交易日为{}", gatewayLogInfo, tradingDayStr);

			// 确认结算单
			CThostFtdcSettlementInfoConfirmField settlementInfoConfirmField = new CThostFtdcSettlementInfoConfirmField();
			settlementInfoConfirmField.setBrokerID(brokerID);
			settlementInfoConfirmField.setInvestorID(userID);
			reqID += 1;
			cThostFtdcTraderApi.ReqSettlementInfoConfirm(settlementInfoConfirmField, reqID);

		} else {
			log.warn("{}交易接口登录回报错误! ErrorID:{},ErrorMsg:{}", gatewayLogInfo, pRspInfo.getErrorID(),
					pRspInfo.getErrorMsg());
			loginFailed = true;
		}

	}

	// 心跳警告
	public void OnHeartBeatWarning(int nTimeLapse) {
		String logContent = gatewayLogInfo + "交易接口心跳警告 nTimeLapse:" + nTimeLapse;
		log.warn(logContent);
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
		String logContent = MessageFormat.format("{0}交易接口错误回报!ErrorID:{1},ErrorMsg:{2},RequestID:{3},isLast{4}",
				gatewayLogInfo, pRspInfo.getErrorID(), pRspInfo.getErrorMsg(), nRequestID, bIsLast);
		log.error(logContent);

	}

	// 验证客户端回报
	public void OnRspAuthenticate(CThostFtdcRspAuthenticateField pRspAuthenticateField, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {

		if (pRspInfo.getErrorID() == 0) {
			authStatus = true;
			String logContent = gatewayLogInfo + "交易接口客户端验证成功";
			log.info(logContent);

			login();

		} else {
			log.warn("{}交易接口客户端验证失败! ErrorID:{},ErrorMsg:{}", gatewayLogInfo, pRspInfo.getErrorID(),
					pRspInfo.getErrorMsg());
			String logContent = gatewayLogInfo + "交易接口客户端验证成功";
			log.info(logContent);
		}

	}

	public void OnRspUserPasswordUpdate(CThostFtdcUserPasswordUpdateField pUserPasswordUpdate,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspTradingAccountPasswordUpdate(
			CThostFtdcTradingAccountPasswordUpdateField pTradingAccountPasswordUpdate, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	// 发单错误（柜台）
	public void OnRspOrderInsert(CThostFtdcInputOrderField pInputOrder, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {

		String symbol = pInputOrder.getInstrumentID();
		String exchange = CtpConstant.exchangeMapReverse.get(pInputOrder.getExchangeID());
		String rtSymbol = symbol + "." + exchange;
		String orderID = pInputOrder.getOrderRef();
		String rtOrderID = gatewayID + "." + orderID;
		String direction = CtpConstant.directionMapReverse.getOrDefault(pInputOrder.getDirection(),
				RtConstant.DIRECTION_UNKNOWN);
		String offset = CtpConstant.offsetMapReverse.getOrDefault(pInputOrder.getCombOffsetFlag(),
				RtConstant.OFFSET_UNKNOWN);
		double price = pInputOrder.getLimitPrice();
		int totalVolume = pInputOrder.getVolumeTotalOriginal();
		int tradedVolume = 0;
		String status = RtConstant.STATUS_REJECTED;
		String tradingDay = tradingDayStr;
		String orderDate = null;
		String orderTime = null;
		String cancelTime = null;
		String activeTime = null;
		String updateTime = null;

		ctpGateway.emitOrder(gatewayID, symbol, exchange, rtSymbol, orderID, rtOrderID, direction, offset, price,
				totalVolume, tradedVolume, status, tradingDay, orderDate, orderTime, cancelTime, activeTime, updateTime,
				frontID, sessionID);

		// 发送委托事件
		String logContent = MessageFormat.format("{0}交易接口发单错误回报(柜台)! ErrorID:{1},ErrorMsg:{2}", gatewayLogInfo,
				pRspInfo.getErrorID(), pRspInfo.getErrorMsg());
		log.error(logContent);

	}

	public void OnRspParkedOrderInsert(CThostFtdcParkedOrderField pParkedOrder, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspParkedOrderAction(CThostFtdcParkedOrderActionField pParkedOrderAction,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	// 撤单错误回报（柜台）
	public void OnRspOrderAction(CThostFtdcInputOrderActionField pInputOrderAction, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {

		String logContent = MessageFormat.format("{0}交易接口撤单错误（柜台）! ErrorID:{1},ErrorMsg:{2}", gatewayLogInfo,
				pRspInfo.getErrorID(), pRspInfo.getErrorMsg());
		log.error(logContent);
	}

	public void OnRspQueryMaxOrderVolume(CThostFtdcQueryMaxOrderVolumeField pQueryMaxOrderVolume,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	// 确认结算信息回报
	public void OnRspSettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField pSettlementInfoConfirm,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {

		if (pRspInfo.getErrorID() == 0) {
			String logContent = MessageFormat.format("{0}交易接口结算信息确认完成!", gatewayLogInfo, pRspInfo.getErrorID(),
					pRspInfo.getErrorMsg());
			log.warn(logContent);

		} else {
			String logContent = MessageFormat.format("{0}交易接口结算信息确认出错! ErrorID:{1},ErrorMsg:{2}", gatewayLogInfo,
					pRspInfo.getErrorID(), pRspInfo.getErrorMsg());
			log.error(logContent);
		}

		// 查询所有合约
		reqID += 1;
		CThostFtdcQryInstrumentField cThostFtdcQryInstrumentField = new CThostFtdcQryInstrumentField();
		cThostFtdcTraderApi.ReqQryInstrument(cThostFtdcQryInstrumentField, reqID);

	}

	public void OnRspRemoveParkedOrder(CThostFtdcRemoveParkedOrderField pRemoveParkedOrder,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspRemoveParkedOrderAction(CThostFtdcRemoveParkedOrderActionField pRemoveParkedOrderAction,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspExecOrderInsert(CThostFtdcInputExecOrderField pInputExecOrder, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspExecOrderAction(CThostFtdcInputExecOrderActionField pInputExecOrderAction,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspForQuoteInsert(CThostFtdcInputForQuoteField pInputForQuote, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQuoteInsert(CThostFtdcInputQuoteField pInputQuote, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	public void OnRspQuoteAction(CThostFtdcInputQuoteActionField pInputQuoteAction, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspBatchOrderAction(CThostFtdcInputBatchOrderActionField pInputBatchOrderAction,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspOptionSelfCloseInsert(CThostFtdcInputOptionSelfCloseField pInputOptionSelfClose,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspOptionSelfCloseAction(CThostFtdcInputOptionSelfCloseActionField pInputOptionSelfCloseAction,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspCombActionInsert(CThostFtdcInputCombActionField pInputCombAction, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryOrder(CThostFtdcOrderField pOrder, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	public void OnRspQryTrade(CThostFtdcTradeField pTrade, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	// 持仓查询回报
	public void OnRspQryInvestorPosition(CThostFtdcInvestorPositionField pInvestorPosition,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {

		if (pInvestorPosition == null || StringUtils.isEmpty(pInvestorPosition.getInstrumentID())) {
			return;
		}
		String symbol = pInvestorPosition.getInstrumentID();
		String rtSymbol = symbol;
		if (contractExchangeMap.containsKey(symbol)) {
			rtSymbol = symbol + "." + contractExchangeMap.get(symbol);
		}

		// 获取持仓缓存
		String posName = gatewayID + symbol + pInvestorPosition.getPosiDirection();

		Position position;
		if (positionMap.containsKey(posName)) {
			position = positionMap.get(posName);
		} else {
			position = new Position();
			positionMap.put(posName, position);
			position.setGatewayID(gatewayID);
			position.setSymbol(symbol);
			position.setRtSymbol(rtSymbol);
			position.setDirection(
					CtpConstant.posiDirectionMapReverse.getOrDefault(pInvestorPosition.getPosiDirection(), ""));
			position.setRtPositionName(gatewayID + rtSymbol + pInvestorPosition.getPosiDirection());
		}
		// 针对上期所持仓的今昨分条返回（有昨仓、无今仓）,读取昨仓数据
		if (pInvestorPosition.getYdPosition() > 0 && pInvestorPosition.getTodayPosition() == 0) {
			position.setYdPosition(pInvestorPosition.getPosition());
		}
		// 计算成本
		Integer size = contractSizeMap.get(symbol);
		Double cost = position.getPrice() * position.getPosition() * size;

		// 汇总总仓
		position.setPosition(position.getPosition() + pInvestorPosition.getPosition());
		position.setPositionProfit(position.getPositionProfit() + pInvestorPosition.getPositionProfit());

		// 计算持仓均价
		if (position.getPosition() != 0 && contractSizeMap.containsKey(symbol)) {
			position.setPrice((cost + pInvestorPosition.getPositionCost()) / (position.getPosition() * size));
		}

		if (RtConstant.DIRECTION_LONG.equals(position.getDirection())) {
			position.setFrozen(pInvestorPosition.getLongFrozen());
		} else {
			position.setFrozen(pInvestorPosition.getShortFrozen());
		}

		// 回报结束
		if (bIsLast) {
			for (Position tmpPosition : positionMap.values()) {
				// 发送持仓事件
				ctpGateway.emitPositon(tmpPosition);
			}

			// 清空缓存
			positionMap = new HashMap<>();
		}

	}

	// 账户查询回报
	public void OnRspQryTradingAccount(CThostFtdcTradingAccountField pTradingAccount, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		Account account = new Account();
		account.setAccountID(pTradingAccount.getAccountID());
		account.setAvailable(pTradingAccount.getAvailable());
		account.setCloseProfit(pTradingAccount.getCloseProfit());
		account.setCommission(pTradingAccount.getCommission());
		account.setGatewayID(gatewayID);
		account.setMargin(pTradingAccount.getCurrMargin());
		account.setPositionProfit(pTradingAccount.getPositionProfit());
		account.setPreBalance(pTradingAccount.getPreBalance());
		account.setRtAccountID(gatewayID + pTradingAccount.getAccountID());

		double balance = pTradingAccount.getPreBalance() - pTradingAccount.getPreCredit()
				- pTradingAccount.getPreMortgage() + pTradingAccount.getMortgage() - pTradingAccount.getWithdraw()
				+ pTradingAccount.getDeposit() + pTradingAccount.getCloseProfit() + pTradingAccount.getPositionProfit()
				+ pTradingAccount.getCashIn() - pTradingAccount.getCommission();

		account.setBalance(balance);

		ctpGateway.emitAccount(account);

	}

	public void OnRspQryInvestor(CThostFtdcInvestorField pInvestor, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	public void OnRspQryTradingCode(CThostFtdcTradingCodeField pTradingCode, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryInstrumentMarginRate(CThostFtdcInstrumentMarginRateField pInstrumentMarginRate,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryInstrumentCommissionRate(CThostFtdcInstrumentCommissionRateField pInstrumentCommissionRate,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryExchange(CThostFtdcExchangeField pExchange, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	public void OnRspQryProduct(CThostFtdcProductField pProduct, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	// 合约查询回报
	public void OnRspQryInstrument(CThostFtdcInstrumentField pInstrument, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		Contract contract = new Contract();
		contract.setGatewayID(gatewayID);
		contract.setSymbol(pInstrument.getInstrumentID());
		contract.setExchange(CtpConstant.exchangeMapReverse.get(pInstrument.getExchangeID()));
		contract.setRtSymbol(contract.getSymbol() + "." + contract.getExchange());
		contract.setName(pInstrument.getInstrumentName());

		contract.setSize(pInstrument.getVolumeMultiple());
		contract.setPriceTick(pInstrument.getPriceTick());
		contract.setStrikePrice(pInstrument.getStrikePrice());
		contract.setProductClass(CtpConstant.productClassMapReverse.getOrDefault(pInstrument.getProductClass(),
				RtConstant.PRODUCT_UNKNOWN));
		contract.setExpiryDate(pInstrument.getExpireDate());
		// 针对商品期权
		contract.setUnderlyingSymbol(pInstrument.getUnderlyingInstrID());
		// contract.setUnderlyingSymbol(pInstrument.getUnderlyingInstrID()+pInstrument.getExpireDate().substring(2,
		// pInstrument.getExpireDate().length()-2));

		if (RtConstant.PRODUCT_OPTION.equals(contract.getProductClass())) {
			if (pInstrument.getOptionsType() == '1') {
				contract.setOptionType(RtConstant.OPTION_CALL);
			} else if (pInstrument.getOptionsType() == '2') {
				contract.setOptionType(RtConstant.OPTION_PUT);
			}
		}
		contractExchangeMap.put(contract.getSymbol(), contract.getExchange());
		contractSizeMap.put(contract.getSymbol(), contract.getSize());

		ctpGateway.emitContract(contract);

		if (bIsLast) {
			String logContent = gatewayLogInfo + "交易接口合约信息获取完成";
			log.info(logContent);
		}
	}

	public void OnRspQryDepthMarketData(CThostFtdcDepthMarketDataField pDepthMarketData,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQrySettlementInfo(CThostFtdcSettlementInfoField pSettlementInfo, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryTransferBank(CThostFtdcTransferBankField pTransferBank, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryInvestorPositionDetail(CThostFtdcInvestorPositionDetailField pInvestorPositionDetail,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryNotice(CThostFtdcNoticeField pNotice, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	public void OnRspQrySettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField pSettlementInfoConfirm,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryInvestorPositionCombineDetail(
			CThostFtdcInvestorPositionCombineDetailField pInvestorPositionCombineDetail,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryCFMMCTradingAccountKey(CThostFtdcCFMMCTradingAccountKeyField pCFMMCTradingAccountKey,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryEWarrantOffset(CThostFtdcEWarrantOffsetField pEWarrantOffset, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryInvestorProductGroupMargin(
			CThostFtdcInvestorProductGroupMarginField pInvestorProductGroupMargin, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryExchangeMarginRate(CThostFtdcExchangeMarginRateField pExchangeMarginRate,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryExchangeMarginRateAdjust(CThostFtdcExchangeMarginRateAdjustField pExchangeMarginRateAdjust,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryExchangeRate(CThostFtdcExchangeRateField pExchangeRate, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQrySecAgentACIDMap(CThostFtdcSecAgentACIDMapField pSecAgentACIDMap,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryProductExchRate(CThostFtdcProductExchRateField pProductExchRate,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryProductGroup(CThostFtdcProductGroupField pProductGroup, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryMMInstrumentCommissionRate(
			CThostFtdcMMInstrumentCommissionRateField pMMInstrumentCommissionRate, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryMMOptionInstrCommRate(CThostFtdcMMOptionInstrCommRateField pMMOptionInstrCommRate,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryInstrumentOrderCommRate(CThostFtdcInstrumentOrderCommRateField pInstrumentOrderCommRate,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQrySecAgentTradingAccount(CThostFtdcTradingAccountField pTradingAccount,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQrySecAgentCheckMode(CThostFtdcSecAgentCheckModeField pSecAgentCheckMode,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryOptionInstrTradeCost(CThostFtdcOptionInstrTradeCostField pOptionInstrTradeCost,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryOptionInstrCommRate(CThostFtdcOptionInstrCommRateField pOptionInstrCommRate,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryExecOrder(CThostFtdcExecOrderField pExecOrder, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	public void OnRspQryForQuote(CThostFtdcForQuoteField pForQuote, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	public void OnRspQryQuote(CThostFtdcQuoteField pQuote, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	public void OnRspQryOptionSelfClose(CThostFtdcOptionSelfCloseField pOptionSelfClose,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryInvestUnit(CThostFtdcInvestUnitField pInvestUnit, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryCombInstrumentGuard(CThostFtdcCombInstrumentGuardField pCombInstrumentGuard,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryCombAction(CThostFtdcCombActionField pCombAction, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryTransferSerial(CThostFtdcTransferSerialField pTransferSerial, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryAccountregister(CThostFtdcAccountregisterField pAccountregister,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	// 委托回报
	public void OnRtnOrder(CThostFtdcOrderField pOrder) {

		String newRef = pOrder.getOrderRef().replace(" ", "");
		// 更新最大报单编号
		orderRef = Math.max(orderRef, Integer.valueOf(newRef));

		String symbol = pOrder.getInstrumentID();
		String exchange = CtpConstant.exchangeMapReverse.get(pOrder.getExchangeID());
		String rtSymbol = symbol + "." + exchange;
		/*
		 * CTP的报单号一致性维护需要基于frontID, sessionID, orderID三个字段
		 * 但在本接口设计中,已经考虑了CTP的OrderRef的自增性,避免重复 唯一可能出现OrderRef重复的情况是多处登录并在非常接近的时间内（几乎同时发单
		 */
		String orderID = pOrder.getOrderRef();
		String rtOrderID = gatewayID + "." + orderID;
		String direction = CtpConstant.directionMapReverse.get(pOrder.getDirection());
		String offset = CtpConstant.offsetMapReverse.get(pOrder.getCombOffsetFlag().toCharArray()[0]);
		double price = pOrder.getLimitPrice();
		int totalVolume = pOrder.getVolumeTotalOriginal();
		int tradedVolume = pOrder.getVolumeTraded();
		String status = CtpConstant.statusMapReverse.get(pOrder.getOrderStatus());
		String tradingDay = tradingDayStr;
		String orderDate = pOrder.getInsertDate();
		String orderTime = pOrder.getInsertTime();
		String cancelTime = pOrder.getCancelTime();
		String activeTime = pOrder.getActiveTime();
		String updateTime = pOrder.getUpdateTime();

		ctpGateway.emitOrder(gatewayID, symbol, exchange, rtSymbol, orderID, rtOrderID, direction, offset, price,
				totalVolume, tradedVolume, status, tradingDay, orderDate, orderTime, cancelTime, activeTime, updateTime,
				frontID, sessionID);

	}

	// 成交回报
	public void OnRtnTrade(CThostFtdcTradeField pTrade) {

		Trade trade = new Trade();
		trade.setGatewayID(gatewayID);
		trade.setSymbol(pTrade.getInstrumentID());
		trade.setExchange(CtpConstant.exchangeMapReverse.get(pTrade.getExchangeID()));
		trade.setRtSymbol(trade.getSymbol() + "." + trade.getExchange());

		trade.setTradeID(pTrade.getTradeID());
		trade.setRtTradeID(gatewayID + "." + trade.getTradeID());

		trade.setOrderID(pTrade.getOrderRef());
		trade.setRtOrderID(gatewayID + "." + pTrade.getOrderRef());

		// 方向
		trade.setDirection(CtpConstant.directionMapReverse.getOrDefault(pTrade.getDirection(), ""));

		// 开平
		trade.setOffset(CtpConstant.offsetMapReverse.getOrDefault(pTrade.getOffsetFlag(), ""));

		trade.setPrice(pTrade.getPrice());
		trade.setVolume(pTrade.getVolume());
		trade.setTradeTime(pTrade.getTradeTime());
		trade.setTradeDate(pTrade.getTradeDate());

		String symbol = pTrade.getInstrumentID();
		String exchange = CtpConstant.exchangeMapReverse.get(pTrade.getExchangeID());
		String rtSymbol = trade.getSymbol() + "." + trade.getExchange();
		String tradeID = pTrade.getTradeID();
		String rtTradeID = gatewayID + "." + trade.getTradeID();
		String orderID = pTrade.getOrderRef();
		String rtOrderID = gatewayID + "." + pTrade.getOrderRef();
		String direction = CtpConstant.directionMapReverse.getOrDefault(pTrade.getDirection(), "");
		String offset = CtpConstant.offsetMapReverse.getOrDefault(pTrade.getOffsetFlag(), "");
		double price = pTrade.getPrice();
		int volume = pTrade.getVolume();
		String tradingDay = tradingDayStr;
		String tradeDate = pTrade.getTradeDate();
		String tradeTime = pTrade.getTradeTime();
		DateTime dateTime = null;

		ctpGateway.emitTrade(gatewayID, symbol, exchange, rtSymbol, tradeID, rtTradeID, orderID, rtOrderID, direction,
				offset, price, volume, tradingDay, tradeDate, tradeTime, dateTime);
	}

	// 发单错误回报（交易所）
	public void OnErrRtnOrderInsert(CThostFtdcInputOrderField pInputOrder, CThostFtdcRspInfoField pRspInfo) {

		String symbol = pInputOrder.getInstrumentID();
		String exchange = CtpConstant.exchangeMapReverse.get(pInputOrder.getExchangeID());
		String rtSymbol = symbol + "." + exchange;
		String orderID = pInputOrder.getOrderRef();
		String rtOrderID = gatewayID + "." + orderID;
		String direction = CtpConstant.directionMapReverse.get(pInputOrder.getDirection());
		String offset = CtpConstant.offsetMapReverse.get(pInputOrder.getCombOffsetFlag().toCharArray()[0]);
		double price = pInputOrder.getLimitPrice();
		int totalVolume = pInputOrder.getVolumeTotalOriginal();
		int tradedVolume = 0;
		String status = RtConstant.STATUS_REJECTED;
		String tradingDay = tradingDayStr;
		String orderDate = null;
		String orderTime = null;
		String cancelTime = null;
		String activeTime = null;
		String updateTime = null;

		ctpGateway.emitOrder(gatewayID, symbol, exchange, rtSymbol, orderID, rtOrderID, direction, offset, price,
				totalVolume, tradedVolume, status, tradingDay, orderDate, orderTime, cancelTime, activeTime, updateTime,
				frontID, sessionID);
		String logContent = MessageFormat.format("{0}交易接口发单错误回报（交易所）! ErrorID:{1},ErrorMsg:{2}", gatewayLogInfo,
				pRspInfo.getErrorID(), pRspInfo.getErrorMsg());
		log.error(logContent);

	}

	// 撤单错误回报（交易所）
	public void OnErrRtnOrderAction(CThostFtdcOrderActionField pOrderAction, CThostFtdcRspInfoField pRspInfo) {
		String logContent = MessageFormat.format("{0}交易接口撤单错误回报（交易所）! ErrorID:{1},ErrorMsg:{2}", gatewayLogInfo,
				pRspInfo.getErrorID(), pRspInfo.getErrorMsg());
		log.error(logContent);
	}

	public void OnRtnInstrumentStatus(CThostFtdcInstrumentStatusField pInstrumentStatus) {
	}

	public void OnRtnBulletin(CThostFtdcBulletinField pBulletin) {
	}

	public void OnRtnTradingNotice(CThostFtdcTradingNoticeInfoField pTradingNoticeInfo) {
	}

	public void OnRtnErrorConditionalOrder(CThostFtdcErrorConditionalOrderField pErrorConditionalOrder) {
	}

	public void OnRtnExecOrder(CThostFtdcExecOrderField pExecOrder) {
	}

	public void OnErrRtnExecOrderInsert(CThostFtdcInputExecOrderField pInputExecOrder,
			CThostFtdcRspInfoField pRspInfo) {
	}

	public void OnErrRtnExecOrderAction(CThostFtdcExecOrderActionField pExecOrderAction,
			CThostFtdcRspInfoField pRspInfo) {
	}

	public void OnErrRtnForQuoteInsert(CThostFtdcInputForQuoteField pInputForQuote, CThostFtdcRspInfoField pRspInfo) {
	}

	public void OnRtnQuote(CThostFtdcQuoteField pQuote) {
	}

	public void OnErrRtnQuoteInsert(CThostFtdcInputQuoteField pInputQuote, CThostFtdcRspInfoField pRspInfo) {
	}

	public void OnErrRtnQuoteAction(CThostFtdcQuoteActionField pQuoteAction, CThostFtdcRspInfoField pRspInfo) {
	}

	public void OnRtnForQuoteRsp(CThostFtdcForQuoteRspField pForQuoteRsp) {
	}

	public void OnRtnCFMMCTradingAccountToken(CThostFtdcCFMMCTradingAccountTokenField pCFMMCTradingAccountToken) {
	}

	public void OnErrRtnBatchOrderAction(CThostFtdcBatchOrderActionField pBatchOrderAction,
			CThostFtdcRspInfoField pRspInfo) {
	}

	public void OnRtnOptionSelfClose(CThostFtdcOptionSelfCloseField pOptionSelfClose) {
	}

	public void OnErrRtnOptionSelfCloseInsert(CThostFtdcInputOptionSelfCloseField pInputOptionSelfClose,
			CThostFtdcRspInfoField pRspInfo) {
	}

	public void OnErrRtnOptionSelfCloseAction(CThostFtdcOptionSelfCloseActionField pOptionSelfCloseAction,
			CThostFtdcRspInfoField pRspInfo) {
	}

	public void OnRtnCombAction(CThostFtdcCombActionField pCombAction) {
	}

	public void OnErrRtnCombActionInsert(CThostFtdcInputCombActionField pInputCombAction,
			CThostFtdcRspInfoField pRspInfo) {
	}

	public void OnRspQryContractBank(CThostFtdcContractBankField pContractBank, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryParkedOrder(CThostFtdcParkedOrderField pParkedOrder, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryParkedOrderAction(CThostFtdcParkedOrderActionField pParkedOrderAction,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryTradingNotice(CThostFtdcTradingNoticeField pTradingNotice, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryBrokerTradingParams(CThostFtdcBrokerTradingParamsField pBrokerTradingParams,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQryBrokerTradingAlgos(CThostFtdcBrokerTradingAlgosField pBrokerTradingAlgos,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRspQueryCFMMCTradingAccountToken(
			CThostFtdcQueryCFMMCTradingAccountTokenField pQueryCFMMCTradingAccountToken,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRtnFromBankToFutureByBank(CThostFtdcRspTransferField pRspTransfer) {
	}

	public void OnRtnFromFutureToBankByBank(CThostFtdcRspTransferField pRspTransfer) {
	}

	public void OnRtnRepealFromBankToFutureByBank(CThostFtdcRspRepealField pRspRepeal) {
	}

	public void OnRtnRepealFromFutureToBankByBank(CThostFtdcRspRepealField pRspRepeal) {
	}

	public void OnRtnFromBankToFutureByFuture(CThostFtdcRspTransferField pRspTransfer) {
	}

	public void OnRtnFromFutureToBankByFuture(CThostFtdcRspTransferField pRspTransfer) {
	}

	public void OnRtnRepealFromBankToFutureByFutureManual(CThostFtdcRspRepealField pRspRepeal) {
	}

	public void OnRtnRepealFromFutureToBankByFutureManual(CThostFtdcRspRepealField pRspRepeal) {
	}

	public void OnRtnQueryBankBalanceByFuture(CThostFtdcNotifyQueryAccountField pNotifyQueryAccount) {
	}

	public void OnErrRtnBankToFutureByFuture(CThostFtdcReqTransferField pReqTransfer, CThostFtdcRspInfoField pRspInfo) {
	}

	public void OnErrRtnFutureToBankByFuture(CThostFtdcReqTransferField pReqTransfer, CThostFtdcRspInfoField pRspInfo) {
	}

	public void OnErrRtnRepealBankToFutureByFutureManual(CThostFtdcReqRepealField pReqRepeal,
			CThostFtdcRspInfoField pRspInfo) {
	}

	public void OnErrRtnRepealFutureToBankByFutureManual(CThostFtdcReqRepealField pReqRepeal,
			CThostFtdcRspInfoField pRspInfo) {
	}

	public void OnErrRtnQueryBankBalanceByFuture(CThostFtdcReqQueryAccountField pReqQueryAccount,
			CThostFtdcRspInfoField pRspInfo) {
	}

	public void OnRtnRepealFromBankToFutureByFuture(CThostFtdcRspRepealField pRspRepeal) {
	}

	public void OnRtnRepealFromFutureToBankByFuture(CThostFtdcRspRepealField pRspRepeal) {
	}

	public void OnRspFromBankToFutureByFuture(CThostFtdcReqTransferField pReqTransfer, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspFromFutureToBankByFuture(CThostFtdcReqTransferField pReqTransfer, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	public void OnRspQueryBankAccountMoneyByFuture(CThostFtdcReqQueryAccountField pReqQueryAccount,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	public void OnRtnOpenAccountByBank(CThostFtdcOpenAccountField pOpenAccount) {
	}

	public void OnRtnCancelAccountByBank(CThostFtdcCancelAccountField pCancelAccount) {
	}

	public void OnRtnChangeAccountByBank(CThostFtdcChangeAccountField pChangeAccount) {
	}
}