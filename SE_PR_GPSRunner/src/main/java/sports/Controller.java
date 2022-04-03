package sports;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.util.*;

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
            System.out.println(sportData);
            //List<Fruit>  fits = sportData.getId();
            //Iterator s = sportData.getSport();
            //for  (Field activity : sportData.getClass().getDeclaredFields()) {
            /*for  (Field activity : sportData.getClass().getDeclaredFields()) {
                System.out.println(sportData);
                System.out.println(sportData.getSport());

               //for (Lap lap : sportData.getLap());  {
                //System.out.println(
                //System.out.println(lap.getDestination());
                //System.out.println(lap.getIntensity());
                //System.out.println(lap.getMaximumHeartRateBpm());
                //System.out.println(lap.getTotalTimeSeconds());
                //System.out.println(lap.getAverageHeartRateBpm());
                //System.out.println(lap.getStartTime());
               //}
            }*/

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


}
