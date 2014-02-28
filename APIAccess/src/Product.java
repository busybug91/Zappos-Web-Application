
public class Product {

	private String productID=Constants.getDefaultProductInfo();
	private String styleID=Constants.getDefaultProductInfo();
	private int percentOff=-1;
	private String productURL=Constants.getDefaultProductInfo();
	private String productName=Constants.getDefaultProductInfo();
	private String brandName=Constants.getDefaultProductInfo();


	public Product(String productID, String styleID, int percentOff,
			String productURL, String productName, String brandName) {
		super();
		this.productID = productID;
		this.styleID = styleID;
		this.percentOff = percentOff;
		this.productURL = productURL;
		this.productName = productName;
		this.brandName = brandName;
	}
	public String getProductURL() {
		return productURL;
	}
	public void setProductURL(String productURL) {
		this.productURL = productURL;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Product(String productID, String styleID, int percentOff) {
		this.productID = productID;
		this.styleID = styleID;
		this.percentOff = percentOff;
	}
	public Product()
	{

	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getStyleID() {
		return styleID;
	}
	public void setStyleID(String styleID) {
		this.styleID = styleID;
	}
	@Override
	public String toString() {
		return "Product [productID=" + productID + ", styleID=" + styleID
				+ ", percentOff=" + percentOff + "]";
	}
	public int getPercentOff() {
		return percentOff;
	}
	public void setPercentOff(int percentOff) {
		this.percentOff = percentOff;
	}


}
