package xyz.springabc.web.form;

/**
 * 其他表单也要用校验
 * @author zonghua
 *
 */
public class EmailForm {
	
	private String to;
	
	private String from;
	
	private String title;
	
	private String content;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
