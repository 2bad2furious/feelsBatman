package com.company;


import com.company.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {

    }

    // refactoring java 7 => 8

    public static void printNameAndAgeOfEveryStudent(List<Student> students) {
        students.stream()
                        .forEach(Student-> System.out.println(Student.getName()+" "+Student.getAge()));
    }


    // map - method reference
    public static List<Integer> getStudentAges(List<Student> students) {
        return students.stream()
                .mapToInt(Student::getAge)
                .collect(()-> new ArrayList<>(),
                        (list,age) -> list.add(age),
                        (list1,list2)-> list1.addAll(list2));
    }

    // map
    public static Integer getMultiplyStudentAge(List<Student> students) {
        return students.stream()
                .map(Student::getAge)
                .reduce(1,(carry,age) -> carry * age);
    }

    // filter
    public static List<Student> getStudentsWithMinimumAge(List<Student> students, int minAge) {
        return students.stream()
                .filter(Student -> Student.getAge()== minAge)
                .collect(toList());
    }

    // filter and possible double method references
    public static List<Integer> getPrimeAges(List<Student> students) {
        return students.stream()
                .filter(Student -> isPrime(Student.getAge()))
                .map(Student::getAge)
                .collect(toList());
    }

    private static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
    }

    // comparing (sorted)
    public static List<Integer> getSortedAges(List<Student> students) {
        /*return students.stream()
                .sorted((s1,s2) -> s1.getAge()-s2.getAge())
                .mapToInt(Student::getAge)
                .collect(()-> new ArrayList<>(),
                        (list,age) -> list.add(age),
                        (list1,list2)-> list1.addAll(list2));
*/
        return students.stream()
                .map(Student::getAge)
                .sorted()
                .collect(toList());
    }

    // count
    public static long countMaleStudents(List<Student> students) {
        return (long) students.stream()
                .filter(Student -> Student.getGender() == Gender.MALE)
                .mapToInt(Student::getAge)
                .count();
    }


    // average, orElse
    public static double avgAgeOfFemaleStudent(List<Student> students){
        return students.stream()
                .filter(Student->Student.getGender()==Gender.FEMALE)
                .mapToInt(Student::getAge)
                .average()
                .getAsDouble();
    }


    // reduce
    public static double productOfStudentGrades(Student student) {
            return student.getGrades().stream()
                    .filter(Grade->Grade.getType()!=GradeType.F)
                    .map(Grade->Grade.getType().getValue())
                .reduce(1,(carry, val) -> val*carry)
                    .doubleValue();
    }


    // sorted(default), findFirst
    public static Optional<Grade> getBestMathGradeFromStudent(Student student) {
        return student.getGrades().stream()
                .filter(Grade ->Grade.getSubject() == Subject.MATH)
                .sorted((Grade1,Grade2)->Grade2.compareTo(Grade1))
                .findFirst();
    }

    // anyMatch
    public static boolean atLeastOneGradeA(Student student){
        return student.getGrades().stream()
                .anyMatch((Grade)->Grade.getType()==GradeType.A);
    }


    // limit, IntStream.iterate, boxed
    public static List<Integer> getFirstPrimes(int howMany) {
        return IntStream
                .iterate(2,i->i+1)
                .filter(Main::isPrime)
                .limit(howMany)
                .boxed()
                .collect(toList());
    }
}
