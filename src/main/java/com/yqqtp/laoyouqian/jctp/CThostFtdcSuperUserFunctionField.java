/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.yqqtp.laoyouqian.jctp;

public class CThostFtdcSuperUserFunctionField {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected CThostFtdcSuperUserFunctionField(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(CThostFtdcSuperUserFunctionField obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ctpapiv6v3v11JNI.delete_CThostFtdcSuperUserFunctionField(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setUserID(String value) {
    ctpapiv6v3v11JNI.CThostFtdcSuperUserFunctionField_UserID_set(swigCPtr, this, value);
  }

  public String getUserID() {
    return ctpapiv6v3v11JNI.CThostFtdcSuperUserFunctionField_UserID_get(swigCPtr, this);
  }

  public void setFunctionCode(char value) {
    ctpapiv6v3v11JNI.CThostFtdcSuperUserFunctionField_FunctionCode_set(swigCPtr, this, value);
  }

  public char getFunctionCode() {
    return ctpapiv6v3v11JNI.CThostFtdcSuperUserFunctionField_FunctionCode_get(swigCPtr, this);
  }

  public CThostFtdcSuperUserFunctionField() {
    this(ctpapiv6v3v11JNI.new_CThostFtdcSuperUserFunctionField(), true);
  }

}