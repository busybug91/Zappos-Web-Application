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


public class JsonParserOwn {
	Notification notifyInstance=null;

	ArrayList<Product> relevantProducts=null;


	//	String s="{\"product\":[{\"styles\":[],\"defaultProductUrl\":\"http:\/\/www.zappos.com\/product\/7564933\",\"defaultImageUrl\":null,\"productId\":\"7564933\",\"productName\":\"Aideen (Youth)\",\"brandId\":\"138\",\"brandName\":\"Sam & Libby Girls\"},{\"styles\":[{\"price\":\"$50.00\",\"productUrl\":\"http:\/\/www.zappos.com\/product\/8148211\/color\/144\",\"percentOff\":\"0%\",\"styleId\":\"2506127\",\"imageUrl\":\"http:\/\/www.zappos.com\/images\/z\/2\/5\/0\/6\/1\/2\/2506127-p-DETAILED.jpg\",\"color\":\"Black\/Red\",\"originalPrice\":\"$50.00\"}],\"defaultProductUrl\":\"http:\/\/www.zappos.com\/product\/8148211\",\"defaultImageUrl\":\"http:\/\/www.zappos.com\/images\/z\/2\/5\/0\/6\/1\/2\/2506127-p-DETAILED.jpg\",\"productId\":\"8148211\",\"productName\":\"Senix D Mid\",\"brandId\":\"444\",\"brandName\":\"etnies\"}],\"statusCode\":\"200\"}";
	//	String s="{\"statusCode\": \"200\",\"product\": [{\"productId\": \"7151832\",\"brandId\": \"258\",\"styles\": [{\"styleId\": \"114116\",\"color\": \"Dark Blue\/Grey\",\"originalPrice\":\"$84.00\",\"price\": \"$84.00\"},{\"styleId\": \"114115\",\"color\": \"Grey\/Black\"}]}]}";

	public JsonParserOwn()
	{
		relevantProducts=new ArrayList<Product>();
		notifyInstance=new Notification();
	}
	public void parse(String s) throws IOException, ParseException, SQLException, InterruptedException
	{
		//		String s=new ZapposApi().getProductInfo("7564933");
		//		s= s.replaceAll("\"", "\"");
		//		System.out.println(s);
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
			//		System.out.println(productArray);
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
				//				System.out.println(stylesArray);
				int numberOFStyles=stylesArray.size();
				for(int j=0;j<numberOFStyles;j++)
				{
					JSONObject particularStyle=(JSONObject)stylesArray.get(j);
					String styleID=particularStyle.get("styleId").toString();
					if(particularStyle.containsKey("percentOff"))
					{
						String percentOff=particularStyle.get("percentOff").toString().replace("%", "");
						int discountPercent=Integer.parseInt(percentOff);
						//						System.out.println("Discount Percentage for productID: "+productID+" is "+discountPercent);
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
