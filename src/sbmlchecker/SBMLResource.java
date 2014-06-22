/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sbmlchecker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.sbml.jsbml.Annotation;
import org.sbml.jsbml.CVTerm;

/**
 *
 * @author Mathialakan
 */
public class SBMLResource {

    public Map<String, String> getResources(org.sbml.jsbml.Species species) {
        Map<String, String> temp = new HashMap<String, String>();
        if (species.isSetAnnotation()) {
            Annotation a = species.getAnnotation();
            if (a.isSetAnnotation()) {
                for (CVTerm cvm : a.getListOfCVTerms()) {
                    System.out.println("species cvterm :" + cvm.toString());
                    for (String res : cvm.getResources()) {
                        System.out.println("species resource: " + res);
                        Map<String, String> strRes = getResources(res);
                        if (strRes != null) {
                            temp.putAll(strRes);
                        }
                    }
                }
            }
        }
        return temp;
    }

    public Map<String, String> getResources(org.sbml.jsbml.Compartment compartment) {
        Map<String, String> temp = new HashMap<String, String>();
        if (compartment.isSetAnnotation()) {
            Annotation a = compartment.getAnnotation();
            if (a.isSetAnnotation()) {
                for (CVTerm cvm : a.getListOfCVTerms()) {
                    System.out.println("compartment cvterm :" + cvm.toString());
                    for (String res : cvm.getResources()) {
                        System.out.println("compartment resource: " + res);
                        Map<String, String> strRes = getResources(res);
                        if (strRes != null) {
                            temp.putAll(strRes);
                        }
                    }
                }
            }
        }
        return temp;
    }

    public Map<String, String> getResources(org.sbml.jsbml.Reaction reaction) {
        Map<String, String> temp = new HashMap<String, String>();
        if (reaction.isSetAnnotation()) {
            Annotation a = reaction.getAnnotation();
            if (a.isSetAnnotation()) {
                for (CVTerm cvm : a.getListOfCVTerms()) {
                    System.out.println("reaction cvterm :" + cvm.toString());
                    for (String res : cvm.getResources()) {
                        System.out.println("reaction resource: " + res);
                        Map<String, String> strRes = getResources(res);
                        if (strRes != null) {
                            temp.putAll(strRes);
                        }
                    }
                }
            }
        }
        return temp;
    }

    public Map<String, String> getResources(String resource) {
        Map<String, String> temp = new HashMap<String, String>();
        String delim;
        if (resource.contains("/")) {
            delim = "/";
        } else if (resource.contains(":")) {
            delim = ":";
        } else {
            return null;
        }
        String[] terms = resource.split(delim);
        int length = terms.length;
        //System.out.println("length  : "+ length);
        //System.out.println("ob: "+terms[length-2]);
        // System.out.println("....: "+terms[length-2].contains("."));
        //String[] intTerms = terms[length-2].split(".");
        String ontDb = terms[length - 2];
        if (terms[length - 2].contains(".")) {
            ontDb = terms[length - 2].substring(0, terms[length - 2].indexOf("."));
        }
        System.out.println("intTerms  : " + ontDb);
        temp.put(ontDb, terms[length - 1]);
        // temp.put(terms[length-2].substring(0, terms[length-2].indexOf(".")), terms[length-1]);
        return temp;
    }

    public List<String> getOntologies() {
        List<String> ontologies = Arrays.asList("obo", "go");
        //ontologies.add("obo");
        //ontologies.add("go");
        return ontologies;
    }

    public List<String> getDbs() {
        List<String> dbs = Arrays.asList("kegg", "pubchem", "uniprot", "ec-code");
        //dbs.add("kegg");
        //dbs.add("pubchem");
        //dbs.add("uniprot");
        //dbs.add("ec-code");
        return dbs;
    }

    public String getType(String dbOnt) {
        List<String> ontologies = getOntologies();
        List<String> dbs = getDbs();
        String type = "";
        if (ontologies.contains(dbOnt)) {
            type = "ontology";
        } else if (dbs.contains(dbOnt)) {
            type = "db";
        }else type = "db";
        System.out.println("dbOnt : " + dbOnt + " type : " + type);
        return type;
    }
    /*
     public String[][] getResources(org.sbml.jsbml.Species species){
     String[][] temp=null; 
     if(species.isSetAnnotation()){ 
     Annotation a = species.getAnnotation();
     if(a.isSetAnnotation()) 
     for(CVTerm cvm : a.getListOfCVTerms()){
     System.out.println("cvterm :"+ cvm.toString());
     temp = new String[2][cvm.getNumResources()];
     for( int i = 0; i< cvm.getNumResources(); i++){
     String res = cvm.getResourceURI(i);
     System.out.println("resource: "+ res);
     temp[i][0] = getResources(res)[i][0];
     temp[i][1] = getResources(res)[i][1];
     }
     }
     }  
     return temp;
     }
     
     public String[][] getResources(org.sbml.jsbml.Compartment compartment){
     Map<String, String> temp = new HashMap<String, String>(); 
     if(compartment.isSetAnnotation()){ 
     Annotation a = compartment.getAnnotation();
     if(a.isSetAnnotation()) 
     for(CVTerm cvm : a.getListOfCVTerms()){
     System.out.println("cvterm :"+ cvm.toString());
     for( String res: cvm.getResources()){
     System.out.println("resource: "+ res);
     temp.putAll(getResources(res));
     }
     }
     }  
     return temp;
     }
   
     public String[][] getResources(org.sbml.jsbml.Reaction reaction){
     Map<String, String> temp = new HashMap<String, String>(); 
     if(reaction.isSetAnnotation()){ 
     Annotation a = reaction.getAnnotation();
     if(a.isSetAnnotation()) 
     for(CVTerm cvm : a.getListOfCVTerms()){
     System.out.println("cvterm :"+ cvm.toString());
     for( String res: cvm.getResources()){
     System.out.println("resource: "+ res);
     temp.putAll(getResources(res));
     }
     }
     }  
     return temp;
     }
    
     public String[][] getResources(String  resource){
     Map<String, String> temp = new HashMap<String, String>(); 
     String[] terms = resource.split(":");
     int length = terms.length;
     temp.put(terms[length-2], terms[length-1]);
     // temp.put(terms[length-2].substring(0, terms[length-2].indexOf(".")), terms[length-1]);
     return temp;
     }
     */
}
