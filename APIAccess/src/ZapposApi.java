

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class ZapposApi {
	String baseUrl="http://api.zappos.com/Product/?id=";

	public String getProductInfo(String productID) throws IOException
	{
//		String url=baseUrl+"[\""+productID+"\",\"8148211"+"\"]"+"&key="+key+ "&includes=[\"styles\"]";
		String url=baseUrl+productID+"&key="+Constants.getKey()+ "&includes=[\"styles\"]";
		System.out.println(url);
		URL obj= new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode=con.getResponseCode();	
		System.out.println("Response code for GET connection is"+responseCode);
		System.out.println("Sending request");
		InputStream iStream=con.getInputStream();
		InputStreamReader isReader= new InputStreamReader(iStream);
		BufferedReader bfReader= new BufferedReader(isReader);
		String line=bfReader.readLine();
		StringBuilder response=  new StringBuilder();
		while(line!=null)
		{
			response.append(line);
			line= bfReader.readLine();
		}
		iStream.close();
		return response.toString();
	}
	public String test() throws IOException
	{
		String url=baseUrl+"[\"7943343\"]"+"&key="+Constants.getKey()+ "&includes=[\"styles\"]";
		System.out.println(url);
		URL obj= new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode=con.getResponseCode();	
		System.out.println("Response code for GET connection is"+responseCode);
		System.out.println("Sending request");
		InputStream iStream=con.getInputStream();
		InputStreamReader isReader= new InputStreamReader(iStream);
		BufferedReader bfReader= new BufferedReader(isReader);
		String line=bfReader.readLine();
		StringBuilder response=  new StringBuilder();
		while(line!=null)
		{
			response.append(line);
			line= bfReader.readLine();
		}
		iStream.close();
		return response.toString();
		
		
	}

}
