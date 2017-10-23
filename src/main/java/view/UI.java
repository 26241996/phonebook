package view;

import com.company.Number;
import com.company.Person;
import controler.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    private static UI INSTANCE = new UI();

    Service service = Service.getINSTANCE();

    Scanner scanner = new Scanner(System.in);

    public static UI getINSTANCE(){
        return INSTANCE;
    }

    private int getCount(){
        System.out.println("Please insert the count of persons you want to add in your contact: ");
//        Scanner scanner=new Scanner(System.in);
        int count =scanner.nextInt();
        return count;
    }

    public void showMenu() throws SQLException {
        System.out.println("show menu\n1.insert Contacts");
        int input = scanner.nextInt();
        if (input==1){
            getContactsFromUserToSave();
        }
    }






    public void getContactsFromUserToSave() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Person> list = new ArrayList<>();
        int count = getCount();
        String flag="yes";

        while (count>0){
            System.out.println("Name: ");
            String name = scanner.nextLine();
            System.out.println("Family: ");
            String family = scanner.nextLine();
            Person person =new Person(name,family);
            while (flag.equals("yes")) {

                person.setNumber(new Number());
                System.out.println("do you need to add more number??? , yes/no");
                flag=scanner.nextLine();
            }
            flag="yes";
            list.add(person);
            count--;
        }
        service.savePersons(list);
    }

    public void showSavedPersons(ArrayList<Person> personArrayList){
        for (int i=0;i<personArrayList.size();i++){
            System.out.println(personArrayList.get(i).getName());
            System.out.println(personArrayList.get(i).getFamily());

            for (int j=0;j<personArrayList.get(i).getNumbers().size();j++){
                System.out.println(personArrayList.get(i).getNumbers().get(j).getNum());
                System.out.println(personArrayList.get(i).getNumbers().get(j).getNumTps());
            }
        }
    }
}
