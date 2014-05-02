package zinchenko.engl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.Properties;

@Controller
public class ConfigurationController {

    @Autowired
    @Qualifier("clientSideProperties")
    private Properties clientSideProperties;

    public

    public Properties getClientSideProperties() {
        return clientSideProperties;
    }

    public void setClientSideProperties(Properties clientSideProperties) {
        this.clientSideProperties = clientSideProperties;
    }

}
