package dev.drop.utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Revenue {
	public static long total(int roundNum, int rank01, int rank02, int rank03, int rank04, int rank05) {
		long revenue_total = 0;
		String url = "https://www.dhlottery.co.kr/gameResult.do?method=byWin&drwNo="+roundNum;
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		}catch(Exception e) {
			e.printStackTrace();
		}
		Elements test = doc.select("td.tar");
		Elements test01 = test.get(1).select("td");		
		Elements test02 = test.get(3).select("td");		
		Elements test03 = test.get(5).select("td");		
		Elements test04 = test.get(7).select("td");		
		Elements test05 = test.get(9).select("td");		

		String text01 = test01.text();
		String text02 = test02.text();
		String text03 = test03.text();
		String text04 = test04.text();
		String text05 = test05.text();
		
		text01 = text01.substring(0, text01.length()-1);
		text01 = text01.replaceAll("\\,", "");
		text02 = text02.substring(0, text02.length()-1);
		text02 = text02.replaceAll("\\,", "");
		text03 = text03.substring(0, text03.length()-1);
		text03 = text03.replaceAll("\\,", "");
		text04 = text04.substring(0, text04.length()-1);
		text04 = text04.replaceAll("\\,", "");
		text05 = text05.substring(0, text05.length()-1);
		text05 = text05.replaceAll("\\,", "");
		
		long money01 = Long.parseLong(text01);
		int money02 = Integer.parseInt(text02);
		int money03 = Integer.parseInt(text03);
		int money04 = Integer.parseInt(text04);
		int money05 = Integer.parseInt(text05);

		revenue_total = (money01*rank01)+(money02*rank02)+(money03*rank03)+(money04*rank04)+(money05*rank05);

		return revenue_total;
	}
}
