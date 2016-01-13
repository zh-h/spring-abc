package xyz.springabc.web.helper;

import org.springframework.data.domain.Page;

public class Pagination {
	
	private static String prameter="";//分页参数
	private static  String url="";//当前页url
	private static int showNumber;//显示的分页元素
	private static String ulStart="<ul class=\"pagination \" style=\"margin: 0px\">";
	private static String ulEnd="</ul>";//ul结束
	
	public static <T> String create(Page<T> page,String url){
		int number=page.getNumber();//当前页
		int size=page.getSize();//一页显示数目
		int totalPages=page.getTotalPages();//总页数
		long total=page.getTotalElements();//总数
		
	
		StringBuffer p=new StringBuffer("<ul class=\"pagination \" style=\"margin: 0px\">");
		for(int i=0;i<total;i++){
			p.append("<li><a href=\"");
			p.append(url);
			p.append("p=");
			p.append(i);
			p.append("\">");
			p.append("</a><li>");
		}
		p.append("</ul>");
		return (p.toString());
	}
}
