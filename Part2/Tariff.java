package Part2;

public class Tariff {
	private String destinationCountry;
	private String originCountry;
	private String productCategory;
	private double minimumTariff;
	
//	Parameterized Constructor
	public Tariff(String destinationCountry,String originCountry,String productCategory,double minimumTariff) {
		this.destinationCountry = destinationCountry;
		this.originCountry = originCountry;
		this.productCategory = productCategory;
		this.minimumTariff = minimumTariff;
	}
	
//	Copy Constructor
	public Tariff(Tariff other) {
		this(other.destinationCountry,other.originCountry,other.productCategory,other.minimumTariff);
	}
	
//	Getters
	public String getDestinationCountry() {
		return destinationCountry;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public double getMinimumTariff() {
		return minimumTariff;
	}

//	Setters
	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public void setMinimumTariff(double minimumTariff) {
		this.minimumTariff = minimumTariff;
	}

//	Clone method using copy constructor as we don't need a deep copy because we have only Strong and double attributes
	public Tariff clone() {
		return new Tariff(this);
	}
	
//	toString method
	public String toString() {
		return this.destinationCountry
				+ ","  + this.originCountry
				+ ","  + this.productCategory
				+ "," + this.minimumTariff;
	}
	
//	equals Method
	public boolean equals(Object obj) {
		if(obj == null) 
			return false;
		
		if(this.getClass() != obj.getClass())
			return false;
		
		Tariff T = (Tariff) obj;
		
		return this.destinationCountry.equals(T.destinationCountry) && this.originCountry.equals(T.originCountry)
				&& this.productCategory.equals(T.productCategory) 
				&&(Math.abs(this.minimumTariff-T.minimumTariff)<0.00001);
	}

}
