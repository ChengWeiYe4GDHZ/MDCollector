/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.yqqtp.laoyouqian.jctp;

public class CThostFtdcSyncStatusField {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected CThostFtdcSyncStatusField(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(CThostFtdcSyncStatusField obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ctpapiv6v3v11JNI.delete_CThostFtdcSyncStatusField(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setTradingDay(String value) {
    ctpapiv6v3v11JNI.CThostFtdcSyncStatusField_TradingDay_set(swigCPtr, this, value);
  }

  public String getTradingDay() {
    return ctpapiv6v3v11JNI.CThostFtdcSyncStatusField_TradingDay_get(swigCPtr, this);
  }

  public void setDataSyncStatus(char value) {
    ctpapiv6v3v11JNI.CThostFtdcSyncStatusField_DataSyncStatus_set(swigCPtr, this, value);
  }

  public char getDataSyncStatus() {
    return ctpapiv6v3v11JNI.CThostFtdcSyncStatusField_DataSyncStatus_get(swigCPtr, this);
  }

  public CThostFtdcSyncStatusField() {
    this(ctpapiv6v3v11JNI.new_CThostFtdcSyncStatusField(), true);
  }

}
