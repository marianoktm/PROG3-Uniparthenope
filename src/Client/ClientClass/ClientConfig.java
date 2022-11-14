package Client.ClientClass;

import Client.Misc.ServerRecord;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Auxiliary class to get client config properties and load them properly.
 */
public class ClientConfig {
    private Properties prop;

    /**
     * Configures the client in order to work properly. Loads the properties in client_config.properties.
     * @param args custom path to client_config.properties
     */
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

        TwitterClient.getInstance().init(this.getServerRecord());
    }

    /**
     *
     * @return a ServerRecord fetched from properties file
     */
    private ServerRecord getServerRecord() {
        return new ServerRecord(prop.getProperty("C_SRV_IP"), Integer.parseInt(prop.getProperty("C_SRV_PORT")));
    }
}
