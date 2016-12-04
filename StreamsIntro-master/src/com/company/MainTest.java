package com.company;

import com.company.domain.*;
import com.sun.corba.se.impl.oa.poa.POAPolicyMediatorImpl_NR_UDS;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.jar.Manifest;

import static com.company.domain.GradeType.*;
import static com.company.domain.GradeType.E;
import static com.company.domain.Subject.*;
import static org.junit.Assert.*;

/**
 * Created by semantier on 11/28/2016.
 */
public class MainTest {

    School school;

    @org.junit.Before
    public void setUp() throws Exception {
        school = MockDataProvider.getNewSchool();
    }

    @org.junit.Test
    public void printNameAndAgeOfEveryStudent() throws Exception {
        Main.printNameAndAgeOfEveryStudent(school.getClasses().get(0).getStudents());
        // cannot test side-effects
    }

    @org.junit.Test
    public void getStudentAges() throws Exception {
        assertEquals(Arrays.asList(new Integer(14),new Integer(15), new Integer(13)),Main.getStudentAges(school.getClasses().get(0).getStudents()));
        assertEquals(Arrays.asList(new Integer(9),new Integer(12)), Main.getStudentAges(school.getClasses().get(1).getStudents()));
    }

    @org.junit.Test
    public void getMultiplyStudentAge() throws Exception {
        assertEquals(new Integer(14*15*13),Main.getMultiplyStudentAge(school.getClasses().get(0).getStudents()));
        assertEquals(new Integer(9*12),Main.getMultiplyStudentAge(school.getClasses().get(1).getStudents()));
    }

    @org.junit.Test
    public void getStudentsWithMinimumAge() throws Exception {
        assertEquals(Arrays.asList(school.getClasses().get(0).getStudents().get(2)),Main.getStudentsWithMinimumAge(school.getClasses().get(0).getStudents(),13));
        assertEquals(Arrays.asList(school.getClasses().get(1).getStudents().get(0)),Main.getStudentsWithMinimumAge(school.getClasses().get(1).getStudents(),9));
    }

    @org.junit.Test
    public void getPrimeAges() throws Exception {
        assertEquals(Arrays.asList(new Integer(13)),Main.getPrimeAges(school.getClasses().get(0).getStudents()));
        assertNotEquals(Arrays.asList(new Integer(12)),Main.getPrimeAges(school.getClasses().get(1).getStudents()));
    }

    @org.junit.Test
    public void getSortedAges() throws Exception {
        List<Student> list = school.getClasses().get(0).getStudents();
        assertEquals(Arrays.asList(list.get(2).getAge(),list.get(0).getAge(),list.get(1).getAge()),Main.getSortedAges(list));
        list = school.getClasses().get(1).getStudents();
        assertEquals(Arrays.asList(list.get(0).getAge(),list.get(1).getAge()),Main.getSortedAges(list));
    }

    @org.junit.Test
    public void countMaleStudents() throws Exception {
        assertEquals((long)2,Main.countMaleStudents(school.getClasses().get(0).getStudents()));
        assertEquals((long)0,Main.countMaleStudents(school.getClasses().get(1).getStudents()));
    }

    @org.junit.Test
    public void avgAgeOfFemaleStudent() throws Exception {
        assertEquals(15,Main.avgAgeOfFemaleStudent(school.getClasses().get(0).getStudents()),0.0);
        assertEquals(10.5,Main.avgAgeOfFemaleStudent(school.getClasses().get(1).getStudents()),0.0);
    }

    @org.junit.Test
    public void productOfStudentGrades() throws Exception {

    }

    @org.junit.Test
    public void getBestMathGradeFromStudent() throws Exception {
        Student s = school.getClasses().get(0).getStudents().get(0);
        assertEquals(Optional.of(s.getGrades().get(0)),Main.getBestMathGradeFromStudent(s));
        s = school.getClasses().get(1).getStudents().get(1);
        assertEquals(Optional.of(s.getGrades().get(1)),Main.getBestMathGradeFromStudent(s));
    }

    @org.junit.Test
    public void atLeastOneGradeA() throws Exception {
        assertTrue(Main.atLeastOneGradeA(new Student(
                "Pepa",
                14,
                Gender.MALE,
                Arrays.asList(
                        new Grade(MATH, A),
                        new Grade(MATH, B),
                        new Grade(GEOLOGY, B),
                        new Grade(GEOLOGY, F),
                        new Grade(BIOLOGY, C)
                ),
                MATH)));
        assertTrue(Main.atLeastOneGradeA(new Student(
                "Lucie",
                15,
                Gender.FEMALE,
                Arrays.asList(
                        new Grade(MATH, F),
                        new Grade(MATH, B),
                        new Grade(GEOLOGY, B),
                        new Grade(GEOLOGY, A),
                        new Grade(BIOLOGY, B),
                        new Grade(COMPUTER_SCIENCE, B),
                        new Grade(COMPUTER_SCIENCE, C)
                ),
                GEOLOGY)));
        assertFalse(Main.atLeastOneGradeA(new Student(
                "Anna",
                9,
                Gender.FEMALE,
                Arrays.asList(
                        new Grade(MATH, F),
                        new Grade(GEOLOGY, E),
                        new Grade(GEOLOGY, E)
                ),
                MATH)));
    }

    @org.junit.Test
    public void getFirstPrimes() throws Exception {

    }

}