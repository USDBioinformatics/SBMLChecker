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
 * @author Mathialakan
 */
public class ListOfSpecies {

    Model model;
    List<Species> listOfSpecies;
    List<String> listOfDBs = new ArrayList<String>();
    List<String> listOfOntologies = new ArrayList<String>();
    List<Double> listOfSpeciesRatio;
    double finalSpeciesValue;
    boolean valid;
    int numDbs;
    int numOntologies;
    int numSpecies;
    
    public ListOfSpecies() {     
    }
    
    public ListOfSpecies(Model model) {
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

    public List<Species> getListOfSpecies() {
        return listOfSpecies;
    }

    public void setListOfSpecies(List<Species> listOfSpecies) {
        this.listOfSpecies = listOfSpecies;
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

    public List<Double> getListOfSpeciesRatio() {
        return listOfSpeciesRatio;
    }

    public void setListOfSpeciesRatio(List<Double> listOfSpeciesRatio) {
        this.listOfSpeciesRatio = listOfSpeciesRatio;
    }

    public double getFinalSpeciesValue() {
        return finalSpeciesValue;
    }

    public void setFinalSpeciesValue(double finalSpeciesValue) {
        this.finalSpeciesValue = finalSpeciesValue;
    }

    public int getNumDbs() {
        return this.listOfDBs.size();
    }

    public int getNumOntologies() {
        return this.listOfOntologies.size();
    }

    public int getNumSpecies() {
        return this.listOfSpecies.size();
    }

    public void addListOfOntologies(String ontology){
       if (! this.listOfOntologies.contains(ontology))listOfOntologies.add(ontology);
    }
     
    public void addListOfDBs(String db){
       if (! this.listOfDBs.contains(db))listOfDBs.add(db);
    }
}
