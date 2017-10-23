package view;

import com.company.Contact;
import com.company.Number;
import com.company.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException {

        UI ui = UI.getINSTANCE();
        ui.showMenu();
    }


}

