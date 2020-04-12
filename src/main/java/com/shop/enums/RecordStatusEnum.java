package com.shop.enums;

public enum RecordStatusEnum {
	
	ACTIVE("ACTIVE", "Active", "1"),
	INACTIVE("INACTIVE", "Innactive", "2"),
	DEACTIVATE("DEACTIVATED", "Deactivated", "3"),
	MODIFIED("MODIFIED", "Modified", "4"),
	DELETED("DELETED", "Deleted", "5"),
	REJECTED("REJECTED", "Rejected", "6"),
        VERIFIED("VERIFIED", "Verified", "7"),
        UNVERIFIED("UNVERIFIED", "Unverified", "8");
	
	private String code;
	private String description;
	private String id;
	
	
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	private RecordStatusEnum(String code, String description, String id) {
		this.code = code;
		this.description = description;
		this.id = id;
	}
}