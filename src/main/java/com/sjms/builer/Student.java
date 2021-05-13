package com.sjms.builer;

public class Student {


    private String name;

    private String address;

    private Integer age;

    public Student(StudentBuilder studentBuilder) {
        this.name = studentBuilder.name;
        this.address = studentBuilder.address;
        this.age = studentBuilder.age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }

    public static class StudentBuilder {

        private String name;

        private String address;

        private Integer age;


        public StudentBuilder setName(String name){
            this.name = name;
            return this;
        }

        public StudentBuilder setAge(Integer age){
            this.age = age;
            return this;
        }

        public StudentBuilder setAddress(String address){
            this.address = address;
            return this;
        }

        public Student build(){
            return new Student(this);
        }

    }

}
