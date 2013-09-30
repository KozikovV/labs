package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Permission;

/**
 * User: zinchenko
 * Date: 9/4/13
 */
public class Main {
    public static void main(String[] args) throws IOException {
        loadAndPrintWebPage();
        printSeparator();

        requestInformation();

    }

    public static void printSeparator() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    public static void print(String name, Object value){
        System.out.println(name+": "+String.valueOf(value));
    }

    public static void loadAndPrintWebPage() {
        String urlAsString = "https://www.google.com.ua/";
        try {
            URL url = new URL(urlAsString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void requestInformation() throws IOException {
        String urlAsString = "https://www.google.com.ua/";
        URL url = new URL(urlAsString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        int responseCode = httpURLConnection.getResponseCode();

        print("Response Code", responseCode);
        print("Recuest Method", httpURLConnection.getRequestMethod());
        print("","");

        String headerField = null;
        String headerKey = null;
        int headerNumber = 0;
        while((headerField = httpURLConnection.getHeaderField(headerNumber))!=null){
            headerKey = httpURLConnection.getHeaderFieldKey(headerNumber);
            print(headerKey, headerField);
            headerNumber++;
        }

        Permission permission = httpURLConnection.getPermission();


        print("","");
    }


}
