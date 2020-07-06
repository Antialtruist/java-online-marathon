package sprint07.Annotation;

//	Create single-value annotation TestSuite with default element that returns array of strings.
//	Create class TestSuitHandler with static method run(Class clazz).
//	This method invokes all public non-static zero args methods.
//	This method print to console information about all executed methods in form:
//	  -- Method <class>.<methodname> started --
//	<result of  methodname invocation>
//	 -- Method <class><methodname> finished --
//	(before -- should printed tab character)
//	If clazz doesn't contain the <methodName> defined in annotation information Method with name <methodName> doesn't exists or not public in class clazz should be displayed.
//	If clazz is not marked with TestSuite annotation message Class clazz isn't annotated  should be displayed.
//	For example
//	We have
//	@TestSuite({"m1", "m2"})
//	class Class1{
//	 public void m2(){
//	   System.out.println("Hello");
//	 }
//	}
//	
//	We run 
//	TestSuiteHandler.run(Class1.class);
//	
//	As a result we have
//	Method with name m1 doesn't exists or not public in class Class1
//		 -- Method Class1.method2 started--
//	Hello
//		 -- Method Class1.method2 finished--

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class TestSuitHandler {
	public static void run(Class<?> cls) {
		if(!cls.isAnnotationPresent(TestSuite.class)) {
			System.out.printf("Class %s isn't annotated%n", cls.getName());
			return;
		}
		
		Object instance = null;
		
		TestSuite annotation = cls.getAnnotation(TestSuite.class);
		for(String methodName : annotation.value()) {
			Method method = null;
			try {
				method = cls.getDeclaredMethod(methodName);
				int modifiers = method.getModifiers();
				if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers)) {
					throw new NoSuchMethodException();
				}
			} catch (NoSuchMethodException e) {
				System.out.printf("Method with name %s doesn't exists or not public in class %s%n", methodName, cls.getName());
				continue;
			}
			
			if (instance == null) {
				try {
					Constructor<?> constructor = cls.getDeclaredConstructor();
					constructor.setAccessible(true);
					instance = constructor.newInstance();
				} catch (Exception e) {
					System.out.println("Can't create an instance of " + cls.getName());
					return;
				}
			}
			
			System.out.printf("\t -- Method %s.%s started --%n", cls.getName(), method.getName());
			try {
				method.invoke(instance);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			System.out.printf("\t -- Method %s.%s finished --%n", cls.getName(), method.getName());
		}
	}
}