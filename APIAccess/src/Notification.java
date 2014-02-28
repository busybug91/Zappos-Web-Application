import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

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
		System.out.println(session.toString());
		try {
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

