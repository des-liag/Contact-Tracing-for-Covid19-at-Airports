package Airports;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Mymap class create some methods thet are necessary for creation of HashMap
 */
public class Mymap<T,E> extends HashMap<String[], E> {

    /**
     * Check if the first of the two map's key is the key that is given
     * @param key1 String containing the key that we search for
     * @return boolean true if map contains this key, otherwise return false
     */
    public boolean containsKey1(String key1) {
        ArrayList<String[]> keysList = new ArrayList<String[]>(this.keySet());
        for( String[] key : keysList){
            if(key[0].equals(key1)){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the second of the two map's key is the key that is given
     * @param key2 String containing the key that we search for
     * @return boolean true if map contains this key, otherwise return false
     */
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

    public ArrayList<E> getValuesArrayListByKey1and2(String key1, String key2){
        ArrayList<E> valuesList = new ArrayList<E>();
        for( String[] key : this.keySet()){
            if(key[0].equals(key1) & key[1].equals(key2)){
                valuesList.add(this.get(key));
            }
        }
        return valuesList;
    }

}
