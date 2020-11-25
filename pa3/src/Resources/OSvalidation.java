package Resources;

public class OSvalidation {
    public String os;
    public OSvalidation() {
        String  operatingSystem = System.getProperty("os.name").toLowerCase();
        if (operatingSystem.indexOf("win") >= 0) {
            this.os = "windows";
        }
        else if (operatingSystem.indexOf("mac") >= 0) {
            this.os = "mac";
        }
        else {
            this.os = "unknown";
        }
    }
}

