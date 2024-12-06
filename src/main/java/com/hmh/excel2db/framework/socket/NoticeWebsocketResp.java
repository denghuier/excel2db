package com.hmh.excel2db.framework.socket;


import org.apache.poi.ss.formula.functions.T;

public class NoticeWebsocketResp {

    private String noticeType;

    private T noticeInfo;

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public T getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(T noticeInfo) {
        this.noticeInfo = noticeInfo;
    }
}
