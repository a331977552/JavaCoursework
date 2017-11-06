package uk.co.java.coursework.entity;

/**
 * a common entity for pojo  to be filled into
 * 
 * 
 * @author cody
 *
 */
public class Common<T> {
	
	public Common(String msg, T bean, Integer statusCode) {
		super();
		this.msg = msg;
		this.t = bean;
		this.statusCode = statusCode;
	}
	
	public Common(String msg, Integer statusCode) {
		super();
		this.msg = msg;
		this.statusCode = statusCode;
	}

	public Common(String msg) {
		super();
		this.msg = msg;
	}

	public Common() {
		super();
	}

	public Common(Integer statusCode) {
		super();
		this.statusCode = statusCode;
	}

	/**
	 *  customerized message that reminds front-end programmers or users
	 */
	private String msg;
	/**
	 * 1 represents success,
	 * 0 represents failure
	 */
	private T t;
	public T getBean() {
		return t;
	}
	public void setBean(T t) {
		this.t = t;
	}
	private Integer statusCode;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

}
