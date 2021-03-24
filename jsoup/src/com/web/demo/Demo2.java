package com.web.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Demo2 {

	public static void main(String[] args) throws Exception {
		long time = System.currentTimeMillis();
		//����URL��ַ����
		Document doc = Jsoup.connect("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&wq=%E6%89%8B%E6%9C%BA&page=1&s=116&click=0").get();
		//��ǩ�����Լ���ʽ���Ի�ȡָ��Ԫ�ر�ǩ����
		Elements es = doc.select("div[class=p-img]");
		//ѭ����������
		for(int i=0;i<es.size();i++){
			Element e = es.get(i);
			//��ȡ��Դ�ļ�URL��ַ
			String imgurl = "http:"+e.select("img").first().attr("data-lazy-img");
			//��ȡͼƬ����
			System.out.println(imgurl);
			String imgname = imgurl.substring(imgurl.lastIndexOf("/")+1);
			//����URL����
			URL url = new URL(imgurl);
			//��һ��HTTP����
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			//��������ģʽ
			con.setRequestMethod("GET");
			//�������ӳ�ʱ
			con.setConnectTimeout(5000);
			//�ж��Ƿ񷵻�Ϊ����200���سɹ�
			if(con.getResponseCode()==200){
				//���������ļ���
				File dir = new File("d:/images");
				if(!dir.exists()){
					dir.mkdirs();
				}
				//����Ŀ���ļ�
				File file = new File(dir,imgname);
				if(!file.exists()){
					file.createNewFile();
				}
				//�ֽ�����
				InputStream in = con.getInputStream();
				//�ļ��ֽ����
				OutputStream out = new FileOutputStream(file);
				byte[] b = new byte[1024];
				int len = -1;
				while((len = in.read(b))!=-1){
					out.write(b, 0, len);
				}
				//����
				in.close();
				out.close();
			}
		}
		System.out.println("ok");
		System.out.println(System.currentTimeMillis()-time);
	}
	
}
