package com.rishu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
public class StudentDAO {
    
    public void addStudent(Student bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addStudent(session,bean);        
        tx.commit();
        session.close();
        
    }
    
    private void addStudent(Session session, Student bean){
        Student student = new Student();
        
        student.setName(bean.getName());
        student.setAge(bean.getAge());
        
        session.save(student);
    }
    
    public List<Student> getStudents(){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Student");
        List<Student> students =  query.list();
        session.close();
        return students;
    }
 
    public int deleteStudent(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Student where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public int updateStudent(int id, Student stu){
         if(id <=0)  
               return 0;  
         Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            String hql = "update Student set name = :name, age=:age where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id",id);
            query.setString("name",stu.getName());
            query.setInteger("age",stu.getAge());
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            tx.commit();
            session.close();
            return rowCount;
    }
}
 