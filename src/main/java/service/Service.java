package service;

import model.Person;
import dao.ContactDao;
import view.UI;

import java.sql.SQLException;
import java.util.ArrayList;

public class Service {

    private UI mView = UI.getINSTANCE();
    private ContactDao mContactDao = ContactDao.getINSTANCE();

    private static Service INSTANCE = new Service();

    public static Service getINSTANCE() {
        return INSTANCE;
    }

    public void getContacts() {

    }

    public void savePersons(ArrayList<Person> personArrayList) throws SQLException {
        mContactDao.saveContactsInDb(personArrayList);
    }

    public void showPersons(ArrayList<Person> personArrayList) {
        mView.showSavedPersons(personArrayList);
    }
}
