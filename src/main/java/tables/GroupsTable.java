package tables;

import db.MySQLConnector;
import objects.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GroupsTable extends AbsTable {
    private final static String TABLE_NAME = "studentGroups";

    public GroupsTable() {
        super(TABLE_NAME);
        columns = new HashMap<>();
        columns.put("id", "bigint");
        columns.put("name", "varchar(100)");
        columns.put("id_curator", "bigint");
        create();
    }

    public ArrayList<Group> selectAll() {
        db = new MySQLConnector();
        String sqlRequest = String.format("SELECT * FROM %s", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        return resultSetToArray(rs);
    }

    public void insertGroups(Group group) {
        db = new MySQLConnector();
        String sqlRequest = String.format("INSERT INTO %s (id, name, id_curator) " + "VALUES ('%d', '%s', '%d')",
                tableName, group.getId(), group.getName(), group.getId_curator());
        db.executeRequest(sqlRequest);
    }

    private ArrayList<Group> resultSetToArray(ResultSet rs){
        ArrayList<Group> result = new ArrayList<>();
        //Обработать ответ по строчно
        try {
            // Перебор строк с данными
            while (rs.next()) {
                //Создать объект и добавление его в результирующий массив
                result.add(
                        new Group (
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getLong("id_curator")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public void updateGroup(int id_curator){
        db = new MySQLConnector();
        String sqlRequest = String.format("UPDATE %s SET id_curator = %d where id = 333", tableName, id_curator);
        db.executeRequest(sqlRequest);
    }

}