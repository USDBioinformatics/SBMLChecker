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
public class ListOfReactions {
    
    Model model;
    List<Reaction> listOfReaction;
    List<String> listOfDBs = new ArrayList<String>();
    List<String> listOfOntologies = new ArrayList<String>();
    List<Double> listOfReactionRatio;
    double finalReactionValue;
    boolean valid;
    int numDbs;
    int numOntologies;
    int numReactions;
    
    public ListOfReactions() {     
    }
    
    public ListOfReactions(Model model) {
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

    public List<Reaction> getListOfReaction() {
        return listOfReaction;
    }

    public void setListOfReaction(List<Reaction> listOfReaction) {
        this.listOfReaction = listOfReaction;
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

    public List<Double> getListOfReactionRatio() {
        return listOfReactionRatio;
    }

    public void setListOfReactionRatio(List<Double> listOfReactionRatio) {
        this.listOfReactionRatio = listOfReactionRatio;
    }

    public double getFinalReactionValue() {
        return finalReactionValue;
    }

    public void setFinalReactionValue(double finalReactionValue) {
        this.finalReactionValue = finalReactionValue;
    }

    public int getNumDbs() {
        return this.listOfDBs.size();
    }

    public int getNumOntologies() {
        return this.listOfOntologies.size();
    }

    public int getNumReactions() {
        return this.listOfReaction.size();
    }

    public void addListOfOntologies(String ontology){
       if (! this.listOfOntologies.contains(ontology))listOfOntologies.add(ontology);
    }
     
    public void addListOfDBs(String db){
       if (! this.listOfDBs.contains(db))listOfDBs.add(db);
    }
}
