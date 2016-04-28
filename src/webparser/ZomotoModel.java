package webparser;

public class ZomotoModel {
	String name;
	String phone;
	String Cuisines ;	
	 String address ;       
	 String ratings ; 
	 String reviews ;
	 String area;
	public String getCuisines() {
		return Cuisines;
	}
	public void setCuisines(String cuisines) {
		Cuisines = cuisines;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRatings() {
		return ratings;
	}
	public void setRatings(String ratings) {
		this.ratings = ratings;
	}
	public String getReviews() {
		return reviews;
	}
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	} 
}
