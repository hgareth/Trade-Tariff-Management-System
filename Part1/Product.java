package Part1;

public class Product implements Comparable<Product>{
	private String productName;
	private String country;
	private String category;
	private double initialPrice;
	
//	Default Constructor
	public Product() {
		this.productName = "N/A";
		this.country = "N/A";
		this.category = "N/A";
		this.initialPrice = 0.0;
	}
	
//	Parameterized Constructor
	public Product(String productName,String country,String rawMaterial,double initialPrice) {
		this.productName = productName;
		this.country = country;
		this.category = rawMaterial;
		this.initialPrice = initialPrice;
	}
	
//	Copy Constructor
	public Product(Product other) {
		this(other.productName,other.country,other.category,other.initialPrice);
	}
	
//	Getters
	public String getProductName() {
		return productName;
	}

	public String getCountry() {
		return country;
	}

	public String getCategory() {
		return category;
	}

	public double getInitialPrice() {
		return initialPrice;
	}

//	Setters
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCategory(String rawMaterial) {
		this.category = rawMaterial;
	}

	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}
	
//	equals Method
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		
		if(this.getClass() != obj.getClass())
			return false;
		
		Product p = (Product) obj;
		
		return this.productName.equals(p.productName) && this.country.equals(p.country)
		&& this.category.equals(p.category) && (Math.abs(this.initialPrice - p.initialPrice)<0.00001);
	}
	
//	toString Method
	public String toString() {
		return "Product Name: " + this.productName 
				+ "\n" + "Country: " + this.country
				+ "\n" + "Raw Material: " + this.category
				+ "\n" + "Initital Price " + this.initialPrice;
	}
	
//	Method to apply tariffs according to country and category
	public void applyTariff() {
		
		double tariffPercentage = 0;
		
		switch(this.country) {
		case "China":
				tariffPercentage = 25;
				break;
		case "USA":
			if(this.category.equalsIgnoreCase("Electronics")) {
				tariffPercentage = 10;
			}
			break;
		case "Japan":
			if(this.category.equalsIgnoreCase("Automobile")) {
				tariffPercentage = 15;
			}
			break;
		case "India":
			if(this.category.equalsIgnoreCase("Agriculture")) {
				tariffPercentage = 5;
			}
			break;
		case "South Korea":
			if(this.category.equalsIgnoreCase("Electronics")) {
				tariffPercentage = 8;
			}
			break;
		case "Saudi Arabia":
			if(this.category.equalsIgnoreCase("Energy")) {
				tariffPercentage = 12;
			}
			break;
		case "Germany":
			if(this.category.equalsIgnoreCase("Manufacturing")) {
				tariffPercentage = 6;
			}
			break;
		case "Bangladesh":
			if(this.category.equalsIgnoreCase("Textile")) {
				tariffPercentage = 4;
			}
			break;
		case "Brazil":
			if(this.category.equalsIgnoreCase("Atomobiles")) {
				tariffPercentage = 9;
			}
			break;
		}
		
		if(tariffPercentage > 0) {
			this.initialPrice = this.initialPrice + ((tariffPercentage/100)*this.initialPrice);
			}
		}
	
//	@Override
	public int compareTo(Product other) {
		return this.productName.compareToIgnoreCase(other.productName);
	}
	
}
