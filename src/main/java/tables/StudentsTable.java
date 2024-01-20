package tables;

import db.MySQLConnector;
import objects.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentsTable extends AbsTable {
    private final static String TABLE_NAME = "students";

    public StudentsTable() {
        super(TABLE_NAME);
        columns = new HashMap<>();
        columns.put("id", "bigint PRIMARY KEY AUTO_INCREMENT");
        columns.put("fio", "varchar(100)");
        columns.put("sex", "varchar(10)");
        columns.put("id_group", "bigint");
        create();
    }

      public void insertStudents(Student student) {
        db = new MySQLConnector();
        String sqlRequest = String.format("INSERT INTO %s (fio, sex, id_group) " + "VALUES ('%s', '%s', '%d')",
                tableName, student.getFio(), student.getSex(), student.getId_group());
        db.executeRequest(sqlRequest);
    }

    public ArrayList<Student> selectAll() {
        db = new MySQLConnector();
        String sqlRequest = String.format("SELECT * FROM %s", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        return resultSetToArray(rs);
    }

    private ArrayList<Student> resultSetToArray(ResultSet rs){
        ArrayList<Student> result = new ArrayList<>();
        //Обработать ответ по строчно
        try {
            // Перебор строк с данными
            while (rs.next()) {
                //Создать объект и добавление его в результирующий массив
                result.add(
                        new Student(
                                rs.getLong("id"),
                                rs.getString("fio"),
                                rs.getString("sex"),
                                rs.getLong("id_group")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public void showAllStudents(String studentsTableName, String groupsTableTableName, String curatorsTableTableName) {
        try {
            MySQLConnector db = new MySQLConnector();
            final String sqlRequest = String.format("SELECT s.fio, s.sex, g.name, c.fio " +
                            "from %s s left join %s g on s.id_group = g.id left join %s c on g.id_curator = c.id order by s.fio;",
                    studentsTableName,
                    groupsTableTableName,
                    curatorsTableTableName);
            ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
            while (rs.next()) {
                System.out.printf("%s | %s | %s | %s%n",
                        rs.getString("s.fio"),
                        rs.getString("s.sex"),
                        rs.getString("g.name"),
                        rs.getString("c.fio"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void countOfStudents() {
        try {
        db = new MySQLConnector();
        String sqlRequest = String.format("SELECT COUNT(id) from %s", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        while (rs.next()) {
            System.out.printf("%s%n",
                    rs.getString("count(id)"));
        }
    } catch (Exception e) { e.printStackTrace(); }
}

    public void showStudentsGirls(String sex) {
        try {
            MySQLConnector db = new MySQLConnector();
            String sqlRequest = String.format("SELECT fio FROM %s where sex = '%s' order by fio", tableName, sex);
            ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
            while (rs.next()) {
                System.out.printf("%s%n",
                        rs.getString("fio"));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void studentsByGroup (String name) {
        try {
            MySQLConnector db = new MySQLConnector();
            String sqlRequest = String.format("SELECT fio from %s where id_group = (select id from studentGroups" +
                    " where name = '%s') order by fio", tableName, name);
            ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
            while (rs.next()) {
                System.out.printf("%s%n",
                        rs.getString("fio"));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

}
