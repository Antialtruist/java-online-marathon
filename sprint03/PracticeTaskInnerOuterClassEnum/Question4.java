package sprint03.PracticeTaskInnerOuterClassEnum;

//	Create a LineType enum that contains different kind of lines as constants SOLID, DOTTED, DASHED and DOUBLE.
//	Write a method that takes the constant of LineType type as input, and returns the message “The line is <LineType> type”.
//	Where <LineType>  is name of type wrote in lower case.
//	For example: The line is dotted type.

enum LineType { SOLID, DOTTED, DASHED, DOUBLE }

public static String drawLine(LineType lineType) {
    String result = String.format("The line is %s type", lineType.name().toLowerCase());
    return result;
}