package zinchenko.engl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zinchenko.engl.bean.ClientSideConfig;

import java.util.Properties;

@Controller
@RequestMapping("/config")
public class ConfigurationController {

    @Autowired
    @Qualifier("clientSideProperties")
    private Properties clientSideProperties;

    @RequestMapping(value = "/clientSide", method = RequestMethod.GET)
    public @ResponseBody
    ClientSideConfig getClientSideConfig(){
        ClientSideConfig clientSideConfig = new ClientSideConfig();
        clientSideConfig.setEnvironment(clientSideProperties);
        return clientSideConfig;
    }

    public Properties getClientSideProperties() {
        return clientSideProperties;
    }

    public void setClientSideProperties(Properties clientSideProperties) {
        this.clientSideProperties = clientSideProperties;
    }
}
