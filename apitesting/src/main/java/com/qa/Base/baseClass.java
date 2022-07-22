package com.qa.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class baseClass {

    public int Response_Status_Code_200 = 200;
   public int Response_Status_Code_201 = 201;
   public int Response_Status_Code_400 = 400;
   public int Response_Status_Code_401 = 401;
   public int Response_Status_Code_500 = 500;

   public static Properties prop;

   public baseClass() {

      try (
         
         InputStream input = new FileInputStream("src/main/java/com/qa/Config/dev.properties")) {

         prop = new Properties();

         // load a properties file
         prop.load(input);

         // get the property value and print it out
         // System.out.println(prop.getProperty("url"));
         // System.out.println(prop.getProperty("serviceurl"));

      } catch (IOException ex) {
         ex.printStackTrace();
      }

   }

   
}
