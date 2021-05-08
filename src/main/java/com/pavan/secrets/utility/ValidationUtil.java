package com.pavan.secrets.utility;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class ValidationUtil {


    /** This method returns true if the collection is null or empty
     * @param collection
     * @return true | false
     */
    public static boolean isEmpty(Collection<?> collection){
        return collection == null || collection.isEmpty();
    }

    /** This method returns true of the map is null or empty
     * @param map
     * @return true | false
     */

    public static boolean isEmpty(Map<?,?> map){
        return map == null || map.isEmpty();
    }

    /** This method returns true if the object is null
     * @param object
     * @return true | false
     */

    public static boolean isEmpty(Object object){
        return object == null;
    }

    /** This method returns true if the input array is null or its length is zero
     * @param array
     * @return true | false
     */

    public static boolean isEmpty(Object[] array){
        return array == null || array.length == 0;
    }


    /** This method returns true if the input string is null or its length is zero
     * @param string
     * @return true | false
     */

    public static boolean isEmpty(String string){
        return string == null || string.trim().length() == 0;
    }

    /** This method returns true if the source string contains the other string
     * @param sourceString,subString
     * @return true | false
     */

    public static boolean containIgnoreCase(String sourceString,String subString){

        return sourceString.toLowerCase().contains(subString.toLowerCase());
    }

}
