package sports;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

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

            System.out.println(sportData);
            System.out.println(sportData.getSport());
            System.out.println(sportData.getCalories());
            System.out.println(sportData.getDestination());
            System.out.println(sportData.getIntensity());
            System.out.println(sportData.getMaximumHeartRateBpm());
            System.out.println(sportData.getTotalTimeSeconds());
            System.out.println(sportData.getAverageHeartRateBpm());
            System.out.println(sportData.getStartTime());


        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


}
