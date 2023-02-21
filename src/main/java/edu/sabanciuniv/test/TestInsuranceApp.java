package edu.sabanciuniv.test;

import edu.sabanciuniv.model.*;
import edu.sabanciuniv.utility.EntityManagerUtils;
import jakarta.persistence.EntityManager;


import java.time.LocalDate;
import java.time.Month;

public class TestInsuranceApp {
    public static void main(String[] args){
        saveTestData();

    }

    private static void saveTestData() {

        Student student1 = new Student("Özden Sayalan", LocalDate.of(1990, Month.JANUARY,01),"Aydın","Female");
        Student student2 = new Student("Aylin Şengül", LocalDate.of(2014, Month.FEBRUARY,10), "Istanbul","Female");
        Student student3 = new Student("Deniz Dora", LocalDate.of(2017, Month.FEBRUARY,27), "Istanbul","Male");

        Course course1=new Course("Java Development",1,3.00);
        Course course2=new Course("Machine Learning",2,3.00);
        Course course3=new Course("Python",3,3.00);
        Course course4=new Course("Software Testing",4,3.00);


        Instructor permanentInstructor1=new PermanentInstructor("Özlem Şengül","İstanbul","325624565",20000.00);
        Instructor permanentInstructor2=new PermanentInstructor("Amide Ünsal","İstanbul","76545643",45000.00);
        Instructor visitingResearcher1=new VisitingResearcher("İhsan Sayalan","Aydın","63472465",2000.00);
        Instructor visitingResearcher2=new VisitingResearcher("Nebahat Sayalan","Aydın","85678945",1000.00);

        course1.setInstructor(permanentInstructor1);
        course2.setInstructor(permanentInstructor2);
        course3.setInstructor(visitingResearcher2);
        course4.setInstructor(visitingResearcher1);

        course1.getStudentList().add(student1);
        course2.getStudentList().add(student2);
        course3.getStudentList().add(student3);
        course4.getStudentList().add(student1);


        EntityManager entityManager = EntityManagerUtils.getEntityManager("mysqlPU");
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(course1);
            entityManager.persist(course2);
            entityManager.persist(course3);
            entityManager.persist(course4);
            entityManager.persist(student1);
            entityManager.persist(student2);
            entityManager.persist(student3);
            entityManager.persist(permanentInstructor1);
            entityManager.persist(permanentInstructor2);
            entityManager.persist(visitingResearcher1);
            entityManager.persist(visitingResearcher2);
            entityManager.getTransaction().commit();
            System.out.println("All data persisted.");
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }finally{
            EntityManagerUtils.closeEntityManager(entityManager);

        }



    }
}
