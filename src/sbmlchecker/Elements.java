/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sbmlchecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;

/**
 *
 * @author Mathialakan.Thavappi
 */
public class Elements {
     
    double finalCombVal;
    double finalSpsVal;
    double finalReactVal;
    double finalValue;
    boolean valid;
    double thres = 1.0;
    ListOfCompartments  listOfCompartments = new ListOfCompartments();
    ListOfSpecies listOfSpecies = new ListOfSpecies();
    ListOfReactions listOfReactions = new ListOfReactions();

    public double getFinalCombVal() {
        return finalCombVal;
    }

  
    public double getFinalSpsVal() {
        return finalSpsVal;
    }


    public double getFinalReactVal() {
        return finalReactVal;
    }

   
   public ListOfElements generateList(Model model){
       
      ListOfElements  listOfElements = new ListOfElements();
      listOfCompartments.setListOfCompartment(generateCompartmentList(model.getListOfCompartments()));
      listOfCompartments.setListOfCompartmentRatio(calculateCompartmentRatio());
      listOfCompartments.setFinalCompartmentValue(finalCombVal);
      listOfCompartments.setValid((finalCombVal>=thres));
      listOfElements.setListOfCompartments(listOfCompartments);
      
      listOfSpecies.setListOfSpecies(generateSpeciesList(model.getListOfSpecies()));
      listOfSpecies.setListOfSpeciesRatio(calculateSpeciesRatio());
      listOfSpecies.setFinalSpeciesValue(finalSpsVal);
      listOfSpecies.setValid((finalSpsVal>=thres));
      listOfElements.setListOfSpecies(listOfSpecies);
      
      listOfReactions.setListOfReaction(generateReactionList(model.getListOfReactions()));
      listOfReactions.setListOfReactionRatio(calculateReactionRatio());
      listOfReactions.setFinalReactionValue(finalReactVal);
      listOfReactions.setValid((finalReactVal>=thres));
      listOfElements.setListOfReactions(listOfReactions);
      
      listOfElements.setFinalValue(getFinalValue());
      listOfElements.setValid(isValid());
    return listOfElements;
   }
   
   public ListOfElements generateList(String  file){
      FileCtrl sbmlfile = new FileCtrl();
      Model model = sbmlfile.read(file).getModel();
      return generateList(model);
   }
   
   public List<Species> generateSpeciesList( ListOf<org.sbml.jsbml.Species> species){
        List<Species> speciesList = new ArrayList<Species>();
        for(int i=0; i<species.size(); i++ ){
            System.out.println(" sp "+i+" :"+ species.get(i).getId());
           speciesList.add(formSpecies(species.get(i)));
        }
        return speciesList;
    }
    
    public List<Reaction> generateReactionList( ListOf<org.sbml.jsbml.Reaction> reaction){
        List<Reaction> reactionesList = new ArrayList<Reaction>();
        for(int i=0; i<reaction.size(); i++ ){
            System.out.println(" reaction "+i+" :"+ reaction.get(i).getId());
           reactionesList.add(formReaction(reaction.get(i)));
        }
        return reactionesList;
    }
    
    public List<Compartment> generateCompartmentList( ListOf<org.sbml.jsbml.Compartment> compartment){
        List<Compartment> compartmentList = new ArrayList<Compartment>();
        for(int i=0; i<compartment.size(); i++ ){
            System.out.println(" compartment "+i+" :"+ compartment.get(i).getId());
           compartmentList.add(formCompartment(compartment.get(i)));
        }
        return compartmentList;
    }
    
    private Species formSpecies( org.sbml.jsbml.Species species){
        Species speciesURI = new Species();
        SBMLResource sBMLResource = new SBMLResource();
        if(species.isSetName())  speciesURI.setName(species.getName());
            else  speciesURI.setName(species.getId());
        if(species.isSetId()) speciesURI.setId(species.getId());   
        speciesURI.setResources(sBMLResource.getResources(species));
        for(String ont: speciesURI.getListOfOntologies())
            listOfSpecies.addListOfOntologies(ont);
        for(String db: speciesURI.getListOfDBs())
            listOfSpecies.addListOfDBs(db);
           
    return speciesURI;
    }
    
     private Reaction formReaction( org.sbml.jsbml.Reaction reaction){
        SBMLResource sBMLResource = new SBMLResource();
        Reaction reactionURI = new Reaction();
        if(reaction.isSetId())   reactionURI.setId(reaction.getId());
        if(reaction.isSetName()) reactionURI.setName(reaction.getName());
        else reactionURI.setName(reaction.getId());
        reactionURI.setResources(sBMLResource.getResources(reaction));
        for(String ont: reactionURI.getListOfOntologies())
            listOfReactions.addListOfOntologies(ont);
        for(String db: reactionURI.getListOfDBs())
            listOfReactions.addListOfDBs(db);
       return reactionURI;
    }
     
