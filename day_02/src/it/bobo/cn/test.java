package it.bobo.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class test {
	 public static void main(String[] args) {
      List<Student> stus = new ArrayList<>();
      Student stu1 = new Student();
      Student stu2 = new Student();
      Student stu3 = new Student();
      Student stu4 = new Student();
      Student stu5 = new Student();
      Student stu6 = new Student();
      stu1.setAge(30);
      stu2.setAge(20);
      stu3.setAge(40);
      stu4.setAge(30);
      stu5.setAge(40);
      stu6.setAge(20);

      stu1.setNum(1);
      stu2.setNum(2);
      stu3.setNum(3);
      stu4.setNum(4);
      stu5.setNum(5);
      stu6.setNum(6);

      stu1.setName("bobo");
      stu2.setName("dede");
      stu3.setName("lili");
      stu4.setName("toto");
      stu5.setName("xixi");
      stu6.setName("haha");

      stus.add(stu1);
      stus.add(stu2);
      stus.add(stu3);
      stus.add(stu4);
      stus.add(stu5);
      stus.add(stu6);
      Collections.sort(stus,new Comparator<Student>() {

          @Override
          public int compare(Student s1, Student s2) {
             int flag = s1.getAge()-s2.getAge();
              if(flag==0){
                  flag = s1.getNum()-s2.getNum();
              }
              return flag;
          }
      });
      System.out.println("年龄       学号       姓名  ");
      for(Student s : stus){
          System.out.println(s.getAge()+"   "+s.getNum()+"   "+s.getName());
          }
        }
	 }