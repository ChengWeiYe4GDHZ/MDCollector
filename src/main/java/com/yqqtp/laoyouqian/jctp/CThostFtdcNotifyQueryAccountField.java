/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.yqqtp.laoyouqian.jctp;

public class CThostFtdcNotifyQueryAccountField {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected CThostFtdcNotifyQueryAccountField(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(CThostFtdcNotifyQueryAccountField obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ctpapiv6v3v11JNI.delete_CThostFtdcNotifyQueryAccountField(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setTradeCode(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_TradeCode_set(swigCPtr, this, value);
  }

  public String getTradeCode() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_TradeCode_get(swigCPtr, this);
  }

  public void setBankID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankID_set(swigCPtr, this, value);
  }

  public String getBankID() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankID_get(swigCPtr, this);
  }

  public void setBankBranchID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankBranchID_set(swigCPtr, this, value);
  }

  public String getBankBranchID() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankBranchID_get(swigCPtr, this);
  }

  public void setBrokerID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BrokerID_set(swigCPtr, this, value);
  }

  public String getBrokerID() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BrokerID_get(swigCPtr, this);
  }

  public void setBrokerBranchID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BrokerBranchID_set(swigCPtr, this, value);
  }

  public String getBrokerBranchID() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BrokerBranchID_get(swigCPtr, this);
  }

  public void setTradeDate(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_TradeDate_set(swigCPtr, this, value);
  }

  public String getTradeDate() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_TradeDate_get(swigCPtr, this);
  }

  public void setTradeTime(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_TradeTime_set(swigCPtr, this, value);
  }

  public String getTradeTime() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_TradeTime_get(swigCPtr, this);
  }

  public void setBankSerial(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankSerial_set(swigCPtr, this, value);
  }

  public String getBankSerial() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankSerial_get(swigCPtr, this);
  }

  public void setTradingDay(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_TradingDay_set(swigCPtr, this, value);
  }

  public String getTradingDay() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_TradingDay_get(swigCPtr, this);
  }

  public void setPlateSerial(int value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_PlateSerial_set(swigCPtr, this, value);
  }

  public int getPlateSerial() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_PlateSerial_get(swigCPtr, this);
  }

  public void setLastFragment(char value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_LastFragment_set(swigCPtr, this, value);
  }

  public char getLastFragment() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_LastFragment_get(swigCPtr, this);
  }

  public void setSessionID(int value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_SessionID_set(swigCPtr, this, value);
  }

  public int getSessionID() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_SessionID_get(swigCPtr, this);
  }

  public void setCustomerName(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_CustomerName_set(swigCPtr, this, value);
  }

  public String getCustomerName() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_CustomerName_get(swigCPtr, this);
  }

  public void setIdCardType(char value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_IdCardType_set(swigCPtr, this, value);
  }

  public char getIdCardType() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_IdCardType_get(swigCPtr, this);
  }

  public void setIdentifiedCardNo(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_IdentifiedCardNo_set(swigCPtr, this, value);
  }

  public String getIdentifiedCardNo() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_IdentifiedCardNo_get(swigCPtr, this);
  }

  public void setCustType(char value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_CustType_set(swigCPtr, this, value);
  }

  public char getCustType() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_CustType_get(swigCPtr, this);
  }

  public void setBankAccount(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankAccount_set(swigCPtr, this, value);
  }

  public String getBankAccount() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankAccount_get(swigCPtr, this);
  }

  public void setBankPassWord(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankPassWord_set(swigCPtr, this, value);
  }

  public String getBankPassWord() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankPassWord_get(swigCPtr, this);
  }

  public void setAccountID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_AccountID_set(swigCPtr, this, value);
  }

  public String getAccountID() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_AccountID_get(swigCPtr, this);
  }

  public void setPassword(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_Password_set(swigCPtr, this, value);
  }

  public String getPassword() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_Password_get(swigCPtr, this);
  }

  public void setFutureSerial(int value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_FutureSerial_set(swigCPtr, this, value);
  }

  public int getFutureSerial() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_FutureSerial_get(swigCPtr, this);
  }

  public void setInstallID(int value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_InstallID_set(swigCPtr, this, value);
  }

  public int getInstallID() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_InstallID_get(swigCPtr, this);
  }

  public void setUserID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_UserID_set(swigCPtr, this, value);
  }

  public String getUserID() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_UserID_get(swigCPtr, this);
  }

  public void setVerifyCertNoFlag(char value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_VerifyCertNoFlag_set(swigCPtr, this, value);
  }

  public char getVerifyCertNoFlag() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_VerifyCertNoFlag_get(swigCPtr, this);
  }

  public void setCurrencyID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_CurrencyID_set(swigCPtr, this, value);
  }

  public String getCurrencyID() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_CurrencyID_get(swigCPtr, this);
  }

  public void setDigest(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_Digest_set(swigCPtr, this, value);
  }

  public String getDigest() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_Digest_get(swigCPtr, this);
  }

  public void setBankAccType(char value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankAccType_set(swigCPtr, this, value);
  }

  public char getBankAccType() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankAccType_get(swigCPtr, this);
  }

  public void setDeviceID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_DeviceID_set(swigCPtr, this, value);
  }

  public String getDeviceID() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_DeviceID_get(swigCPtr, this);
  }

  public void setBankSecuAccType(char value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankSecuAccType_set(swigCPtr, this, value);
  }

  public char getBankSecuAccType() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankSecuAccType_get(swigCPtr, this);
  }

  public void setBrokerIDByBank(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BrokerIDByBank_set(swigCPtr, this, value);
  }

  public String getBrokerIDByBank() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BrokerIDByBank_get(swigCPtr, this);
  }

  public void setBankSecuAcc(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankSecuAcc_set(swigCPtr, this, value);
  }

  public String getBankSecuAcc() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankSecuAcc_get(swigCPtr, this);
  }

  public void setBankPwdFlag(char value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankPwdFlag_set(swigCPtr, this, value);
  }

  public char getBankPwdFlag() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankPwdFlag_get(swigCPtr, this);
  }

  public void setSecuPwdFlag(char value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_SecuPwdFlag_set(swigCPtr, this, value);
  }

  public char getSecuPwdFlag() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_SecuPwdFlag_get(swigCPtr, this);
  }

  public void setOperNo(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_OperNo_set(swigCPtr, this, value);
  }

  public String getOperNo() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_OperNo_get(swigCPtr, this);
  }

  public void setRequestID(int value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_RequestID_set(swigCPtr, this, value);
  }

  public int getRequestID() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_RequestID_get(swigCPtr, this);
  }

  public void setTID(int value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_TID_set(swigCPtr, this, value);
  }

  public int getTID() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_TID_get(swigCPtr, this);
  }

  public void setBankUseAmount(double value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankUseAmount_set(swigCPtr, this, value);
  }

  public double getBankUseAmount() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankUseAmount_get(swigCPtr, this);
  }

  public void setBankFetchAmount(double value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankFetchAmount_set(swigCPtr, this, value);
  }

  public double getBankFetchAmount() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_BankFetchAmount_get(swigCPtr, this);
  }

  public void setErrorID(int value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_ErrorID_set(swigCPtr, this, value);
  }

  public int getErrorID() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_ErrorID_get(swigCPtr, this);
  }

  public void setErrorMsg(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_ErrorMsg_set(swigCPtr, this, value);
  }

  public String getErrorMsg() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_ErrorMsg_get(swigCPtr, this);
  }

  public void setLongCustomerName(String value) {
    ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_LongCustomerName_set(swigCPtr, this, value);
  }

  public String getLongCustomerName() {
    return ctpapiv6v3v11JNI.CThostFtdcNotifyQueryAccountField_LongCustomerName_get(swigCPtr, this);
  }

  public CThostFtdcNotifyQueryAccountField() {
    this(ctpapiv6v3v11JNI.new_CThostFtdcNotifyQueryAccountField(), true);
  }

}