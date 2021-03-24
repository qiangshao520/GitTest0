package com.web.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Demo {

	public static void main(String[] args) throws Exception {
		
		//����URL��ַ����
		Document doc = Jsoup.connect("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&wq=%E6%89%8B%E6%9C%BA&page=1&s=116&click=0").get();
		//��ǩ�����Լ���ʽ���Ի�ȡָ��Ԫ�ر�ǩ����
		Elements es = doc.select("div[class=p-name p-name-type-2]");
		//ѭ����������
		for(int i=0;i<es.size();i++){
			//��ȡ������ǩ�ڵ�
			Element e = es.get(i);
			//System.out.println(e);
			//��ȡ�ӱ�ǩ�е�һ��Ԫ�ر�ǩ
			//Element em = e.select("em").first();
			//��ȡ��ǩ�ı�����(��������)
			//String name = em.text();
			Element a = e.select("a").first();
			//��ȡ����
			String href = a.attr("href");
			System.out.println(href);
		}
		
		
	}
	
}
