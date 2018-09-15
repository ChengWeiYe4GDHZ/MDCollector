/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.yqqtp.laoyouqian.jctp;

public class CThostFtdcInvestorPositionCombineDetailField {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected CThostFtdcInvestorPositionCombineDetailField(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(CThostFtdcInvestorPositionCombineDetailField obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ctpapiv6v3v11JNI.delete_CThostFtdcInvestorPositionCombineDetailField(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setTradingDay(String value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_TradingDay_set(swigCPtr, this, value);
  }

  public String getTradingDay() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_TradingDay_get(swigCPtr, this);
  }

  public void setOpenDate(String value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_OpenDate_set(swigCPtr, this, value);
  }

  public String getOpenDate() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_OpenDate_get(swigCPtr, this);
  }

  public void setExchangeID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_ExchangeID_set(swigCPtr, this, value);
  }

  public String getExchangeID() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_ExchangeID_get(swigCPtr, this);
  }

  public void setSettlementID(int value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_SettlementID_set(swigCPtr, this, value);
  }

  public int getSettlementID() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_SettlementID_get(swigCPtr, this);
  }

  public void setBrokerID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_BrokerID_set(swigCPtr, this, value);
  }

  public String getBrokerID() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_BrokerID_get(swigCPtr, this);
  }

  public void setInvestorID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_InvestorID_set(swigCPtr, this, value);
  }

  public String getInvestorID() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_InvestorID_get(swigCPtr, this);
  }

  public void setComTradeID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_ComTradeID_set(swigCPtr, this, value);
  }

  public String getComTradeID() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_ComTradeID_get(swigCPtr, this);
  }

  public void setTradeID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_TradeID_set(swigCPtr, this, value);
  }

  public String getTradeID() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_TradeID_get(swigCPtr, this);
  }

  public void setInstrumentID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_InstrumentID_set(swigCPtr, this, value);
  }

  public String getInstrumentID() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_InstrumentID_get(swigCPtr, this);
  }

  public void setHedgeFlag(char value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_HedgeFlag_set(swigCPtr, this, value);
  }

  public char getHedgeFlag() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_HedgeFlag_get(swigCPtr, this);
  }

  public void setDirection(char value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_Direction_set(swigCPtr, this, value);
  }

  public char getDirection() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_Direction_get(swigCPtr, this);
  }

  public void setTotalAmt(int value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_TotalAmt_set(swigCPtr, this, value);
  }

  public int getTotalAmt() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_TotalAmt_get(swigCPtr, this);
  }

  public void setMargin(double value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_Margin_set(swigCPtr, this, value);
  }

  public double getMargin() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_Margin_get(swigCPtr, this);
  }

  public void setExchMargin(double value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_ExchMargin_set(swigCPtr, this, value);
  }

  public double getExchMargin() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_ExchMargin_get(swigCPtr, this);
  }

  public void setMarginRateByMoney(double value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_MarginRateByMoney_set(swigCPtr, this, value);
  }

  public double getMarginRateByMoney() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_MarginRateByMoney_get(swigCPtr, this);
  }

  public void setMarginRateByVolume(double value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_MarginRateByVolume_set(swigCPtr, this, value);
  }

  public double getMarginRateByVolume() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_MarginRateByVolume_get(swigCPtr, this);
  }

  public void setLegID(int value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_LegID_set(swigCPtr, this, value);
  }

  public int getLegID() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_LegID_get(swigCPtr, this);
  }

  public void setLegMultiple(int value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_LegMultiple_set(swigCPtr, this, value);
  }

  public int getLegMultiple() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_LegMultiple_get(swigCPtr, this);
  }

  public void setCombInstrumentID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_CombInstrumentID_set(swigCPtr, this, value);
  }

  public String getCombInstrumentID() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_CombInstrumentID_get(swigCPtr, this);
  }

  public void setTradeGroupID(int value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_TradeGroupID_set(swigCPtr, this, value);
  }

  public int getTradeGroupID() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_TradeGroupID_get(swigCPtr, this);
  }

  public void setInvestUnitID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_InvestUnitID_set(swigCPtr, this, value);
  }

  public String getInvestUnitID() {
    return ctpapiv6v3v11JNI.CThostFtdcInvestorPositionCombineDetailField_InvestUnitID_get(swigCPtr, this);
  }

  public CThostFtdcInvestorPositionCombineDetailField() {
    this(ctpapiv6v3v11JNI.new_CThostFtdcInvestorPositionCombineDetailField(), true);
  }

}