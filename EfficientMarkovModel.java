import java.util.*;
import edu.duke.*;
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovModel extends AbstractMarkovModel{
    private int pass;
    private HashMap<String,ArrayList<String>> map;
    
    public EfficientMarkovModel(int passed) {
        myRandom = new Random();
        pass=passed;
        map=new HashMap<String,ArrayList<String>>();
    }
    
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
            printHashMapInfo();
        return sb.toString();
    }
    
    public String toString(){
       return "EfficientMarkovModel class of a specific number "+pass; 
    }
    
    public HashMap<String,ArrayList<String>> buildMap(String key){
        if(!map.containsKey(key)){
            ArrayList<String> follows=new ArrayList<String>();
            for(int i=0;i<myText.length()-key.length();++i){
                if(key.equals(myText.substring(i,i+key.length()))){
                    follows.add(myText.substring(i+key.length(),i+key.length()+1));
                }
            }
            //duke code
            /*int pos=0;
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
            }*/
            //System.out.println("if\n"+key+":"+follow);
            map.put(key,follows);
        }
        /*else{
            ArrayList<String> follow=map.get(key);
            for(int i=0;i<myText.length()-key.length();++i){
                if(key.equals(myText.substring(i,i+key.length()))){
                    follow.add(myText.substring(i+key.length(),i+key.length()+1));
                }
            }
            //System.out.println("else\n"+key+":"+follow);
            map.put(key,follow);
        }*/
        return map;
    }
    
    public ArrayList<String> getFollows(String key){
        HashMap<String,ArrayList<String>> map=buildMap(key);
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
                maxKey.add(s);
            }
        }
        System.out.println("size of largest: "+max);
        System.out.println("keys of max size:\n"+maxKey);
        //map.clear();
    }
    
}
