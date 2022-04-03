package sports;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;

public class Controller {
    public static void main(String[] args) {
        System.out.println("READ XML File with JAXB");


        //JAXBContext jaxbContext = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Activity.class);

            File file = new File("C:\\temp\\activity.xml");

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Activity sportData = (Activity) jaxbUnmarshaller.unmarshal(file);

            //List<Fruit>  fits = sportData.getId();
            //Iterator s = sportData.getSport();
            for  (Field activity : sportData.getClass().getDeclaredFields()) {
                System.out.println(sportData);
                System.out.println(sportData.getSport());

               /*for (Field lap : sportData.getLap());  {
                /*System.out.println(sportData.getCalories());
                System.out.println(sportData.getDestination());
                System.out.println(sportData.getIntensity());
                System.out.println(sportData.getMaximumHeartRateBpm());
                System.out.println(sportData.getTotalTimeSeconds());
                System.out.println(sportData.getAverageHeartRateBpm());
                System.out.println(sportData.getStartTime());
               }*/
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


}
