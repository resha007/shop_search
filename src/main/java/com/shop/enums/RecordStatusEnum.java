package com.shop.enums;

public enum RecordStatusEnum {
	
	ACTIVE("ACTIVE", "Active", 1),
	INACTIVE("INACTIVE", "Innactive", 2),
	DEACTIVATE("DEACTIVATED", "Deactivated", 3),
	MODIFIED("MODIFIED", "Modified", 4),
	DELETED("DELETED", "Deleted", 5),
	REJECTED("REJECTED", "Rejected", 6);
	
	private String code;
	private String description;
	private int id;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	private RecordStatusEnum(String code, String description, int id) {
		this.code = code;
		this.description = description;
		this.id = id;
	}
}