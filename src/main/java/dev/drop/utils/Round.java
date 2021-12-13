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
	public static int lastRound() {
		String last_url = "https://dhlottery.co.kr/gameResult.do?method=byWin";
		Document last_doc = null;
		try {
			last_doc = Jsoup.connect(last_url).get();
		}catch(Exception e) {
			e.printStackTrace();
		}
		Elements last1 = last_doc.select("div.win_result");
		Elements last_1 = last1.get(0).select("h4");
		String last_result = last_1.text();
		int last_num = Integer.parseInt(last_result.substring(0, 3));

		return last_num;
	}
}
