import java.util.*;
import edu.duke.*;
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovModel2 extends AbstractMarkovModel{
    private int pass;
    private HashMap<String,ArrayList<String>> map;
    
    public EfficientMarkovModel2(int passed) {
        myRandom = new Random();
        pass=passed;
        map=new HashMap<String,ArrayList<String>>();
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
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
            if(follows==null||follows.size()==0){
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
       return "EfficientMarkovModel class of a specific number "+pass; 
    }
    
    public void buildMap(){
        for(int k=0;k<=myText.length()-pass;++k){
            String key=myText.substring(k,k+pass);
            if(!map.containsKey(key)){
                ArrayList<String> follows=new ArrayList<String>();
                /*for(int i=0;i<myText.length()-key.length();++i){
                    if(key.equals(myText.substring(i,i+key.length()))){
                        follows.add(myText.substring(i+key.length(),i+key.length()+1));
                    }
                }*/
                int pos=0;
                while(pos<myText.length()){
                    int start=myText.indexOf(key,pos);
                    if(start==-1){
                        break;
                    }
                    if(start+key.length()>=myText.length()-1){
                        break;
                    }
                    String next=myText.substring(start+key.length(),start+key.length()+1);
                    follows.add(next);
                    pos=start+key.length();
                }
                map.put(key,follows);
            }
        }
        printHashMapInfo();
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> follow=map.get(key);
        return follow;
    }
    
    public void printHashMapInfo(){
        /*for(String s:map.keySet()){
            System.out.println(s+":"+map.get(s));
        }*/
        System.out.println("no of keys: "+map.size());
        int max=0;
        ArrayList<String> maxKey=new ArrayList<String>();
        for(String s:map.keySet()){
            ArrayList<String> al=map.get(s);
            if(al.size()>=max){
                max=al.size();
            }
        }
        for(String s:map.keySet()){
            ArrayList<String> al=map.get(s);
            if(al.size()==max){
                maxKey.add(s);
            }
        }
        System.out.println("size of largest: "+max);
        System.out.println("keys of max size:\n"+maxKey);
        map.clear();
    }
    
}
