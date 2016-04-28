package webparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ZomatoDataExtractor {
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
		Document doc = null;
		String excelFilePath = "src//ZomatoInfo.xls";
		int i = 1;
		Elements aTag = null;
		do {
			doc = Jsoup
					.connect("https://www.zomato.com/bangalore/cafes?page=" + i)
					.userAgent(
							"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21"
									+ " (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
					.timeout(1000000000).get();
			System.out.println("pageno." + i);
			aTag = doc.getElementsByClass("search-result");
			Elements Cuisines = doc.getElementsByClass("res-cuisine");
			Elements add = doc.getElementsByClass("search-result-address");
			Elements ratings = doc.getElementsByClass("rating-rank");
			Elements reviews = doc.getElementsByClass("result-reviews");
			Elements areas = doc.getElementsByClass("search-page-text");

			for (Element name : aTag) {
				if (!name.getElementsByClass("result-title").text().isEmpty()) {
					model = new ZomotoModel();
					model.setName(name.getElementsByClass("result-title")
							.text());		
				/*	System.out.println(name.getElementsByClass("result-title")
							.text());
*/
				}
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
