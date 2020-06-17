//	Create next types: Person (field String name), Student (fields String studyPlace, int studyYears) and 
//	Worker (fields String workPosition, int experienceYears). 
//	Classes Student and Worker are derived from class Person. All classes have getters to return fields.
//			
//	Create a maxDuration() method of the MyUtils class to return a list of Students with maximum duration of study 
//	and Workers with maximum experience.
//	
//	For example, for a given list
//	[Person [name=Ivan], Student [name=Petro, studyPlace=University, studyYears=3], 
//	 Worker [name=Andriy, workPosition=Developer, experienceYears=12], Student [name=Stepan, studyPlace=College, studyYears=4], 
//	 Worker [name=Ira, workPosition=Manager, experienceYears=8], Student [name=Ihor, studyPlace=University, studyYears=4]]
//			 
//	you should get
//	[Worker [name=Andriy, workPosition=Developer, experienceYears=12], Student [name=Stepan, studyPlace=College, studyYears=4], 
//	 Student [name=Ihor, studyPlace=University, studyYears=4]]

import java.util.ArrayList;
import java.util.List;

class Person {
    private String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}

class Student extends Person {
    private String studyPlace;
    private int studyYears;
    
	public Student(String name, String studyPlace, int studyYears) {
		super(name);
		this.studyPlace = studyPlace;
		this.studyYears = studyYears;
	}
	
	public String getStudyPlace() {
		return studyPlace;
	}
	public int getStudyYears() {
		return studyYears;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((studyPlace == null) ? 0 : studyPlace.hashCode());
		result = prime * result + studyYears;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (studyPlace == null) {
			if (other.studyPlace != null)
				return false;
		} else if (!studyPlace.equals(other.studyPlace))
			return false;
		if (studyYears != other.studyYears)
			return false;
		return true;
	}
}

class Worker extends Person {
	private String workPosition;
	private int experienceYears;
	
	public Worker(String name, String workPosition, int experienceYears) {
		super(name);
		this.workPosition = workPosition;
		this.experienceYears = experienceYears;
	}
	
	public String getWorkPosition() {
		return workPosition;
	}
	public int getExperienceYears() {
		return experienceYears;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + experienceYears;
		result = prime * result + ((workPosition == null) ? 0 : workPosition.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Worker other = (Worker) obj;
		if (experienceYears != other.experienceYears)
			return false;
		if (workPosition == null) {
			if (other.workPosition != null)
				return false;
		} else if (!workPosition.equals(other.workPosition))
			return false;
		return true;
	}
}

class MyUtils {
    public List<Person> maxDuration(List<Person> persons) {
    	List<Worker> workers = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        int maxExperience = 0;
        int maxStudyDuration = 0;

        for (Person person : persons){
            if(person instanceof Worker && !workers.contains(person)){
                workers.add((Worker)person);
                if(((Worker) person).getExperienceYears() > maxExperience){
                    maxExperience = ((Worker) person).getExperienceYears();
                }
            }

            if(person instanceof Student && !students.contains(person)){
                students.add((Student)person);
                if(((Student) person).getStudyYears() > maxStudyDuration){
                    maxStudyDuration = ((Student) person).getStudyYears();
                }
            }
        }

        List<Person> result = new ArrayList<>();
        for(Worker worker : workers){
            if(worker.getExperienceYears() == maxExperience) {
                result.add(worker);
            }
        }
        for(Student student : students){
            if(student.getStudyYears() == maxStudyDuration){
                result.add(student);
            }
        }
        return result;
    }
}
