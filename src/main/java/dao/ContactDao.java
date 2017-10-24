package dao;

import model.Number;
import model.Person;
import service.Service;
import util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class ContactDao {

    private static ContactDao INSTANCE = new ContactDao();

    Service service = Service.getINSTANCE();

    private static String personTableName = "person";
    private static String numberTableName = "number";

    private static ConnectionUtil connectionUtil = new ConnectionUtil();




    public static ContactDao getINSTANCE() {
        return INSTANCE;
    }

    public void saveContactsInDb(ArrayList<Person> personArrayList) throws SQLException {
        //save in database
        Connection conn= connectionUtil.createConnection();
        savePersons(personArrayList);
        conn.commit();
        connectionUtil.shutdown();
    }

    public void getPersons() {
        ArrayList<Person> personArrayList = new ArrayList<>();
        Connection conn= connectionUtil.createConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * FROM " + personTableName);
            stmt.close();

        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        connectionUtil.shutdown();

        service.showPersons(personArrayList);
    }

    public int savePerson(Person person) throws SQLException {

        Connection conn= connectionUtil.createConnection();
        Statement stmt = conn.createStatement();

        String sql = "INSERT INTO " + personTableName + " (NAME, FAMILY) values(?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, person.getName());
        statement.setString(2, person.getFamily());

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }


    public void savePersons(ArrayList<Person> personArrayList) throws SQLException {
        for (int i = 0; i < personArrayList.size(); i++) {
            int personId = savePerson(personArrayList.get(i));
            saveNumbers(personId, personArrayList.get(i).getNumbers());
        }
    }

    public void saveNumbers(int personId, ArrayList<Number> numbers) throws SQLException {
        for (int i = 0; i < numbers.size(); i++) {
            saveNumber(personId, numbers.get(i));
        }
    }

    public void saveNumber(int personId, Number number) throws SQLException {
        Connection conn= connectionUtil.createConnection();
        Statement stmt = conn.createStatement();
        String sql = "INSERT INTO " + numberTableName + " (NUMBER, TYPE, PERSON_ID ) values(?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, number.getNum());
        statement.setString(2, String.valueOf(number.getPhoneNumberEnum()));
        statement.setString(3, String.valueOf(personId));

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

    }




}
