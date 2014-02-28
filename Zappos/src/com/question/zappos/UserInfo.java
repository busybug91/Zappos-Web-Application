package com.question.zappos;
/*A class to represent the elements of a user request submitted through index.jsp
 */
public class UserInfo {

	String email;
	String productID;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public UserInfo(String email, String productID)
	{
		this.email=email;
		this.productID=productID;
	}
	@Override
	public String toString() {
		return "UserInfo [email=" + email + ", productID=" + productID + "]";
	}

}
