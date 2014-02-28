package com.nitin.java.zappos;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

/*This class holds main function for the application.
 * For simplicity it is kept in infinite loop.
 * In future quartz scheduler can be used for better implementation.
 * */
public class Main {

	public static void main(String[] args) throws SQLException, IOException, ParseException, InterruptedException {
		boolean run=true;
		int count=0;
		AutomatedNotificationSystem ans= new AutomatedNotificationSystem();
		while(run)
		{
			ans.performBackendOperation();
			System.out.println("Number of iterations completed: "+(++count));
			Thread.sleep(Constants.getSleepTimeBackendOperation());
		}
	}
}
