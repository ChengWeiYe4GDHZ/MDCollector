/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.yqqtp.laoyouqian.jctp;

public class CThostFtdcRspRepealField {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected CThostFtdcRspRepealField(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(CThostFtdcRspRepealField obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ctpapiv6v3v11JNI.delete_CThostFtdcRspRepealField(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setRepealTimeInterval(int value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_RepealTimeInterval_set(swigCPtr, this, value);
  }

  public int getRepealTimeInterval() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_RepealTimeInterval_get(swigCPtr, this);
  }

  public void setRepealedTimes(int value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_RepealedTimes_set(swigCPtr, this, value);
  }

  public int getRepealedTimes() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_RepealedTimes_get(swigCPtr, this);
  }

  public void setBankRepealFlag(char value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankRepealFlag_set(swigCPtr, this, value);
  }

  public char getBankRepealFlag() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankRepealFlag_get(swigCPtr, this);
  }

  public void setBrokerRepealFlag(char value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BrokerRepealFlag_set(swigCPtr, this, value);
  }

  public char getBrokerRepealFlag() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BrokerRepealFlag_get(swigCPtr, this);
  }

  public void setPlateRepealSerial(int value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_PlateRepealSerial_set(swigCPtr, this, value);
  }

  public int getPlateRepealSerial() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_PlateRepealSerial_get(swigCPtr, this);
  }

  public void setBankRepealSerial(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankRepealSerial_set(swigCPtr, this, value);
  }

  public String getBankRepealSerial() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankRepealSerial_get(swigCPtr, this);
  }

  public void setFutureRepealSerial(int value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_FutureRepealSerial_set(swigCPtr, this, value);
  }

  public int getFutureRepealSerial() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_FutureRepealSerial_get(swigCPtr, this);
  }

  public void setTradeCode(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_TradeCode_set(swigCPtr, this, value);
  }

  public String getTradeCode() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_TradeCode_get(swigCPtr, this);
  }

  public void setBankID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankID_set(swigCPtr, this, value);
  }

  public String getBankID() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankID_get(swigCPtr, this);
  }

  public void setBankBranchID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankBranchID_set(swigCPtr, this, value);
  }

  public String getBankBranchID() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankBranchID_get(swigCPtr, this);
  }

  public void setBrokerID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BrokerID_set(swigCPtr, this, value);
  }

  public String getBrokerID() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BrokerID_get(swigCPtr, this);
  }

  public void setBrokerBranchID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BrokerBranchID_set(swigCPtr, this, value);
  }

  public String getBrokerBranchID() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BrokerBranchID_get(swigCPtr, this);
  }

  public void setTradeDate(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_TradeDate_set(swigCPtr, this, value);
  }

  public String getTradeDate() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_TradeDate_get(swigCPtr, this);
  }

  public void setTradeTime(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_TradeTime_set(swigCPtr, this, value);
  }

  public String getTradeTime() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_TradeTime_get(swigCPtr, this);
  }

  public void setBankSerial(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankSerial_set(swigCPtr, this, value);
  }

  public String getBankSerial() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankSerial_get(swigCPtr, this);
  }

  public void setTradingDay(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_TradingDay_set(swigCPtr, this, value);
  }

  public String getTradingDay() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_TradingDay_get(swigCPtr, this);
  }

  public void setPlateSerial(int value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_PlateSerial_set(swigCPtr, this, value);
  }

  public int getPlateSerial() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_PlateSerial_get(swigCPtr, this);
  }

  public void setLastFragment(char value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_LastFragment_set(swigCPtr, this, value);
  }

  public char getLastFragment() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_LastFragment_get(swigCPtr, this);
  }

  public void setSessionID(int value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_SessionID_set(swigCPtr, this, value);
  }

  public int getSessionID() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_SessionID_get(swigCPtr, this);
  }

  public void setCustomerName(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_CustomerName_set(swigCPtr, this, value);
  }

  public String getCustomerName() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_CustomerName_get(swigCPtr, this);
  }

  public void setIdCardType(char value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_IdCardType_set(swigCPtr, this, value);
  }

  public char getIdCardType() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_IdCardType_get(swigCPtr, this);
  }

  public void setIdentifiedCardNo(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_IdentifiedCardNo_set(swigCPtr, this, value);
  }

  public String getIdentifiedCardNo() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_IdentifiedCardNo_get(swigCPtr, this);
  }

  public void setCustType(char value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_CustType_set(swigCPtr, this, value);
  }

  public char getCustType() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_CustType_get(swigCPtr, this);
  }

  public void setBankAccount(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankAccount_set(swigCPtr, this, value);
  }

  public String getBankAccount() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankAccount_get(swigCPtr, this);
  }

  public void setBankPassWord(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankPassWord_set(swigCPtr, this, value);
  }

  public String getBankPassWord() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankPassWord_get(swigCPtr, this);
  }

  public void setAccountID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_AccountID_set(swigCPtr, this, value);
  }

  public String getAccountID() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_AccountID_get(swigCPtr, this);
  }

  public void setPassword(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_Password_set(swigCPtr, this, value);
  }

  public String getPassword() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_Password_get(swigCPtr, this);
  }

  public void setInstallID(int value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_InstallID_set(swigCPtr, this, value);
  }

  public int getInstallID() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_InstallID_get(swigCPtr, this);
  }

  public void setFutureSerial(int value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_FutureSerial_set(swigCPtr, this, value);
  }

  public int getFutureSerial() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_FutureSerial_get(swigCPtr, this);
  }

  public void setUserID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_UserID_set(swigCPtr, this, value);
  }

  public String getUserID() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_UserID_get(swigCPtr, this);
  }

  public void setVerifyCertNoFlag(char value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_VerifyCertNoFlag_set(swigCPtr, this, value);
  }

  public char getVerifyCertNoFlag() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_VerifyCertNoFlag_get(swigCPtr, this);
  }

  public void setCurrencyID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_CurrencyID_set(swigCPtr, this, value);
  }

  public String getCurrencyID() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_CurrencyID_get(swigCPtr, this);
  }

  public void setTradeAmount(double value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_TradeAmount_set(swigCPtr, this, value);
  }

  public double getTradeAmount() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_TradeAmount_get(swigCPtr, this);
  }

  public void setFutureFetchAmount(double value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_FutureFetchAmount_set(swigCPtr, this, value);
  }

  public double getFutureFetchAmount() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_FutureFetchAmount_get(swigCPtr, this);
  }

  public void setFeePayFlag(char value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_FeePayFlag_set(swigCPtr, this, value);
  }

  public char getFeePayFlag() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_FeePayFlag_get(swigCPtr, this);
  }

  public void setCustFee(double value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_CustFee_set(swigCPtr, this, value);
  }

  public double getCustFee() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_CustFee_get(swigCPtr, this);
  }

  public void setBrokerFee(double value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BrokerFee_set(swigCPtr, this, value);
  }

  public double getBrokerFee() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BrokerFee_get(swigCPtr, this);
  }

  public void setMessage(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_Message_set(swigCPtr, this, value);
  }

  public String getMessage() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_Message_get(swigCPtr, this);
  }

  public void setDigest(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_Digest_set(swigCPtr, this, value);
  }

  public String getDigest() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_Digest_get(swigCPtr, this);
  }

  public void setBankAccType(char value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankAccType_set(swigCPtr, this, value);
  }

  public char getBankAccType() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankAccType_get(swigCPtr, this);
  }

  public void setDeviceID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_DeviceID_set(swigCPtr, this, value);
  }

  public String getDeviceID() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_DeviceID_get(swigCPtr, this);
  }

  public void setBankSecuAccType(char value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankSecuAccType_set(swigCPtr, this, value);
  }

  public char getBankSecuAccType() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankSecuAccType_get(swigCPtr, this);
  }

  public void setBrokerIDByBank(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BrokerIDByBank_set(swigCPtr, this, value);
  }

  public String getBrokerIDByBank() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BrokerIDByBank_get(swigCPtr, this);
  }

  public void setBankSecuAcc(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankSecuAcc_set(swigCPtr, this, value);
  }

  public String getBankSecuAcc() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankSecuAcc_get(swigCPtr, this);
  }

  public void setBankPwdFlag(char value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankPwdFlag_set(swigCPtr, this, value);
  }

  public char getBankPwdFlag() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_BankPwdFlag_get(swigCPtr, this);
  }

  public void setSecuPwdFlag(char value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_SecuPwdFlag_set(swigCPtr, this, value);
  }

  public char getSecuPwdFlag() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_SecuPwdFlag_get(swigCPtr, this);
  }

  public void setOperNo(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_OperNo_set(swigCPtr, this, value);
  }

  public String getOperNo() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_OperNo_get(swigCPtr, this);
  }

  public void setRequestID(int value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_RequestID_set(swigCPtr, this, value);
  }

  public int getRequestID() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_RequestID_get(swigCPtr, this);
  }

  public void setTID(int value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_TID_set(swigCPtr, this, value);
  }

  public int getTID() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_TID_get(swigCPtr, this);
  }

  public void setTransferStatus(char value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_TransferStatus_set(swigCPtr, this, value);
  }

  public char getTransferStatus() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_TransferStatus_get(swigCPtr, this);
  }

  public void setErrorID(int value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_ErrorID_set(swigCPtr, this, value);
  }

  public int getErrorID() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_ErrorID_get(swigCPtr, this);
  }

  public void setErrorMsg(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_ErrorMsg_set(swigCPtr, this, value);
  }

  public String getErrorMsg() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_ErrorMsg_get(swigCPtr, this);
  }

  public void setLongCustomerName(String value) {
    ctpapiv6v3v11JNI.CThostFtdcRspRepealField_LongCustomerName_set(swigCPtr, this, value);
  }

  public String getLongCustomerName() {
    return ctpapiv6v3v11JNI.CThostFtdcRspRepealField_LongCustomerName_get(swigCPtr, this);
  }

  public CThostFtdcRspRepealField() {
    this(ctpapiv6v3v11JNI.new_CThostFtdcRspRepealField(), true);
  }

}