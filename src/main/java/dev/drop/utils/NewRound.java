package dev.drop.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NewRound {
	public static int newRound() {
		
		// 회차 자동가져오기
		String url = "https://www.dhlottery.co.kr/gameResult.do?method=byWin&wiselog=C_A_1_1";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Elements test = doc.select(".win_result h4");
		Elements testRound = test.get(0).select("strong");
		String lastRound = testRound.text();
		lastRound = lastRound.substring(0, lastRound.length()-1);
		int round = Integer.parseInt(lastRound)+1;
		
		return round;
	}
}
