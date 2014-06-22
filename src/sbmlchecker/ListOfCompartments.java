/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sbmlchecker;


import java.util.ArrayList;
import java.util.List;
import org.sbml.jsbml.Model;

/**
 *
 * @author Mathialakan.Thavappi
 */
public class ListOfCompartments {
  
    Model model;
    List<Compartment> listOfCompartment;
    List<String> listOfDBs = new ArrayList<String>();
    List<String> listOfOntologies = new ArrayList<String>();
    List<Double> listOfCompartmentRatio; 
    double finalCompartmentValue;
    boolean valid;
    int numDbs;
    int numOntologies;
    int numCompartments;
   
    public ListOfCompartments() {     
    }
    
    public ListOfCompartments(Model model) {
        this.model = model;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<Compartment> getListOfCompartment() {
        return listOfCompartment;
    }

    public void setListOfCompartment(List<Compartment> listOfCompartment) {
        this.listOfCompartment = listOfCompartment;
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

    public void setListOfOntologies(List<String> listOfOntologies) {
        this.listOfOntologies = listOfOntologies;
    }

    public List<Double> getListOfCompartmentRatio() {
        return listOfCompartmentRatio;
    }

    public void setListOfCompartmentRatio(List<Double> listOfCompartmentRatio) {
        this.listOfCompartmentRatio = listOfCompartmentRatio;
    }

    public double getFinalCompartmentValue() {
        return finalCompartmentValue;
    }

    public void setFinalCompartmentValue(double finalCompartmentValue) {
        this.finalCompartmentValue = finalCompartmentValue;
    }

    public int getNumDbs() {
        return this.listOfDBs.size();
    }

    public int getNumOntologies() {
        return this.listOfOntologies.size();
    }

    public int getNumCompartments() {
        return this.listOfCompartment.size();
    }

    public void addListOfOntologies(String ontology){
       if (! this.listOfOntologies.contains(ontology))listOfOntologies.add(ontology);
    }
     
    public void addListOfDBs(String db){
       if (! this.listOfDBs.contains(db))listOfDBs.add(db);
    }
}
