package webparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ZomatoDataNew {
	public static void main(String[] args) throws IOException {
		ZomotoModel model = null;
		List<ZomotoModel> restList = new ArrayList<ZomotoModel>();
		ZomotoModel model2 = new ZomotoModel();
		model2.setName("Name of Restaurants");
		model2.setCuisines("Cuisines");
		model2.setAddress("Full Address");
		model2.setRatings("Restaurants Rating");
		model2.setReviews("Review");
		model2.setArea("Area");
		restList.add(model2);
		ZomotoModel model1 = new ZomotoModel();
		model1.setName("");
		model1.setCuisines("");
		model1.setAddress("");
		model1.setRatings("");
		model1.setReviews("");
		model1.setArea("");
		restList.add(model1);
		Document doc1 = null;
		List urlList =null;
		String excelFilePath = "src//zomato//ZomatoContactInfo.xls";
		int i = 1;
		do {
		
			System.out.println("pageno." + i);
		 urlList = new ArrayList();
		doc1 = Jsoup
		.connect("https://www.zomato.com/bangalore/cafes?page=" +i)
		.userAgent(
				"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21"
						+ " (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
		.timeout(999999999).get();
			
		Elements titles = doc1.getElementsByClass("result-title");
		Elements Cuisines = doc1.getElementsByClass("res-cuisine");
		Elements add = doc1.getElementsByClass("search-result-address");
		Elements ratings = doc1.getElementsByClass("rating-rank");
		Elements reviews = doc1.getElementsByClass("result-reviews");
		Elements areas = doc1.getElementsByClass("search-page-text");
		
		
		System.out.println("urls "+titles.size());
		for (Element title : titles) {
		//	model.setCuisines(title.getElementsByAttribute("href").text());		
			//System.out.println("title "+title.attr("href"));	
			urlList.add(title.attr("href"));			
		}				
		System.out.println("url finish" +urlList.size());
		for(int k=0;k<urlList.size();k++){
			String url=(String) urlList.get(k);
			url= url.replace('é', 'e');
			url= url.replace('-', '-');		
			url= url.replace('û', 'u');	
		
			Document doc = Jsoup			
		.connect(url)
		.userAgent(
				"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21"
						+ " (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
		.timeout(999999999).get();		
			model = new ZomotoModel();
			String name = doc.getElementsByClass("res-name").text();			
			model.setName(name);
			//System.out.println("aTag "+name);
			String tel = doc.getElementsByClass("tel").text();
			model.setPhone(tel);
		//	System.out.println("tel "+tel);
		
		for (Element Cuisines1 : Cuisines) {
			model.setCuisines(Cuisines1.getElementsByClass("search-grid-right-text").text());					
			Cuisines.remove(Cuisines1);
			break;

		}
		for (Element address : add) {
			model.setAddress(address.getElementsByClass(
					"search-result-address").text());				
			add.remove(address);
			break;
		}
		for (Element rating : ratings) {
			model.setRatings(rating.getElementsByTag("span ")
					.text());				
			ratings.remove(rating);
			break;
		}
		for (Element review : reviews) {
			model.setReviews(review
					.getElementsByClass("result-reviews").text());					
			reviews.remove(review);
			break;
		}
		for (Element area : areas) {
			model.setArea(area
					.getElementsByClass("search-page-text").text());					
			reviews.remove(area);
			break;
		}
			restList.add(model);				
		}
			// System.out.println("*************************************************");
			i++;
			
		} while (i<=14);
		ZomatoExcelWriter.writeExcel(restList, excelFilePath);
	}
}
