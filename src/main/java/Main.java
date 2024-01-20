import db.MySQLConnector;
import objects.Curator;
import objects.Group;
import objects.Student;
import tables.CuratorsTable;
import tables.GroupsTable;
import tables.StudentsTable;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        
        try {
            StudentsTable studentsTable = new StudentsTable();
            studentsTable.deleteAll();
            ArrayList<Student> students = studentsTable.selectAll();
            if (students.size() < 15) {
                studentsTable.insertStudents(new Student("Петров Петр Петрович", "муж", 111));
                studentsTable.insertStudents(new Student("Федоров Федор Федорович", "муж", 111));
                studentsTable.insertStudents(new Student("Васильева Василиса Васильевна", "жен", 111));
                studentsTable.insertStudents(new Student("Петрова Петрунья Петровна", "жен", 111));
                studentsTable.insertStudents(new Student("Федорова Федорина Федоровна", "жен", 111));
                studentsTable.insertStudents(new Student("Васильев Василий Васильевич", "муж", 222));
                studentsTable.insertStudents(new Student("Семенов Семен Семенович", "муж", 222));
                studentsTable.insertStudents(new Student("Семенова Семенина Семеновна", "жен", 222));
                studentsTable.insertStudents(new Student("Владиславова Владислава Владиславовна", "жен", 222));
                studentsTable.insertStudents(new Student("Владиславов Владислав Владиславович", "муж", 333));
                studentsTable.insertStudents(new Student("Александров Александр Александрович", "муж", 333));
                studentsTable.insertStudents(new Student("Александрова Александра Александровна", "жен", 333));
                studentsTable.insertStudents(new Student("Алексеев Алексей Алексеевич", "муж", 333));
                studentsTable.insertStudents(new Student("Алексеева Алекса Алексеевна", "жен", 333));
                studentsTable.insertStudents(new Student("Ленин Владимир Ильич", "муж", 333));
           }

            GroupsTable groupsTable = new GroupsTable();
            groupsTable.deleteAll();
            ArrayList<Group> studentGroups = groupsTable.selectAll();
            if (studentGroups.size() < 3) {
                groupsTable.insertGroups(new Group(111, "Группа 1", 11111));
                groupsTable.insertGroups(new Group(222, "Группа 2", 22222));
                groupsTable.insertGroups(new Group(333, "Группа 3", 33333));
            }

            CuratorsTable curatorsTable = new CuratorsTable();
            curatorsTable.deleteAll();
            ArrayList<Curator> curators = curatorsTable.selectAll();
            if (curators.size() < 4) {
                curatorsTable.insertCurators(new Curator(11111, "Первяхин Петр Петрович"));
                curatorsTable.insertCurators(new Curator(22222, "Вторинова Варвара Викторовна"));
                curatorsTable.insertCurators(new Curator(33333, "Третьяков Трофим Теодорович"));
                curatorsTable.insertCurators(new Curator(44444, "Четвертак Чулпан Чеславовна"));
            }

            System.out.println("Информация о всех студентах:");
            studentsTable.showAllStudents(studentsTable.getTableName(), groupsTable.getTableName(), curatorsTable.getTableName());

            System.out.printf("%nКоличество всех студентов: ");
            studentsTable.countOfStudents();

            System.out.printf("%nСписок студенток:%n");
            studentsTable.showStudentsGirls("жен");

            groupsTable.updateGroup(44444);
            System.out.printf("%nУ 3 группы сменился куратор! Новые данные по кураторам:%n");

            groupsTable.showGroupsWithCurators(groupsTable.getTableName(), curatorsTable.getTableName());

            System.out.printf("%nСписок студентов 3 группы:%n");
            studentsTable.studentsByGroup("Группа 3");

        } finally {
            MySQLConnector.close();
        }
        }
    }