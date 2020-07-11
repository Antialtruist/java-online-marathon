package sprint09.DateTimeIOStreamsStreamAPI;

//	Create the method readFile(String filename) that read from file a sequence of bytes in binary format from previous task and return ridable string.
//	
//	For example, the sequence of 7-bit bytes
//			100100011001011101100110110011011110100001
//	
//	should be represented as text fragment:
//			Hello!

class Question4 {
	public static String readFile(String filename) {

		String result = "";

		try (java.io.FileReader reader = new java.io.FileReader(filename)) {
			int i = 0;
			String character = "";
			while ((i = reader.read()) != -1) {
				character += (char) i;
				if (character.length() == 7) {
					int charCode = Integer.parseInt(character, 2);
					result += (char) charCode;
					character = "";
				}
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}