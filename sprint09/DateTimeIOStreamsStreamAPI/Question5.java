package sprint09.DateTimeIOStreamsStreamAPI;

//	Let the key of Map is project name and value contains list of participants.
//	Create a Stream<String> nameList(Map<String, Stream<String>> map) method of the MyUtils class to build sorted stream of all participants without duplication.
//	Please ignore null or empty strings, extra spaces and case sensitivity.
//	Throw NullPointerException if map is null.
//	For example, for a given map
//	{"Desktop"=[" iVan", "PeTro ", " Ira "], "Web"=["STepan", "ira ", " Andriy ", "an na"], "Spring"=["Ivan", "Anna"]}
//	you should get
//	["Andriy", "Anna", "Ira", "Ivan", "Petro ", "Stepan"]

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public class MyUtils {
	public Stream<String> nameList(Map<String, Stream<String>> map) {
		if (map == null) {
			throw new NullPointerException();
		}

		return map.values().stream().flatMap(Function.identity()).filter(Objects::nonNull)
				.map(str -> str.replace(" ", "")).filter(str -> !str.isEmpty()).map(String::toLowerCase).distinct()
				.map(str -> str.substring(0, 1).toUpperCase() + str.substring(1)).sorted();
	}
}