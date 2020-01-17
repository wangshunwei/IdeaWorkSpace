package offer;

import java.util.List;

public class Teacher {

    private String name;
    private String sex;

    private List<Student> list;

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Teacher(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

}
