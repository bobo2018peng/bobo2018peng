package it.bobo.cn;

public class Student {
private int age;
private int num;
private String name;
public Student() {
	super();
	// TODO Auto-generated constructor stub
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
public String toString() {
	return "Student [age=" + age + ", num=" + num + ", name=" + name + "]";
}

}
