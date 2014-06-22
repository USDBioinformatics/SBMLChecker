/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sbmlchecker;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mathialakan.Thavappi
 */
public class SBMLChecker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SBMLChecker sbmlChecker = new SBMLChecker();
        String file = args[0]; //"C:\\Users\\Mathialakan.Thavappi\\Documents\\curated\\BIOMD0000000001.xml"; 
        ListOfElements list = new Elements().generateList(file);
        ArrayList<org.sbml.jsbml.SBMLError> sbmlError = new JsbmlValidator().SBMLFile(file);
        XStream xstream = new XStream();
        xstream.alias("ErrorLog", org.sbml.jsbml.SBMLError.class);
        String result = xstream.toXML(sbmlError);
        sbmlChecker.createFile("sbml_error_report1.xml", result);
        sbmlChecker.createFile("sbml_error_report2.xml", sbmlChecker.listOfElementsToXML(list));
        sbmlChecker.createFile("sbml_error_report2.txt", sbmlChecker.stringListOfElements(list));
        String filename = sbmlChecker.getFilename(file);
        System.out.println("filename :"+filename);
        sbmlChecker.copyFile(file, filename+".xml");
        
    }
    
     public String getFilename(String fullPath) { 
        int dot = fullPath.lastIndexOf(".");
        int sep = fullPath.lastIndexOf(File.separator);
        return fullPath.substring(sep + 1, dot);
    }
     
     private String listOfElementsToXML(ListOfElements listOfElements){
        XStream xstream = new XStream();
        xstream.alias("Compartments", Compartment.class);
        xstream.alias("listOfCompartments", ListOfCompartments.class);
        xstream.alias("Species", Species.class);
        xstream.alias("listOfSpecies", ListOfSpecies.class);
        xstream.alias("Reactions", Reaction.class);
        xstream.alias("listOfReactions", ListOfReactions.class);
        xstream.alias("listOfElements", ListOfElements.class);
        return xstream.toXML(listOfElements);
    }
     
    private void createFile(String name, String content){   
        File file = new File(name);
        try (FileOutputStream fop = new FileOutputStream(file)) {
 
			if (!file.exists()) {
				file.createNewFile();
			}
 
			byte[] contentInBytes = content.getBytes();
 
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public  void copyFile(String original, String copy) {
        File originFile = new File(original);
        File destinationFile = new File(copy);
        if (!originFile.exists() ){//|| destinationFile.exists()) {
        return;
            }
        try {
            byte[] readData = new byte[1024];
            FileInputStream fis = new FileInputStream(originFile);
            FileOutputStream fos = new FileOutputStream(destinationFile);
            int i = fis.read(readData);
            while (i != -1) {
                fos.write(readData, 0, i);
                i = fis.read(readData);
            }
            fis.close();
            fos.close();
        } catch (IOException e) {
          System.out.println(e);
        }
    }
    
    private String stringListOfElements(ListOfElements listOfElements){
        String result = listOfElements.isValid()? "It has valid semantic \n\n" : "It has not valid semantic  \n\n";
        result+="Score is "+listOfElements.getFinalValue()+"\n";
        ListOfCompartments listComp= listOfElements.getListOfCompartments();
        result += "Compartment \n\n ";
        result+="Final score for compartment is "+listComp.getFinalCompartmentValue()+"\n\n";
        result+= "ID \t Name \t"; 
        
        for(String key: listComp.getListOfDBs())
                result+= key+"\t";
        for(String key: listComp.getListOfOntologies())
                result+= key+"\t";
        
        result+= "\n";
        for(Compartment ele: listOfElements.getListOfCompartments().getListOfCompartment()){
            result+=ele.getId()+"\t"+ ele.getName()+"\t";
            for(String key: listOfElements.getListOfCompartments().getListOfDBs())
                result+= ele.getDbAnnotationValue(key)+"\t";
            for(String key: listOfElements.getListOfCompartments().getListOfOntologies())
                result+= ele.getOntologyAnnotationValue(key)+"\t";
            
            result+="\n";
        }
        result+="\t Compartment score : ";
        for(Double ratio: listOfElements.getListOfCompartments().getListOfCompartmentRatio())
                result+= ratio+"\t";
         
        result += "\n Species \n\n "; 
        result+="Final score for species is "+listOfElements.getListOfSpecies().getFinalSpeciesValue()+"\n\n";
        result+= "ID \t Name \t"; 
        for(String key: listOfElements.getListOfSpecies().getListOfDBs())
                result+= key+"\t";
        for(String key: listOfElements.getListOfSpecies().getListOfOntologies())
                result+= key+"\t";
        
        result+= "\n";
        for(Species ele: listOfElements.getListOfSpecies().getListOfSpecies()){
            result+=ele.getId()+"\t"+ ele.getName()+"\t";
            for(String key: listOfElements.getListOfSpecies().getListOfDBs())
                result+= ele.getDbAnnotationValue(key)+"\t";
            for(String key: listOfElements.getListOfSpecies().getListOfOntologies())
                result+= ele.getOntologyAnnotationValue(key)+"\t";
           
            result+="\n";
        } 
        result+="\t Species score : ";
        for(Double ratio: listOfElements.getListOfSpecies().getListOfSpeciesRatio())
                result+= ratio+"\t";
        
        result += "\nReaction \n\n "; 
        result+="Final score for reaction is "+listOfElements.getListOfReactions().getFinalReactionValue()+"\n\n";
        result+= "ID \t Name \t";
        for(String key: listOfElements.getListOfReactions().getListOfDBs())
                result+= key+"\t";
        for(String key: listOfElements.getListOfReactions().getListOfOntologies())
                result+= key+"\t";
       
        result+= "\n";
        for(Reaction ele: listOfElements.getListOfReactions().getListOfReaction()){
            result+=ele.getId()+"\t"+ ele.getName()+"\t";
            for(String key: listOfElements.getListOfReactions().getListOfDBs())
                result+= ele.getDbAnnotationValue(key)+"\t";
            for(String key: listOfElements.getListOfReactions().getListOfOntologies())
                result+= ele.getOntologyAnnotationValue(key)+"\t";
            
            result+="\n";
        } 
        result+="\t Reaction score : ";
       for(Double ratio: listOfElements.getListOfReactions().getListOfReactionRatio())
                result+= ratio+"\t";
        
       return result;
    }
}
