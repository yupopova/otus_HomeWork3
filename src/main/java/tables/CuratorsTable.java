package tables;

import db.MySQLConnector;
import objects.Curator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class CuratorsTable extends AbsTable {
    private final static String TABLE_NAME = "curators";

    public CuratorsTable() {
        super(TABLE_NAME);
        columns = new HashMap<>();
        columns.put("id", "bigint");
        columns.put("fio", "varchar(100)");
        create();
    }

    public ArrayList<Curator> selectAll(){
        db = new MySQLConnector();
        String sqlRequest = String.format("SELECT * FROM %s", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        return resultSetToArray(rs);
    }

    public void insertCurators(Curator curator){
        db = new MySQLConnector();
        String sqlRequest = String.format("INSERT INTO %s (id, fio) " + "VALUES ('%d','%s')",
                tableName, curator.getId(), curator.getFio());
        db.executeRequest(sqlRequest);
    }

    private ArrayList<Curator> resultSetToArray(ResultSet rs){
        ArrayList<Curator> result = new ArrayList<>();
        //Обработать ответ по строчно
        try {
            // Перебор строк с данными
            while (rs.next()) {
                //Создать объект и добавление его в результирующий массив
                result.add(
                        new Curator(
                                rs.getLong("id"),
                                rs.getString("fio")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
