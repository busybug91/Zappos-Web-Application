Zappos-Web-Application
======================

Zappos summer 2014 java development intern code.
================================================

Assumptions
-----------

- Client side email validation has been done. No code for server side validation.
- User will enter the correct SKU / product id as it is available on every product page.
- Notification is provided if discount is greater than 20%. If this value is same as previous value then we don't send
another email to user as it might be considered spam. However, if discount percentage is different from previous noted value
then we notify all users.
- Two separate applications have been created that essentially use the same database.
- One application (Zappos) is for front end using JSP and Servlet to register user for future product notifications.
- Second application (APIAccess) is a complete backend application that uses same database and decides whether to send notification or not.


Instructions to run the application
-----------------------------------

- set key, fromEmail and password in Constants.java
- Install MySql. Create schema 'zappos' and import the given dumps. 
- Add JavaMail api jar, json_simple jar to build path.
- Run Zappos for front end user registration for notification in Apache Tomcat.
- Run main method from Main.java in APIAccess for backend notification process.

Development Environment
-----------------------

- Kepler Eclipse IDE 
- Java 6
- Mac OS x 10.9 mavericks
- Apache Tomcat 7

Testing
-------

- Product IDs from clearance items on zappos website were used to perform testing.
- System worked well. Only known issue found was email limit achieved. Had to login into email account online and
fill in the Captcha to enable emailing again.
