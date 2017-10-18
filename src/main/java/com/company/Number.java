package com.company;

import java.util.Scanner;

public class Number {
    private String num;
    private com.company.NumTps numTps;
    private Scanner scanner=new Scanner(System.in);

    public Number(){
        manegeNumbersType();
    }
    public Number(String value, com.company.NumTps type) {
        this.num = value;
        this.numTps = type;
    }

    public Number manegeNumbersType(){
        System.out.println("Number: ");
        num = scanner.nextLine();
        System.out.println("enter the type : (home/work/mobile)");
        numTps= NumTps.valueOf(scanner.nextLine().toUpperCase());
        return new Number(num,numTps);
    }


    @Override
    public String toString() {
        return "Number{" +
                "type='" + numTps +
                ", number=" + num +
                '}';
    }
}
