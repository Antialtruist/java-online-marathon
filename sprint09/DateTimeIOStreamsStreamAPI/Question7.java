package sprint09.DateTimeIOStreamsStreamAPI;

//	Create a Stream<Integer> duplicateElements(Stream<Integer> stream) method of the MyUtils class to return a sorted stream of duplicated elements of the input stream.
//	For example, for a given elements
//	[3, 2, 1, 1, 12, 3, 8, 2, 4, 2]
//	you should get
//	[1, 2, 3]

import java.util.stream.Stream;
import java.util.Objects;
import java.util.HashSet;

public class MyUtils {
   public Stream<Integer> duplicateElements(Stream<Integer> stream) {
	   
		HashSet<Integer> elements = new HashSet<>();
		return stream
				.filter(integer -> !elements.add(integer))
				.filter(Objects::nonNull)
				.distinct()
				.sorted();
	}
}