package sprint04.PracticeTaskArraysCollectionGenericTypesWrapperClasses;

//	Create the commonStudents() method of the MyUtils class to return a HashSet of common elements of two student lists.
//	For example, for a given
//	list1 [Students [id=1, name=Ivan], Students [id=2, name=Petro], Students [id=3, name=Stepan]]
//	and
//	list2 [Students [id=1, name=Ivan], Students [id=3, name=Stepan], Students [id=4, name=Andriy]]
//	you should get
//	[Students [id=3, name=Stepan], Students [id=1, name=Ivan]].

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyUtils {
    public static class Student {
        private int id;
        private String name;
        
		public Student(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Student other = (Student) obj;
			if (id != other.id)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
    }
    
    public Set<Student> commonStudents(List<Student> list1, List<Student> list2) {
    	list1.retainAll(list2);
        Set<Student> student = new HashSet<>(list1);
        return student;
    }
}