package com.stacksimplify.restservices.dtos;

public class UserMsDto {

	private Long userid;
	private String username;
	private String emailaddress;
	
	
	
	public UserMsDto() {
		
	}

	public UserMsDto(Long userid, String username, String email) {
		super();
		this.userid = userid;
		this.username = username;
		this.emailaddress = email;
	}
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return emailaddress;
	}
	public void setEmail(String email) {
		this.emailaddress = email;
	}
}
