import bean.Student;
import dao.StudentDAO;
import daoIMP.StudentDAOIMP;

import java.sql.SQLOutput;
import java.util.List;

public class MainStarter {
    public static void main(String[] args){
        StudentDAO studentDAO = new StudentDAOIMP();

        System.out.println("findAll: ");
        List<Student> stuList = studentDAO.findAll();
        for(Student s: stuList){
            System.out.println(s.toString());
        }
        System.out.println("findById: " + studentDAO.findByID(1).toString());
        System.out.println("insert...");
        studentDAO.insert(new Student("伍六七"));
        System.out.println("insert done!");

        System.out.println("update...");
        studentDAO.update(new Student(3, "伊尔三"));
        System.out.println("update done!");

        System.out.println("delete...");
        studentDAO.delete(2);
        System.out.println("delete done!");
    }
}
