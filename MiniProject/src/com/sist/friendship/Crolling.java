package com.sist.friendship;
import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crolling {
/*
 * data-url="https://cdn.bonif.co.kr/cmdt/BF101_thum_BN100347.jpg"
 * https://cdn.bonif.co.kr/cmdt/BF101_pic_10000277.jpg
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			
			Document doc=Jsoup.connect("https://www.bonif.co.kr/menu/list?brdCd=BF102").get();
			Elements link=doc.select("a.goods-detail-view");
			for(int i=0;i<link.size();i++)
			{	
				//System.out.println(link.get(i).text());
				
				String subLink="https://www.bonif.co.kr/menu/"+link.get(i).attr("href");
				System.out.println(subLink);
				
				Document doc2=Jsoup.connect(subLink).get();
				Elements title=doc.select("div.goods-name p.nm");
				Elements money=doc.select("div.goods-name p.price");
				Elements poster=doc.select("div.goods-thumb img");
				Element dd1=doc2.select("div.goods-info-box p.goods-txt").get(0);
				Element dd2=doc2.select("div.goods-summary p").get(0);
				if (dd2 != null) {
				    Elements brElements = dd2.select("br");

				    // 각 <br> 태그를 줄 바꿈 문자로 대체하여 텍스트 추출
				    
				    for (Element brElement : brElements) {
				        brElement.replaceWith(new Element("br").text("\n"));
				    }
				   
				}

				try {
					
					System.out.println(title.get(i).text());
					System.out.println(money.get(i).text());
					System.out.println(poster.get(i).attr("src"));
					System.out.println(dd1.text());
					System.out.println(dd2.text());

					} 
				catch(Exception ex) 
				{
					ex.printStackTrace();
				}
				
				String msg=title.get(i).text()+"|"
				           +money.get(i).text()+"|"
						+subLink+"|"
				        +poster.get(i).attr("src")+"|"
						+dd1.text()+"|"
						+dd2.text()+"\r\n";
				FileWriter fw=new FileWriter("c:\\java_data\\project.txt",true);
				fw.write(msg);
				fw.close();
			}
	
	}

		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
}
	
}