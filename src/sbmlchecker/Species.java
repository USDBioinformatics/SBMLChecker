/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sbmlchecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Mathialakan.Thavappi
 */

public class Species {
    private String name;
    private String id;
    private Map<String, String> resources;
    private List<String> listOfDBs;
    private List<String> listOfOntologies;
    private boolean annotatedWithDB;
    private boolean annotatedWithOntology;
    private int dbAnnotationValue;
    private int ontologyAnnotationValue;

    public Species() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getResources() {
        return resources;
    }

    public void setResources(Map<String, String> resources) {
        this.resources = resources;
        this.listOfOntologies = new ArrayList<String>();
        this.listOfDBs = new ArrayList<String>();
        Set<String> dbOnt= this.resources.keySet();
        for(String s: dbOnt)
        switch(new SBMLResource().getType(s))
        {
            case "ontology":this.listOfOntologies.add(s); break;
            case "db":this.listOfDBs.add(s);
        }
    }

    public int getNumResource() {
        return this.resources.size();
    }

    public int getNumDatabases() {
        return this.listOfDBs.size();
    }

    public int getNumOntologie() {
        return this.listOfOntologies.size();
    }

    public List<String> getListOfDBs() {
        return listOfDBs;
    }

    public void setListOfDBs(List<String> listOfDBs) {
        this.listOfDBs = listOfDBs;
    }

    public List<String> getListOfOntologies() {
        return listOfOntologies;
    }

    public boolean isAnnotatedWithDB(String db) {
        annotatedWithDB = this.listOfDBs.contains(db);
        return annotatedWithDB;
    }

    public void setAnnotatedWithDB(boolean annotatedWithDB) {
        this.annotatedWithDB = annotatedWithDB;
    }

    public boolean isAnnotatedWithOntology(String ontology) {
        annotatedWithOntology = this.listOfOntologies.contains(ontology);
        return annotatedWithOntology; 
    }

    public void setAnnotatedWithOntology(boolean annotatedWithOntology) {
        this.annotatedWithOntology = annotatedWithOntology;
    }

    public void setListOfOntologies(List<String> listOfOntologies) {
        this.listOfOntologies = listOfOntologies;
    }
    
    public int getDbAnnotationValue(String db) {
        this.dbAnnotationValue = (isAnnotatedWithDB(db))? 1:0;
        return dbAnnotationValue;
    }

    public int getOntologyAnnotationValue(String ontology) {
        this.ontologyAnnotationValue = (isAnnotatedWithOntology(ontology))? 1:0;
        return ontologyAnnotationValue;
    }
    
    public String getURIId(String ontdb){
        String id =this.resources.get(ontdb);
        if (id!=null) return  id;
                else return "";
    }
}
