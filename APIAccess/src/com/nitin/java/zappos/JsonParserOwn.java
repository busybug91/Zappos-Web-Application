package com.nitin.java.zappos;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import javax.xml.ws.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
/*This class uses json_simple.jar to parse the received information from server.
 * Parsing could be done with the help of gson parser also.
 */

public class JsonParserOwn {
	Notification notifyInstance=null;
	ArrayList<Product> relevantProducts=null;
	public JsonParserOwn()
	{
		relevantProducts=new ArrayList<Product>();
		notifyInstance=new Notification();
	}
	public void parse(String s) throws IOException, ParseException, SQLException, InterruptedException
	{
		relevantProducts.clear();
		s=s.replaceAll("\\s+", "");
		s=s.replaceAll("\\r\\n", "");
		System.out.println("******Parsing String********\n"+s);
		JSONParser parser =new JSONParser();
		Object obj=parser.parse(s);
		JSONObject jObj= (JSONObject) obj;
		int statusCode=Integer.parseInt((jObj.get("statusCode")).toString());
		//statusCode check start
		if(statusCode==200)
		{
			Object obj2=jObj.get("product");
			JSONArray productArray=(JSONArray)obj2;
			int numOfProductsRetreived=productArray.size();
			System.out.println("Number of products retreived "+numOfProductsRetreived);
			for(int i=0;i<numOfProductsRetreived;i++)
			{
				Object tempObject=productArray.get(i);
				JSONObject productMap=(JSONObject)tempObject;
				String productID=productMap.get("productId").toString();
				String brandName=productMap.get("brandName").toString();
				String defaultProductUrl=productMap.get("defaultProductUrl").toString();
				String productName=productMap.get("productName").toString();
				Object obj3= productMap.get("styles");
				JSONArray stylesArray=(JSONArray) obj3;
				int numberOFStyles=stylesArray.size();
				for(int j=0;j<numberOFStyles;j++)
				{
					JSONObject particularStyle=(JSONObject)stylesArray.get(j);
					String styleID=particularStyle.get("styleId").toString();
					if(particularStyle.containsKey("percentOff"))
					{
						String percentOff=particularStyle.get("percentOff").toString().replace("%", "");
						int discountPercent=Integer.parseInt(percentOff);
						if(discountPercent>=20)
						{
							relevantProducts.add(new Product(productID,styleID,discountPercent, defaultProductUrl, productName, brandName));
						}
					}
				}
			}
		}	
		//status code check ends here
		System.out.println("Total relevant products in this iteration"+relevantProducts.size());
		System.out.println(relevantProducts);
		{
			/*Once the products with discount percentage greater than 20 are identified
			 * then the following code checks if discount is different from previous iteration
			 * or not. If not then we notify only the new users else notify all users
			 * about the new available discount
			 * */
			if(relevantProducts.size()>0)
			{
				for(Product product:relevantProducts)
				{
					DAOProductIDs dbAccess= new DAOProductIDs();
					HashSet<String> emailID=dbAccess.retreiveRelevantEmailIDs(product);
					if(emailID!=null&&emailID.size()>0)
						notifyInstance.sendNotificaiton(product, emailID);
				}

			}
		}
	}
}
