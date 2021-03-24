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
		//解析URL地址返回
		Document doc = Jsoup.connect("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&wq=%E6%89%8B%E6%9C%BA&page=1&s=116&click=0").get();
		//标签名称以及样式属性获取指定元素标签集合
		Elements es = doc.select("div[class=p-img]");
		//循环遍历集合
		for(int i=0;i<es.size();i++){
			Element e = es.get(i);
			//获取资源文件URL地址
			String imgurl = "http:"+e.select("img").first().attr("data-lazy-img");
			//获取图片名称
			System.out.println(imgurl);
			String imgname = imgurl.substring(imgurl.lastIndexOf("/")+1);
			//创建URL对象
			URL url = new URL(imgurl);
			//打开一个HTTP链接
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			//设置请求模式
			con.setRequestMethod("GET");
			//设置链接超时
			con.setConnectTimeout(5000);
			//判断是否返回为正常200返回成功
			if(con.getResponseCode()==200){
				//创建保存文件夹
				File dir = new File("d:/images");
				if(!dir.exists()){
					dir.mkdirs();
				}
				//创建目标文件
				File file = new File(dir,imgname);
				if(!file.exists()){
					file.createNewFile();
				}
				//字节输入
				InputStream in = con.getInputStream();
				//文件字节输出
				OutputStream out = new FileOutputStream(file);
				byte[] b = new byte[1024];
				int len = -1;
				while((len = in.read(b))!=-1){
					out.write(b, 0, len);
				}
				//关流
				in.close();
				out.close();
			}
		}
		System.out.println("ok");
		System.out.println(System.currentTimeMillis()-time);
	}
	
}
