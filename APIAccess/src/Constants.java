
public class Constants {

	private static String key="52ddafbe3ee659bad97fcce7c53592916a6bfd73";
	private static String defaultProductInfo="Information not available";
	private static String fromEmail="zapposcode@gmail.com";
	private static String fromEmailPassword="summer2014intern";
	private static int sleepTimeEmail=2000; //in ms
	private static int sleepTimeBackendOperation=5000; //in ms
	private static String dbUserName="root";
	private static String dbPassword="root";
	private static String databaseName="zappos";
	public static String getDbUserName() {
		return dbUserName;
	}
	public static void setDbUserName(String dbUserName) {
		Constants.dbUserName = dbUserName;
	}
	public static String getDbPassword() {
		return dbPassword;
	}
	public static void setDbPassword(String dbPassword) {
		Constants.dbPassword = dbPassword;
	}
	public static String getDatabaseName() {
		return databaseName;
	}
	public static void setDatabaseName(String databaseName) {
		Constants.databaseName = databaseName;
	}
	public static String getKey() {
		return key;
	}
	public static String getFromEmail() {
		return fromEmail;
	}
	public static void setFromEmail(String fromEmail) {
		Constants.fromEmail = fromEmail;
	}
	public static int getSleepTimeEmail() {
		return sleepTimeEmail;
	}
	public static void setSleepTimeEmail(int sleepTimeEmail) {
		Constants.sleepTimeEmail = sleepTimeEmail;
	}
	public static int getSleepTimeBackendOperation() {
		return sleepTimeBackendOperation;
	}
	public static void setSleepTimeBackendOperation(int sleepTimeBackendOperation) {
		Constants.sleepTimeBackendOperation = sleepTimeBackendOperation;
	}
	public static void setKey(String key) {
		Constants.key = key;
	}
	public static String getDefaultProductInfo() {
		return defaultProductInfo;
	}
	public static void setDefaultProductInfo(String defaultProductInfo) {
		Constants.defaultProductInfo = defaultProductInfo;
	}
	public static String getFromEmailString() {
		return fromEmail;
	}
	public static void setFromEmailString(String fromEmailString) {
		Constants.fromEmail = fromEmailString;
	}
	public static String getFromEmailPassword() {
		return fromEmailPassword;
	}
	public static void setFromEmailPassword(String fromEmailPassword) {
		Constants.fromEmailPassword = fromEmailPassword;
	}
	
	
	
}
