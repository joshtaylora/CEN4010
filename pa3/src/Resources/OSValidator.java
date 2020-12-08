package Resources;

public class OSValidator {
    public String os;
    /* Default Constructor */
    public OSValidator() {
        String  operatingSystem = System.getProperty("os.name").toLowerCase();
        if (operatingSystem.contains("win")) {
            /* Updates the os string field to indicate the running OS is windows */
            this.os = "windows";
        }
        else if (operatingSystem.contains("mac")) {
            /* Updates the os string field to indicate the running OS is mac os */
            this.os = "mac";
        }
        else {
            /* Updates the os string field to indicate the running OS is an unsupported type */
            this.os = "unknown";
        }
    }
    public String getPathToFile(String fileName, String module) {
        String fileURL = null;
        String systemPath = System.getProperty("user.dir");
        if (this.os.equals("windows")) {
            if (systemPath.contains("pa3")) {
                fileURL = systemPath.concat("\\src\\" + module + "\\" + fileName);
            }
            else {
                fileURL = systemPath.concat("\\pa3\\src\\" + module + "\\" + fileName);
            }
        }
        else if (this.os.equals("mac")) {
            if (systemPath.contains("pa3")){
                fileURL = systemPath.concat("/src/" + module + "/" + fileName);
            }
            else {
                fileURL = systemPath.concat("/pa3/src/" + module + "/" + fileName);
            }
        }
        return fileURL;
    }
}

