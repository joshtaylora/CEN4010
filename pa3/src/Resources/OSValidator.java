package Resources;

public class OSValidator {
    public String os;
    public OSValidator() {
        String  operatingSystem = System.getProperty("os.name").toLowerCase();
        if (operatingSystem.contains("win")) {
            this.os = "windows";
        }
        else if (operatingSystem.contains("mac")) {
            this.os = "mac";
        }
        else {
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

