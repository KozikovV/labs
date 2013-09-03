package laba;

public class M {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        XMLPropertyLoader pl = new XMLPropertiesLoaderImpl();
        
        String dr = pl.getDriver();
        String un = pl.getUsername();
        String p = pl.getPassword();
        String url = pl.getUrl();
        
        System.out.println("sadf");
        

    }

}
