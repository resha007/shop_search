package com.shop.enums;

public enum MessageEnum {
	
	CREATE_SUCCESS_MESSAGE("CREATE_SUCCESS_MESSAGE", "Data inserted successfully !", 1),
	CREATE_FAILED_MESSAGE("CREATE_FAILED_MESSAGE", "Data insertion failed. Try again !", 2),
	UPDATE_SUCCESS_MESSAGE("UPDATE_SUCCESS_MESSAGE", "Data updated successfully !", 3),
	UPDATE_FAILED_MESSAGE("UPDATE_FAILED_MESSAGE", "Data updation failed. Try again !", 4),
	DELETE_SUCCESS_MESSAGE("DELETE_SUCCESS_MESSAGE", "Data deleted successfully !", 5),
	DELETE_FAILED_MESSAGE("DELETE_FAILED_MESSAGE", "Data deletion failed. Try again !", 6),
	RECORD_ALREADY_EXISTS("RECORD_ALREADY_EXISTS", "This record is already exists !", 7),
	INCORRECT_FORMAT("INCORRECT_FORMAT", "Incorrect format !", 8);	
	
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
	
	private MessageEnum(String code, String description, int id) {
		this.code = code;
		this.description = description;
		this.id = id;
	}
}