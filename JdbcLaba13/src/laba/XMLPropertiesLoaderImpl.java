package laba;

import java.io.File;
import java.io.IOException;
import java.nio.channels.IllegalSelectorException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The utility class for loading database settings.
 * 
 * @author zinchenko
 * 
 */
public class XMLPropertiesLoaderImpl implements XMLPropertyLoader {

    private static final String USER_NAME = "db-user-name";
    private static final String USER_PASSWORD = "db-user-password";
    private static final String DRIVER_CLASS = "driver-class-name";
    private static final String URL = "url";

    private String fileName;

    private Map<String, String> properties;

    private boolean wasRead;

    /**
     * Default constructor.
     */
    public XMLPropertiesLoaderImpl() {
        fileName = "jdbc-prop.xml";
    }

    /**
     * Constructor which take name of file as parameter.
     * 
     * @param fileName
     *            name of file.
     */
    public XMLPropertiesLoaderImpl(String fileName) {
        super();
        this.fileName = fileName;
    }

    /**
     * Set name of file
     * 
     * @param arg0
     *            name of file
     * 
     * @throws IllegalArgumentException
     *             if arg0 == null of if arg0 is empty
     * 
     */
    public void setFileName(String arg0) {
        if (arg0 == null) {
            throw new NullPointerException(" the name of file is null");
        }

        if (arg0.isEmpty()) {
            throw new IllegalArgumentException(" the name of file is empty");
        }

        fileName = arg0;
    }

    /**
     * Reading a XML file.
     */
    protected void read() {

        properties = new HashMap<String, String>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        File file = new File(fileName);
        Document doc = null;
        try {
            doc = db.parse(file);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Node jdbcNode = doc.getFirstChild();
        NodeList childsOfJdbc = jdbcNode.getChildNodes();
        int n = childsOfJdbc.getLength();
        for (int i = 0; i < n; i++) {
            Node node = childsOfJdbc.item(i);
            String childNodeName = node.getNodeName().intern();

            if (childNodeName != null && childNodeName.intern() == USER_NAME) {
                properties.put(USER_NAME, node.getTextContent().intern());
            } else if (childNodeName != null
                    && childNodeName.intern() == USER_PASSWORD) {
                properties.put(USER_PASSWORD, node.getTextContent().intern());
            } else if (childNodeName != null
                    && childNodeName.intern() == DRIVER_CLASS) {
                properties.put(DRIVER_CLASS, node.getTextContent().intern());
            } else if (childNodeName != null && childNodeName.intern() == URL) {
                properties.put(URL, node.getTextContent().intern());
            }
        }

    }

    /**
     * Getting URL
     * 
     * @return URL
     */
    @Override
    public String getUrl() {

        if (properties == null) {
            read();
        }

        return properties.get(URL);
    }

    /**
     * Getting name of a Driver.
     * 
     * @return name of a Driver
     */
    @Override
    public String getDriver() {
        if (properties == null) {
            read();
        }

        return properties.get(DRIVER_CLASS);
    }

    /**
     * Getting an username.
     * 
     * @return username
     */
    @Override
    public String getUsername() {
        if (properties == null) {
            read();
        }

        return properties.get(USER_NAME);
    }

    /**
     * Getting a password.
     * 
     * @return password
     * 
     */
    @Override
    public String getPassword() {
        if (properties == null) {
            read();
        }

        return properties.get(USER_PASSWORD);
    }

}
