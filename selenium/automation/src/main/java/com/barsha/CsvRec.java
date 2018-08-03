package com.barsha;

public class CsvRec {
	private String pageName;
	private String cssSelector;
	private String value;
	private ActionType type;
	private String regex;
	
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getCssSelector() {
		return cssSelector;
	}
	public void setCssSelector(String cssSelector) {
		this.cssSelector = cssSelector;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ActionType getType() {
		return type;
	}
	public void setType(ActionType type) {
		this.type = type;
	}
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}
	@Override
	public String toString() {
		return "CsvRec [pageName=" + pageName + ", cssSelector=" + cssSelector + ", value=" + value + ", type=" + type
				+ ", regex=" + regex + "]";
	}
}
