package com.example.restapi.dao;

import com.example.restapi.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.example.restapi.util.HibernateUtil;

import java.util.List;

public class StudentDao {
    public static List<Student> getAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<Student> students = session.createQuery(" from Student ").list();
            session.getTransaction().commit();
            return students;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    public static boolean insert(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
        }
        return false;
    }

    public static boolean update(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
        }
        return false;
    }

    public static boolean remove(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Student student = session.load(Student.class, id);
            session.delete(student);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
        }
        return false;
    }

    public static List<Student> cmSinhNhat() {
        String query = "select *\n" +
                "from student\n" +
                "where TO_CHAR( birthday, 'MM-DD')=(select (to_char(sysdate, 'MM-DD')) current_year from dual)";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Student> students = session.createSQLQuery(query).list();
            session.getTransaction().commit();
            return students;
        } catch (HibernateException e) {
        }
        return null;
    }

    //    public static List<Student> search(String name, String classname, String major, String hometown, String gender){
    public static List<Student> search(String name, String hometown, String gender, String classname, String major, List<Integer> mark, List<Integer> birthday) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            String query = "select * from student where 1=1";

            if (name != null) {
                query = query + " and name like '%" + name + "%'";
            }

            if (hometown != null) {
                query = query + " and hometown ='" + hometown + "'";
            }
            if (gender != null) {
                query = query + " and gender like '%" + gender + "%'";
            }

            if (classname != null) {
                query = query + " and classname like '%" + classname + "%'";
            }

            if (major != null) {
                query = query + " and major='" + major + "'";
            }

            if (mark.size() == 2) {
                int a = mark.get(0);
                int b = mark.get(1);
                query = query + " and mark between " + a + " and " + b;
            }
            if(birthday.size()==2){
                query = query + " and TO_Number(TO_CHAR( birthday, 'DD')) between  " + birthday.get(0) + " and " + birthday.get(1);
            }

            System.out.println(query);
            List<Student> students = session.createSQLQuery(query).list();
            session.getTransaction().commit();
            return students;

        } catch (HibernateException e) {
        }
        return null;
    }

}
