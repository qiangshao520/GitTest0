package com.web.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Demo {

	public static void main(String[] args) throws Exception {
		
		//解析URL地址返回
		Document doc = Jsoup.connect("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&wq=%E6%89%8B%E6%9C%BA&page=1&s=116&click=0").get();
		//标签名称以及样式属性获取指定元素标签集合
		Elements es = doc.select("div[class=p-name p-name-type-2]");
		//循环遍历集合
		for(int i=0;i<es.size();i++){
			//获取单个标签节点
			Element e = es.get(i);
			//System.out.println(e);
			//获取子标签中第一个元素标签
			//Element em = e.select("em").first();
			//获取标签文本内容(主体内容)
			//String name = em.text();
			Element a = e.select("a").first();
			//获取属性
			String href = a.attr("href");
			System.out.println(href);
		}
		
		
	}
	
}
