package it.bobo.com;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Test {
public static void main(String[] args) {
	List list=new ArrayList<>();
	list.add(new Person(18,"bobo",1));
	list.add(new Person(32,"xixi",2));
	list.add(new Person(45,"haha",8));
	list.add(new Person(76,"tete",6));
	list.add(new Person(22,"dodo",9));
	list.add(new Person(45,"nana",7));
	list.add(new Person(50,"lili",5));
	Collections.sort(list);
	System.out.println(list);
}
}
