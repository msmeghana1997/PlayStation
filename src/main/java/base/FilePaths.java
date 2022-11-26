package base;

public interface FilePaths {

	String ABSOLUTEPATH=System.getProperty("user.dir");
	String APPDATA=System.getenv("APPDATA");
	String EXCELDATA="./src/test/TestData/PlaystationTestData.xlsx";
	String PROPERTYFILE="./src/test/TestData/TestData.properties";
	String LOG4J_PROPERTIES="./src/main/java/resources/log4j.properties";
	String APPIUM_SERVER_IP="127.0.0.1";
	String APPIUM_JS_FILE=APPDATA+"\\npm\\node_modules\\appium\\build\\lib\\main.js";
	String APPIUM_NODEJS=ABSOLUTEPATH+"/resourse/node.exe";
	String PLAYSTATION_APK="./src/main/java/resources/Playstation.apk";
	String DEVICEFILEPATH="/sdcard/DCIM/screenshot.png";
	String PROFILEIMAGEPATH="./src/test/Images/ProfileImage.png";
}
