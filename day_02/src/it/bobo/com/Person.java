package it.bobo.com;

public class Person implements Comparable<Person>{
private int age;
private String name;
private int code;

	@Override
public String toString() {
	return "Person [age=" + age + ", name=" + name + ", code=" + code + "]";
}

	public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getCode() {
	return code;
}

public void setCode(int code) {
	this.code = code;
}

	public Person(int age, String name, int code) {
	super();
	this.age=age;
	this.name=name;
	this.code=code;
	
	
	// TODO Auto-generated constructor stub
}

	@Override
	public int compareTo(Person o) {
		if(age==o.getAge())
	
		{
			 return 1;
		
		}else if (code>o.getCode()) {
			 return 0;
		}
		     return -1;
	}

}
