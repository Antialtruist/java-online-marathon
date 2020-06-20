package sprint03.PracticeTaskInnerOuterClassEnum;

//	Create public inner class named Iterator inside NameList class that correspond the next class diagram:

class NameList {
    private String[] names = {"Mike", "Emily", "Nick", "Patric", "Sara"};

    public Iterator getIterator() {
        return new Iterator();
    }

    public class Iterator {
        
        private int counter = 0;

        private Iterator() {
        }

        public boolean hasNext() {
            return counter < names.length && names[counter] != null;
        }

        public String next() {
            return names[counter++];
        }
    }
}