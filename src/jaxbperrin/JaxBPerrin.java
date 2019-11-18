/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxbperrin;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import itta.LaClasseGenere;
import itta.ObjectFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirObjectFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/*
C:\Users\Administrator\Documents\NetBeansProjects\JaxBPerrin>"C:\Program Files\Java\jdk1.8.0_171\bin\schemagen.exe" -cp ./dist/JaxBPerrin.jar jaxbperrin.LaClasse
C:\Users\Administrator\Documents\NetBeansProjects\JaxBPerrin>"C:\Program Files\Java\jdk1.8.0_171\bin\xjc.exe" -p itta -d src schema1.xsd
*/
public class JaxBPerrin {


    public static void main(String[] args) {
        try {
           
            JAXBContext context = JAXBContext.newInstance(LaClasseGenere.class);
            Unmarshaller um= context.createUnmarshaller();
            
            LaClasseGenere lcg = (LaClasseGenere) um.unmarshal( 
                    new FileReader(System.getProperty("user.dir")+"\\build\\classes\\resources\\perrin.xml"));
            System.out.println(lcg);
        } catch (JAXBException | FileNotFoundException ex) {
            System.out.println(ex);
        }
         
    }

    static void createXmlFromObject() {
        try {
            JAXBContext context = JAXBContext.newInstance(LaClasse.class);
            Marshaller m = context.createMarshaller();
            LaClasse lc = new LaClasse("tavel", "pierre", 180, new Date());
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(lc, System.out);
        } catch (JAXBException ex) {
            System.out.println(ex);
        }
        try {
            JAXBContext context = JAXBContext.newInstance(LaClasseGenere.class);
            Marshaller m = context.createMarshaller();
            ObjectFactory fact = new ObjectFactory();
          
            LaClasseGenere lc = fact.createLaClasseGenere();
            lc.setNaissance(XMLGregorianCalendarImpl.createDate(2010,12,10,0));
            lc.setNom("tavel");
            lc.setPrenom("pierre");
            lc.setTaille(180);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            JAXBElement<LaClasseGenere> je = fact.createLaClasseGenere(lc);
            m.marshal(je, System.out);
            
        } catch (JAXBException ex) {
            System.out.println(ex);
        }
    }
    
}
