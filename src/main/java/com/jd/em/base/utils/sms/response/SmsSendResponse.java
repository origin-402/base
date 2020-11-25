package com.jd.em.base.utils.sms.response;

public class SmsSendResponse
{
  private String time;
  private String msgId;
  private String errorMsg;
  private String code;
  
  public String getTime()
  {
    return this.time;
  }
  
  public void setTime(String time)
  {
    this.time = time;
  }
  
  public String getMsgId()
  {
    return this.msgId;
  }
  
  public void setMsgId(String msgId)
  {
    this.msgId = msgId;
  }
  
  public String getErrorMsg()
  {
    return this.errorMsg;
  }
  
  public void setErrorMsg(String errorMsg)
  {
    this.errorMsg = errorMsg;
  }
  
  public String getCode()
  {
    return this.code;
  }
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  public String toString()
  {
    return 
      "SmsSingleResponse [time=" + this.time + ", msgId=" + this.msgId + ", errorMsg=" + this.errorMsg + ", code=" + this.code + "]";
  }
}
