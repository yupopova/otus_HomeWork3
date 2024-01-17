package objects;

public class Student {
    private long id, id_group;
    private String fio, sex;

    public Student (String fio, String sex, long id_group) {
        this.fio = fio;
        this.sex = sex;
        this.id_group = id_group;
    }

    public Student(long id, String fio, String sex, long id_group) {
        this.id = id;
        this.fio = fio;
        this.sex = sex;
        this.id_group = id_group;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", sex='" + sex + '\'' +
                ", id_group=" + id_group +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getId_group() {
        return id_group;
    }

    public void setId_group(long id_group) {
        this.id_group = id_group;
    }
}
