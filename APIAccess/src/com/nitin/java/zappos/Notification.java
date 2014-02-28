package com.nitin.java.zappos;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
/*This class contains methods to notify users
 * JavaMail Api and Google SMTP have been used to send emails.
 * Google account settings can be set up in Constants.java
 * */
public class Notification {

	private Session session=null;
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Notification()
	{

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		try{
			session = Session.getInstance(props,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(Constants.getFromEmailString(), Constants.getFromEmailPassword());
				}
			});
		}
		catch(Exception e)
		{
			System.out.println("Please check user name and password");
			e.printStackTrace();
		}	
	}
	public void sendNotificaiton(Product product, HashSet<String> emails) throws SQLException, InterruptedException
	{	

		/*
	This function receives the final list of users that need to to be notified about the 
	product
		 */

		if(session==null)	
		{
			Properties props = new Properties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			try{
				session = Session.getInstance(props,
						new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(Constants.getFromEmailString(), Constants.getFromEmailPassword());
					}
				});
			}
			catch(Exception e)
			{
				System.out.println("Please check user name and password");
				e.printStackTrace();
			}
		}
		String notification= generateMessage(product);
		try {

			/*once user is notified we set in database that he/she has been notified
			 * to prevent spamming again with same information in our next iteration.
			 * 
			 * Future Add on: if status remains same for a week and product is still available on discount
			 * then user can be notified again about the same offer.
			 * */
			DatabaseAccess dao= new DatabaseAccess();
			Connection conn= dao.getConn();
			PreparedStatement ps= conn.prepareStatement("UPDATE zappos.notify_data SET notify_status=? WHERE (emailID=?)");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Constants.getFromEmailString()));
			message.setSubject("Zappos Offers");
			message.setText(notification);
			System.out.println(notification.substring(notification.indexOf("Product ID"),notification.indexOf("Product ID")+20));
			for(String toUser:emails)
			{

				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(toUser));
				Transport.send(message);
				ps.setInt(1, 1);
				ps.setString(2, toUser);
				ps.execute();
				System.out.println("Email Sent to user "+toUser);
				/*A small fix to prevent google from blocking email ID after certain
				 * number of emails.
				 * 
				 * Future add on: User business accounts for bulk emailing.
				 * */
				Thread.sleep(Constants.getSleepTimeEmail());
			}
			ps.close();
			conn.close();
			dao.closeConneciton();
		} 
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	public String generateMessage(Product product)
	{

		/*Based on the product and selected list of users this function builds an
		 * email message to be sent as notification.
		 * 
		 * Future addon: link to un-subscribe can be added to this message.
		 * */
		String notification= "Dear customer,\nYour requested product with following details: -\n"
				+ "Product Name: "+product.getProductName()+"\nProduct ID: "+product.getProductID()+
				"\nBrand Name: "+product.getBrandName()+
				"\nStyle ID: "+product.getStyleID()+""
				+ "\nis now available with a discount of "+
				product.getPercentOff()+"%. Click on this link to visit product page: "+product.getProductURL()
				+"\nOffer valid till stock lasts. \nCheers,\nTeam Zappos";	
		return notification;
	}
}

