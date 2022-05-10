package org.example;

import org.example.entity.ClazzEntity;
import org.example.entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure("/hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

//        ClazzEntity clazzEntity = new ClazzEntity();
//        clazzEntity.setClassId("1006");
//        clazzEntity.setClassName("计科1班");

//        StudentEntity studentEntity = new StudentEntity();
//        studentEntity.setClassId("1006");
//        studentEntity.setStuId("1910300012");
//
//        studentEntity.setBirthday(new Date(2000 - 1900, 1, 12));
//        studentEntity.setSex("女");
//        studentEntity.setStuName("赵晓璐");

        ClazzEntity clazzEntity = new ClazzEntity();


//        session.save(studentEntity);

        transaction.commit();

        session.close();
        sessionFactory.close();

    }
}
