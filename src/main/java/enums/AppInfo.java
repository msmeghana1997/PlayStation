package enums;
/**
 * 
 * @author TestYantra
 *
 */
public enum AppInfo {

    ANDROID_AUTOMATION_NAME("UiAutomator2"),
    ANDROID_PLATFORM_NAME("android"),
    ANDROID_APP_PACKAGE("com.scee.psxandroid"),
    ANDROID_APP_ACTIVITY(".activity.TopActivity"),
    PLATFORM("android"),
    SERVER_URL("http://localhost:4723"),
    DEVICE_UDID("33c814800704")
    ;




    String label;
    AppInfo(String label)
    {
        this.label=label;
    }

    public String getLabel()
    {
        return  label;
    }
}
