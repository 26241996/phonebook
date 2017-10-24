package view;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        UI ui = UI.getINSTANCE();
        ui.showMenu();
    }


}

