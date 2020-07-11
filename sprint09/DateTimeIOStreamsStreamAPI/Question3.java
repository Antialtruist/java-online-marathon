package sprint09.DateTimeIOStreamsStreamAPI;

//	Create the method writeFile(String filename, String text) that write the text to file as sequence of bytes in binary format.
//	
//	For example, the text fragment
//				Hello!
//	
//	should be represented in the file as a sequence of 7-bit bytes without spaces between them:
//	        100100011001011101100110110011011110100001
//	
//	If less than 7 bits are required to represent the character then add to binary sequence leading zeros '0'.

import java.io.FileWriter;
import java.io.IOException;

class Question3 {

	public static void writeFile(String filename, String text) {

		byte[] bytes = text.getBytes();
		String str = "";
		for (byte b : bytes) {
			str += String.format("%7s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
		}

		try (FileWriter fileWriter = new FileWriter(filename)) {
			fileWriter.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}