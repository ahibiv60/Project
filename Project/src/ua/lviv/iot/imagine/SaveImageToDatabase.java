package ua.lviv.iot.imagine;

import java.sql.*;
import java.io.*;

class SaveImageToDatabase {
      public static void main(String[] args) throws SQLException {

            Connection connection = null;

            String connectionURL = "jdbc:mysql://localhost:3306/imagestore_java?useSSL=false";

            @SuppressWarnings("unused")
            ResultSet rs = null;

            PreparedStatement psmnt = null;

			FileInputStream fis;

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();

                connection = DriverManager.getConnection(connectionURL, "iotuser", "iotuser");

	            File image = new File("C:/Project/image.png");

	            psmnt = connection.prepareStatement("insert into save_image(filetype, image) "+
	                    "values(?,?)");
	                psmnt.setString(1,"image/png");

               fis = new FileInputStream(image);
	 
               psmnt.setBinaryStream(2, (InputStream)fis, (int)(image.length()));

               int s = psmnt.executeUpdate();
               if(s>0) {
                      System.out.println("Uploaded successfully !");
               }
               else {
		            System.out.println("Unsucessfull to upload image.");
               }
           }

           catch (Exception ex) {
	              System.out.println("Found some error : "+ex);
           }

           finally {
	            connection.close();
                psmnt.close();
           }
	  }
}
