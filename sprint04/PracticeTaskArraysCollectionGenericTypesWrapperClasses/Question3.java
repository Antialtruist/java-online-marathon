package sprint04.PracticeTaskArraysCollectionGenericTypesWrapperClasses;

//	Create a listMapCompare() method of the MyUtils class to compare the contents of a list of strings and the values of a map.
//	For example, for a given list
//	[aa, bb, aa, cc]
//	and map
//	{1=cc, 2=bb, 3=cc, 4=aa, 5=cc}
//	you should get true.

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MyUtils {
    public boolean listMapCompare(List<String> list, Map<String, String> map) {
    	return list.containsAll(map.values());
    }
}