/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sbmlchecker;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import org.sbml.jsbml.validator.SBMLValidator;
import org.sbml.jsbml.SBMLErrorLog;
import org.sbml.jsbml.SBMLError;

/**
 *
 * @author Mathialakan.Thavappi
 */

public class JsbmlValidator implements Serializable {

    /**
     * Creates a new instance of JsbmlValidator
     */
    
    
    public JsbmlValidator() {
    }

    public ArrayList<SBMLError> SBMLFile( String fileName) {
        //path = sbmlPath.getSavePath();
        HashMap<String, String> parameters= new HashMap<String, String>();
        parameters.put("output", "xml");
        SBMLErrorLog errorLog = SBMLValidator.checkConsistency(fileName, parameters);
        //SBMLErrorLog errorLog = SBMLValidator.checkConsistency(jSBMLFile.writeString(jSBMLFile.read(path+fileName+".xml")));
        if (errorLog==null) return null;
        System.out.println("NumErrors: "+errorLog.getNumErrors());
        ArrayList<SBMLError> errorList = new ArrayList<SBMLError>();
        for(long i=0; i< errorLog.getNumErrors(); i++)
        {  
            SBMLError sbmlError = errorLog.getError(i);
            errorList.add(sbmlError);  //System.out.println("Errors: "+errorString);
        }
        return errorList;
    }
    
   
    public ArrayList<String> SBMLXml( String xml) {
        //path = sbmlPath.getSavePath();
        String errorString="Errors";
        SBMLErrorLog errorLog = SBMLValidator.checkConsistency(xml);
        ArrayList<String> errorList = new ArrayList<String>();
        for(long i=0; i< errorLog.getNumErrors(); i++){
            errorString = errorLog.getError(i).getMessage();
            errorList.add(errorString);  //System.out.println("Errors: "+errorString);
            System.out.println("Errors: "+errorString);
        }
        return errorList;
    }
}
