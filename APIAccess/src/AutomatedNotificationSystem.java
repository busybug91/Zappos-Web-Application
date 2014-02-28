import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import org.json.simple.parser.ParseException;


public class AutomatedNotificationSystem {


	public void performBackendOperation() throws IOException, ParseException, SQLException, InterruptedException
	{

		DAOProductIDs dao=new DAOProductIDs();
		HashSet<String>	allProductIds=dao.retreiveAllProductIDs();
		System.out.println("********ALL product IDs from the database******");
		System.out.println(allProductIds);
		ArrayList<String> allProductIdsList= new ArrayList<String>(allProductIds);
		ZapposApi zAPI= new ZapposApi();
		if(allProductIds.size()>0)
		{
			int count=0;
			int total=0;
			while(total<allProductIdsList.size())
			{
				StringBuilder ids=new StringBuilder();
				count=0;
				ids.append("[");
				while(count<10&&total<allProductIdsList.size())
				{
					ids.append("\"");
					ids.append(allProductIdsList.get(total));
					count++;
					total++;
					ids.append("\"");
					ids.append(",");
				}
				int n=ids.lastIndexOf(",");
				ids.deleteCharAt(n);
				ids.append("]");
				String jsonResponseString=zAPI.getProductInfo(ids.toString());
				JsonParserOwn parser= new JsonParserOwn();
				parser.parse(jsonResponseString);
			}
		}

	}

}
