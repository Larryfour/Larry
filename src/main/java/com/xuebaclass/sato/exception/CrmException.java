package com.xuebaclass.sato.exception;

/**
 * Created by sunhao on 2017-08-24.
 */
public class CrmException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private int code = 0;
	private String title = "Sales Crm Exception";

	public CrmException(String message) {
		super(message);
	}

	public CrmException(String message, int code) {
		super(message);
		this.code = code;
	}

	public CrmException(String message, int code, String title) {
		super(message);
		this.code = code;
		this.title = title;
	}

	public static CrmException newException(String message) {
		return new CrmException(message);
	}

	public static CrmException newException(String message, int code) {
		return new CrmException(message, code);
	}

	public static CrmException newException(String message, int code, String title) {
		return new CrmException(message, code, title);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
