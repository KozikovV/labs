package laba;

/**
 * Load properties from XML
 * 
 * @author zinchenko
 * 
 */
public interface XMLPropertyLoader {

    /**
     * Getting URL.
     * 
     * @return URL
     */
    public String getUrl();

    /**
     * Getting Driver.
     * 
     * @return Driver
     */
    public String getDriver();

    /**
     * Getting username.
     * 
     * @return username
     */
    public String getUsername();

    /**
     * Getting password.
     * 
     * @return password.
     */
    public String getPassword();

    /**
     * Set name of file with settings.
     * 
     * @param arg0
     *            name of a file
     */
    public void setFileName(String arg0);

}
