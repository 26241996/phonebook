package com.company;

import java.util.ArrayList;

public class Person {
    private String name;
    private String family;
    private ArrayList<com.company.Number> number;

    public Person(String name, String family) {
        this.name = name;
        this.family = family;
        this.number =new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", number=" + viewNum() +
                '}';
    }

    public String viewNum() {
        StringBuilder numbers=new StringBuilder();
        for (com.company.Number number : this.number) {
            numbers.append(number.toString()+ "\n");
            System.out.println("                   ");
        }
        return numbers.toString() ;
    }


    public void setNumber(com.company.Number number) {
        this.number.add(number);
    }

    public ArrayList<Number> getNumbers() {
        return number;
    }

    public String getName() {

        return name;
    }

    public String getFamily() {
        return family;
    }


}