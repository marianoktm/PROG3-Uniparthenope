package Client.Misc;

import java.io.FileInputStream;
import java.util.Properties;

public class ClientConfig {
    private Properties prop;

    public ClientConfig(String[] args) {
            // Set config path
        String configFilePath;
        if (args.length == 0)
                configFilePath = "src/client/client_config.properties";
            else
                configFilePath = args[0];

            System.out.println("Config path set to: " + configFilePath);

            try {
                FileInputStream propsInput = new FileInputStream(configFilePath);

                prop = new Properties();
                prop.load(propsInput);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

    }

    public ServerRecord getServerRecord() {
            return new ServerRecord(prop.getProperty("C_SRV_IP"), Integer.parseInt(prop.getProperty("C_SRV_PORT")));
        }

}
