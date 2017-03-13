import java.util.*;
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovModel extends AbstractMarkovModel{
    private int pass;
    
    public MarkovModel(int passed) {
        myRandom = new Random();
        pass=passed;
    }
    
    /*public void setRandom(int seed){
        myRandom = new Random(seed);
    }*/
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index=myRandom.nextInt(myText.length()-pass);
        String key=myText.substring(index,index+pass);
        sb.append(key);
        for(int k=0;k <numChars-pass;k++){
            ArrayList<String> follows=getFollows(key);
            if(follows.size()==0){
                break;
            }
            index=myRandom.nextInt(follows.size());
            String next=follows.get(index);
            sb.append(next);
            key=key.substring(1)+next;
        }
        return sb.toString();
    }
    
    
    public String toString(){
       return "Markov model of order "+pass; 
    }
}
