package com.gn.model.vo;

import java.time.LocalDateTime;

public class User {
	private int user_no;
	private String user_id;
	private String user_pwd;
	private String user_nick;
	private LocalDateTime user_reg_date;
	private LocalDateTime user_update_date;
	
	public User() {
		super();
	}

	public User(int user_no, String user_id, String user_pwd, String user_nick, LocalDateTime user_reg_date,
			LocalDateTime user_update_date) {
		super();
		this.user_no = user_no;
		this.user_id = user_id;
		this.user_pwd = user_pwd;
		this.user_nick = user_nick;
		this.user_reg_date = user_reg_date;
		this.user_update_date = user_update_date;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getUser_nick() {
		return user_nick;
	}

	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}

	public LocalDateTime getUser_reg_date() {
		return user_reg_date;
	}

	public void setUser_reg_date(LocalDateTime user_reg_date) {
		this.user_reg_date = user_reg_date;
	}

	public LocalDateTime getUser_update_date() {
		return user_update_date;
	}

	public void setUser_update_date(LocalDateTime user_update_date) {
		this.user_update_date = user_update_date;
	}

	@Override
	public String toString() {
		return user_no + "||" + user_id + "||" + user_pwd + "||"
				+ user_nick + "||" + user_reg_date + "||" + user_update_date;
	}
	
	
}
