package Resources;

import java.io.File;
import java.net.MalformedURLException;

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
        String filePath = null;
        File retFile = null;
        String systemPath = System.getProperty("user.dir");
        if (this.os.equals("windows")) {
            if (systemPath.contains("pa3")) {
                filePath = systemPath.concat("\\src\\" + module + "\\" + fileName);
            }
            else {
                filePath = systemPath.concat("\\pa3\\src\\" + module + "\\" + fileName);
            }
        }
        else if (this.os.equals("mac")) {
            if (systemPath.contains("pa3")){
                filePath = systemPath.concat("/src/" + module + "/" + fileName);
            }
            else {
                filePath = systemPath.concat("/pa3/src/" + module + "/" + fileName);
            }
        }
        else {
            System.out.println("OS not supported");
            System.exit(1);
        }
        try {
            retFile = new File(filePath);
            fileURL = retFile.toURI().toURL().toString();
        } catch (MalformedURLException me) {
            me.printStackTrace();
        }
        return fileURL;
    }
}

