package controller;

import model.PhoneNumberEnum;
import model.Number;
import model.Person;

import java.util.ArrayList;
import java.util.Scanner;

public class ContactController {
    private static int count;
    private ArrayList<Person> list = new ArrayList<Person>();
    private String name;
    private String family;
    private Scanner scanner = new Scanner(System.in);
    private Scanner scannerInt = new Scanner(System.in);
    private Person person;
    private boolean reAdd = false;

    public Number manegeNumbersType() {
        System.out.println("Number: ");
        String num = scanner.nextLine();
        System.out.println("enter the type : (home/work/mobile)");
        PhoneNumberEnum phoneNumberEnum = PhoneNumberEnum.valueOf(scanner.nextLine().toUpperCase());
        return new Number(num, phoneNumberEnum);
    }


    public void getCount() {
        System.out.println("Please insert the count of persons you want to add in your contact: ");
        Scanner scanner = new Scanner(System.in);
        count = scanner.nextInt();
    }



//    public ContactController() {
//        getCount();
//        getInfo();
//        view();
//    }

//    private void getInfo() {
//
//        String flag = "yes";
//
//        while (count > 0 || reAdd) {
//            System.out.println("Name: ");
//            name = scanner.nextLine();
//            System.out.println("Family: ");
//            family = scanner.nextLine();
//            person = new Person(name, family);
//            while (flag.equals("yes")) {
//                person.setNumber(manegeNumbersType());
//                System.out.println("do you need to add more number??? , yes/no");
//                flag = scanner.nextLine();
//            }
//            flag = "yes";
//            list.add(person);
//            count--;
//        }
//    }


//    public void view() {
//        String searchName;
//        String searchFamily;
//        String yesOrNo = "yes";
//        while (yesOrNo.equals("yes")) {
//            System.out.println("enter the name you want to see its number");
//            searchName = scanner.nextLine();
//            System.out.println("enter the family ");
//            searchFamily = scanner.nextLine();
//            for (Person person : list) {
//                if (person == null) {
//                    System.out.println("error");
//                    return;
//                }
//                if (person.getName().equalsIgnoreCase(searchName) && person.getFamily().equalsIgnoreCase(searchFamily)) {
//                    System.out.println(person.toString());
//                    System.out.println("do you need to add any number to " + person.getFamily() + "? yes/no");
//                    if (scanner.nextLine().equals("yes")) {
//                        person.setNumber(manegeNumbersType());
//                    }
//                }
//            }
//            System.out.println("do you want to continue? yes/no/add");
//            yesOrNo = scanner.nextLine();
//            if (yesOrNo.equals("add")) {
//                reAdd = true;
//                getInfo();
//                yesOrNo = "yes";
//            }
//        }
//    }

}