     private Compartment formCompartment(org.sbml.jsbml.Compartment compartment){
        SBMLResource sBMLResource = new SBMLResource();
        Compartment  compartmentURI = new Compartment();
        compartmentURI.setId(compartment.getId());
        if(compartment.isSetName()) compartmentURI.setName(compartment.getName());
        else compartmentURI.setName(compartment.getId());
        compartmentURI.setResources(sBMLResource.getResources(compartment));
        for(String ont: compartmentURI.getListOfOntologies())
           listOfCompartments.addListOfOntologies(ont);
         for(String db: compartmentURI.getListOfDBs())
             listOfCompartments.addListOfDBs(db);
        return compartmentURI;
    }
    
    private List<Double> calculateCompartmentRatio(){
        Double[] compv = new Double[listOfCompartments.getNumDbs()+listOfCompartments.getNumOntologies()];
        for(int i=0; i< compv.length; i++) compv[i] =0.0;
        int i=0;
        List<String> dbs = listOfCompartments.getListOfDBs();
        for(i=0; i< listOfCompartments.getNumDbs(); i++){
            double sum=0;
            for(Compartment comp: listOfCompartments.getListOfCompartment()) 
                sum+=comp.getDbAnnotationValue(dbs.get(i));
            compv[i] = sum/listOfCompartments.getNumCompartments();
        }
        List<String> onts = listOfCompartments.getListOfOntologies();
        for(int j=0; j< listOfCompartments.getNumOntologies(); j++){
            double sum=0;
            for(Compartment comp: listOfCompartments.getListOfCompartment()) 
                sum += comp.getOntologyAnnotationValue(onts.get(j));
            compv[j+i] = sum/listOfCompartments.getNumCompartments();       
        }
        this.finalCombVal = calculateRatioSum(compv);
        return Arrays.asList(compv);
    }
     
    private List<Double> calculateSpeciesRatio(){
        Double[] compv = new Double[listOfSpecies.getNumDbs()+listOfSpecies.getNumOntologies()];
        for(int i=0; i< compv.length; i++) compv[i] =0.0;
        List<String> dbs = listOfSpecies.getListOfDBs();
        int i=0;
        for(i=0; i< listOfSpecies.getNumDbs(); i++){
            double sum=0;
            for(Species sps: listOfSpecies.getListOfSpecies()) 
                sum+=sps.getDbAnnotationValue(dbs.get(i));
            compv[i] = sum/listOfSpecies.getNumSpecies();
        }
        List<String> onts = listOfSpecies.getListOfOntologies();
        for(int j=0; j< listOfSpecies.getNumOntologies(); j++){
            double sum=0;
            for(Species sps: listOfSpecies.getListOfSpecies()) 
                sum += sps.getOntologyAnnotationValue(onts.get(j));
            compv[j+i] = sum/listOfSpecies.getNumSpecies();       
        }
        
        this.finalSpsVal = calculateRatioSum(compv);
        return Arrays.asList(compv);
    }
    
     private List<Double> calculateReactionRatio(){
        Double[] compv = new Double[listOfReactions.getNumDbs()+listOfReactions.getNumOntologies()];
        for(int i=0; i< compv.length; i++) compv[i] =0.0;
        List<String> dbs = listOfReactions.getListOfDBs();
        int i=0;
        for(i=0; i< listOfReactions.getNumDbs(); i++) {
            double sum=0;
            for(Reaction sps: listOfReactions.getListOfReaction())
                sum += sps.getDbAnnotationValue(dbs.get(i));
            compv[i] = sum/listOfReactions.getNumReactions();
        }      
        List<String> onts = listOfReactions.getListOfOntologies();
        for(int j=0; j< listOfReactions.getNumOntologies(); j++){
            double sum=0;
            for(Reaction sps: listOfReactions.getListOfReaction())
                sum += sps.getOntologyAnnotationValue(onts.get(j));
            compv[j+i] = sum/listOfReactions.getNumReactions();;
        }
         System.out.println("i: "+i);
        this.finalReactVal = calculateRatioSum(compv);
         System.out.println("finalReactVal: "+finalReactVal);
        return Arrays.asList(compv);
    }
    
     private double calculateRatioSum(Double[] compv){ 
        double finalCombVal = 0;
        for(int i=0; i< compv.length; i++)
           if (finalCombVal < compv[i] ) finalCombVal =  compv[i];
        return finalCombVal;///(double)compv.length;
    }

    private double calculateRatioSum(Double[] compv, int size){ 
        double finalCombVal = 0;
        for(int i=0; i< compv.length; i++){
           compv[i] = compv[i]/(double)size;
           finalCombVal +=compv[i];
        }
        return finalCombVal;
    }

    public double getFinalValue() {
        this.finalValue = (this.finalCombVal+ this.finalReactVal+ this.finalSpsVal)/3.0;
        return this.finalValue;
    }
   
    public boolean isValid() {
        valid = (finalValue >= thres);
        return valid;
    }
    
}
