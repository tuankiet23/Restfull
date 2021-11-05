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
        // Session session = HibernateUtil.getSessionFactory().openSession();
        // try {
        //     session.beginTransaction();

        //     String query = "select * from student where 1=1";

        //     if (name != null) {
        //         query = query + " and name like '%" + name + "%'";
        //     }

        //     if (hometown != null) {
        //         query = query + " and hometown ='" + hometown + "'";
        //     }
        //     if (gender != null) {
        //         query = query + " and gender like '%" + gender + "%'";
        //     }

        //     if (classname != null) {
        //         query = query + " and classname like '%" + classname + "%'";
        //     }

        //     if (major != null) {
        //         query = query + " and major='" + major + "'";
        //     }

        //     if (mark.size() == 2) {
        //         int a = mark.get(0);
        //         int b = mark.get(1);
        //         query = query + " and mark between " + a + " and " + b;
        //     }
        //     if(birthday.size()==2){
        //         query = query + " and TO_Number(TO_CHAR( birthday, 'DD')) between  " + birthday.get(0) + " and " + birthday.get(1);
        //     }

        //     System.out.println(query);
        //     List<Student> students = session.createSQLQuery(query).list();
        //     session.getTransaction().commit();
        //     return students;

        // } catch (HibernateException e) {
        // }
        // return null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        session.beginTransaction();
        String sql="select * from student where 1=1";
        if (name != null) {
            sql = sql + " and name like (:p_student_name)";
        }

        if (hometown != null) {
            sql = sql + " and hometown like (:p_student_hometown)";
        }
        if (gender != null) {
            sql = sql + " and gender like (:p_student_gender)";
        }
        if (classname != null) {
            sql = sql + " and classname like (:p_student_classname)";
        }
        if (major != null) {
            sql = sql + " and major like (:p_student_major)";
        }
        if (mark.size() == 2) {
             sql = sql + " and mark between (:p_student_mark1) and (:p_student_mark1) ";
        }
        if(birthday.size()==2){
            sql = sql + " and TO_Number(TO_CHAR( birthday, 'DD')) between (:p_student_day1) and (:p_student_day2) ";
        }
        Query<Student> query=  session.createSQLQuery(sql);


        if (name != null) {
            query.setParameter("p_student_name", name);
        }

        if (hometown != null) {
            query.setParameter("p_student_hometown", hometown);
        }
        if (gender != null) {
            query.setParameter("p_student_gender", gender);
        }

        if (classname != null) {
            query.setParameter("p_student_classname", classname);
        }

        if (major != null) {
            query.setParameter("p_student_major", major);
        }

        if (mark.size() == 2) {

            query.setParameter("p_student_mark1", mark.get(0));
            query.setParameter("p_student_mark2", mark.get(1));
           }
        if(birthday.size()==2){
              query.setParameter("p_student_day1", birthday.get(0));
                query.setParameter("p_student_day2", birthday.get(1));
            }

        List<Student> students=query.list();
            session.getTransaction().commit();
            return students;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

}
