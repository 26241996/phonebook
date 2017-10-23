package model;

import com.company.Number;
import com.company.Person;
import controler.Service;

import java.sql.*;
import java.util.ArrayList;

public class Model {

    private static Model INSTANCE = new Model();

    Service service = Service.getINSTANCE();

    private static String dbURL = "jdbc:derby:db;create=true";
    private static String personTableName = "person";
    private static String numberTableName = "number";

    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;


    public static Model getINSTANCE() {
        return INSTANCE;
    }

   public void saveContactsInDb(ArrayList<Person> personArrayList) throws SQLException{
        //save in database
       createConnection();
      savePersons(personArrayList);
      conn.commit();
       shutdown();
    }

    public void getPersons(){
       ArrayList<Person> personArrayList = new ArrayList<>();
       createConnection();
        try
        {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * FROM " + personTableName);
            stmt.close();

        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
       shutdown();

        service.showPersons(personArrayList);
    }

    public int savePerson(Person person) throws SQLException{


            stmt = conn.createStatement();

            String sql = "INSERT INTO " + personTableName + " (NAME, FAMILY) values(?, ?)";

            PreparedStatement statement =conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, person.getName());
            statement.setString(2, person.getFamily());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
    }


    public void savePersons(ArrayList<Person> personArrayList) throws SQLException{
        for (int i =0;i<personArrayList.size();i++){
            int personId=savePerson(personArrayList.get(i));
            saveNumbers(personId,personArrayList.get(i).getNumbers());
        }
    }

    public void saveNumbers(int personId ,ArrayList<Number> numbers) throws SQLException{
         for (int i=0;i<numbers.size();i++){
             saveNumber(personId,numbers.get(i));
         }
    }

    public void saveNumber(int personId,Number number) throws SQLException{

            stmt = conn.createStatement();
        String sql = "INSERT INTO " + numberTableName + " (NUMBER, TYPE, PERSON_ID ) values(?, ?, ?)";

        PreparedStatement statement =conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, number.getNum());
        statement.setString(2, String.valueOf(number.getNumTps()));
        statement.setString(3, String.valueOf(personId));

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

        }


    private static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL);
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }

    private static void shutdown() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }
        } catch (SQLException sqlExcept) {
        }
    }

}
