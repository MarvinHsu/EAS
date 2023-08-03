package com.hsuforum.eas;

import java.io.Serializable;

public class DefaultSetting implements Serializable{ 

    private static final long serialVersionUID = 8322268167179906456L;

    private int dataTableRows;
    private String mailFrom;
	private Boolean devMode=true;
	private Integer rowsOfPerPage;
	private String timeZone;
	private String secret;
    
	public int getDataTableRows() {
		return dataTableRows;
	}
	public void setDataTableRows(int dataTableRows) {
		this.dataTableRows = dataTableRows;
	}
	public String getMailFrom() {
		return mailFrom;
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public Boolean getDevMode() {
		return devMode;
	}
	public void setDevMode(Boolean devMode) {
		this.devMode = devMode;
	}
	public Integer getRowsOfPerPage() {
		return rowsOfPerPage;
	}
	public void setRowsOfPerPage(Integer rowsOfPerPage) {
		this.rowsOfPerPage = rowsOfPerPage;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	} 



}