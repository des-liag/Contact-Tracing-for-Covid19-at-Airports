import java.util.ArrayList;
import java.util.HashMap;

public class Mymap<T,E> extends HashMap<String[], E> {
    
    
    public boolean containsKey1(String key1) {
        ArrayList<String[]> keysList = new ArrayList<String[]>(this.keySet());
        for( String[] key : keysList){
            if(key[0].equals(key1)){
                return true;
            }
        }
        return false;
    }
    
    

    public boolean containsKey2(String key2) {
        ArrayList<String[]> keysList = new ArrayList<String[]>(this.keySet());
        for( String[] key : keysList){
            if(key[1].equals(key2)){
                return true;
            }
        }
        return false;
    }

    

    public E getByKey1(String key1) {
        ArrayList<String[]> keysList = new ArrayList<String[]>(this.keySet());
        for( String[] key : keysList){
            if(key[0].equals(key1)){
                return this.get(key);
            }
        }
        return null;
    }

    

    public ArrayList<E> getByKey2(String key2) {
        ArrayList<String[]> keysList = new ArrayList<String[]>(this.keySet());
        ArrayList<E> listE = new ArrayList<E>();
        for( String[] key : keysList){
            if(key[1].equals(key2)){
                listE.add(this.get(key));
            }
        }
        if(listE.size() > 0){
            return listE;
        }
        return null;
    } 

    
    public ArrayList<String[]> getKeysArrayList(){
        return new ArrayList<String[]>(this.keySet());
    }
    
}
