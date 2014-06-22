/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sbmlchecker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mathialakan
 */
public class ListOfElements {
  
    ListOfCompartments  listOfCompartments = new ListOfCompartments();
    ListOfSpecies listOfSpecies = new ListOfSpecies();
    ListOfReactions listOfReactions = new ListOfReactions();
    List<String> listOfDBs;
    List<String> listOfOntologies;
    double finalValue;
    boolean valid;

    public ListOfElements() {
    }

    public ListOfCompartments getListOfCompartments() {
        return listOfCompartments;
    }

    public void setListOfCompartments(ListOfCompartments listOfCompartments) {
        this.listOfCompartments = listOfCompartments;
    }

    public ListOfSpecies getListOfSpecies() {
        return listOfSpecies;
    }

    public void setListOfSpecies(ListOfSpecies listOfSpecies) {
        this.listOfSpecies = listOfSpecies;
    }

    public ListOfReactions getListOfReactions() {
        return listOfReactions;
    }

    public void setListOfReactions(ListOfReactions listOfReactions) {
        this.listOfReactions = listOfReactions;
    }

    public double getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(double finalValue) {
        this.finalValue = finalValue;
    }
    
    public List<String> getListOfDBs() {
        listOfDBs = new ArrayList<String>();
        listOfDBs.addAll(listOfCompartments.getListOfDBs());
        listOfDBs = addDistinctList(listOfDBs, listOfSpecies.getListOfDBs());
        listOfDBs = addDistinctList(listOfDBs, listOfReactions.getListOfDBs());
        return listOfDBs;
    }

    public void setListOfDBs(List<String> listOfDBs) {
        this.listOfDBs = listOfDBs;
    }

    public List<String> getListOfOntologies() {
        listOfOntologies = new ArrayList<String>();
        listOfOntologies.addAll(listOfCompartments.getListOfOntologies());
        listOfOntologies = addDistinctList(listOfOntologies, listOfSpecies.getListOfOntologies());
        listOfOntologies = addDistinctList(listOfOntologies, listOfReactions.getListOfOntologies());
        return listOfOntologies;
    }

    public void setListOfOntologies(List<String> listOfOntologies) {
        this.listOfOntologies = listOfOntologies;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    public List<String> addDistinct(List<String> list, String ele){
       if (! list.contains(ele))list.add(ele);
       return list;
    }
    
    public List<String> addDistinctList(List<String> list, List<String> listele){
       for(String ele:listele)
            list = addDistinct(list, ele);
       return list;
    }
}
