package com.example.test;

public class TestBean {

	private String historyType;
	private String id;
	private String attribute;
	private String attribute1;
	private String attribute2;
	
	
	public String getHistoryType() {
		return historyType;
	}
	public void setHistoryType(String historyType) {
		this.historyType = historyType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	@Override
	public String toString() {
		return "TestBean [historyType=" + historyType + ", id=" + id + ", attribute=" + attribute + ", attribute1="
				+ attribute1 + ", attribute2=" + attribute2 + "]";
	}
	
}
