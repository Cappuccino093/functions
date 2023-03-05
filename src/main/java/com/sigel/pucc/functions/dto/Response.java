package com.sigel.pucc.functions.dto;

public class Response {

	private String status;
	private short code;
	private String reason;
	private String detail;
	private String reply;

	public String getStatus() {
		return status;
	}

	public short getCode() {
		return code;
	}

	public String getReason() {
		return reason;
	}

	public String getDetail() {
		return detail;
	}

	public String getReply() {
		return reply;
	}

	public Response(String status, short code, String reason, String detail, String reply) {
		this.status = status;
		this.code = code;
		this.reason = reason;
		this.detail = detail;
		this.reply = reply;
	}

	public Response setStatus(String status) {
		this.status = status;
		return this;
	}

	public Response setCode(short code) {
		this.code = code;
		return this;
	}

	public Response setReason(String reason) {
		this.reason = reason;
		return this;
	}

	public Response setDetail(String detail) {
		this.detail = detail;
		return this;
	}

	public Response setReply(String reply) {
		this.reply = reply;
		return this;
	}
}
