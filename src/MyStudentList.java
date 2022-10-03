import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@author HE170417 and HE173136
public class MyStudentList {
    protected ArrayList<Student> list = new ArrayList<>();
    Scanner sc1 = new Scanner(System.in);
    int current = 0;
    //=====================Create=====================
    public void CreateStudent(int amount, boolean needCheck) {
        String tmp;
        Student newStudent;
        for (int i = current; i < amount+current; i++) {
            try {
                if (i>=10 && needCheck) {
                    System.out.println("Do you want to continues? (Y/N)");
                    tmp = sc1.nextLine().toUpperCase();
                    if (tmp.compareTo("N") == 0) break;
                    else if (tmp.compareTo("Y") != 0) throw new Exception("Input invalid");
                }
                System.out.println("\nStudent "+(i + 1));
                newStudent = inputStudentData();
                if(isRecordDuplicate(newStudent)){
                    System.out.println("Record duplicate! Reject!");
                    i--;
                }else{
                    list.add(newStudent);
                    System.out.println("Complete!");
                }
            } catch (Exception e) {
                System.out.println(e);
                i--;
            }
        }
        current += amount;
    }
    public Student inputStudentData() {
        Student studentTmp = new Student();
        while (true) {
            try {
                System.out.print("ID: ");
                studentTmp.setId(sc1.nextLine().trim());
                break;
            } catch (Exception e) {
                System.out.println("Invalid: " + e);
            }
        }
        if(findById(list,studentTmp.getId())== null){
            while (true) {
                System.out.print("Please enter student name: ");
                studentTmp.setStudentName(sc1.nextLine());
                if (isStudentNameValid(studentTmp.getStudentName().trim())) break;
                else System.out.println("Student name is invalid");
            }
        }
        else{
            System.out.println("Notification: ID has been found in the data base! records will be save to the student who own this ID");
            studentTmp.setStudentName(findById(list,studentTmp.getId()).getStudentName());
        }

        while (true) {
            try {
                System.out.print("Semester: ");
                studentTmp.setSemester(Integer.parseInt(sc1.nextLine().trim()));
                if (studentTmp.getSemester() < 10 && studentTmp.getSemester() > 0) break;
                else System.out.println("Semester must be between 0 and 9");
            } catch (Exception e) {
                System.out.println("Invalid: " + e);
            }
        }
        while (true) {
            try {
                System.out.print("Course name: ");
                studentTmp.setCourseName(sc1.nextLine().toUpperCase().trim());
                if (isCourseNameValid(studentTmp.getCourseName())) break;
                else System.out.println("Course must be in 1 of 3 courses only: Java, .Net, C/C++");
            } catch (Exception e) {
                System.out.println("Invalid: " + e);
            }
        }
        return studentTmp;
    }
    public Student inputTargetStudent(String id){
        Student studentTmp = new Student();
        studentTmp.setId(id);
        studentTmp.setStudentName(findById(list,studentTmp.getId()).getStudentName());
        while (true) {
            try {
                System.out.print("Semester: ");
                studentTmp.setSemester(Integer.parseInt(sc1.nextLine().trim()));
                if (studentTmp.getSemester() < 10 && studentTmp.getSemester() > 0) break;
                else System.out.println("Semester must be between 0 and 9");
            } catch (Exception e) {
                System.out.println("Invalid: " + e);
            }
        }
        while (true) {
            try {
                System.out.print("Course name: ");
                studentTmp.setCourseName(sc1.nextLine().toUpperCase().trim());
                if (isCourseNameValid(studentTmp.getCourseName())) break;
                else System.out.println("Course must be in 1 of 3 courses only: Java, .Net, C/C++");
            } catch (Exception e) {
                System.out.println("Invalid: " + e);
            }
        }
        return studentTmp;
    }
    //=====================Report=====================
    public void traverse(ArrayList<Student> printList) {
        if (printList.isEmpty()) System.out.println("List is empty!");
        for (Student student : printList) {
            System.out.println(student.toString());
        }
    }
    public void traverseListByID(String id){
        if(list.isEmpty()) System.out.println("List is empty");
        for(Student i : list){
            if(i.getId().compareToIgnoreCase(id)==0){
                System.out.println(i.toString());
            }
        }
    }
    public void report(){
         ArrayList<Student> studentSearched = new ArrayList<>();
         //take list of specific student
         for(Student i : list){
             if(findById(studentSearched,i.getId())==null){
                studentSearched.add(i);
             }
         }
        System.out.println("ID  | Name  | Course Name   | Attempts");
         //count course
        for(Student i: studentSearched){
            //java
            if(countCourse(i.getId(),"java")!=0){
                System.out.println(i.getId()+"  | "+i.getStudentName().toUpperCase()+"    | Java    | "+countCourse(i.getId(),"java"));
            }
            //.net
            if(countCourse(i.getId(),".net")!=0){
                System.out.println(i.getId()+"  | "+i.getStudentName()+"   |.NET    |"+countCourse(i.getId(),".net"));
            }
            // C/C++
            if(countCourse(i.getId(),"C/C++")!=0){
                System.out.println(i.getId()+"  | "+i.getStudentName()+"   |C/C++  |"+countCourse(i.getId(),"C/C++"));
            }
        }
    }
    int countCourse(String id, String courseName){
        int count = 0;
        for(Student i: list){
            if(i.getId().compareToIgnoreCase(id)==0){
                if(i.getCourseName().compareToIgnoreCase(courseName)==0){
                    count++;
                }
            }
        }
        return count;
    }
    //=====================Find=====================
    public Student findById(ArrayList<Student> findList, String targetId) {
        for (Student student : findList) {
            if (student.getId().compareToIgnoreCase(targetId) == 0) {
                return student;
            }
        }
        return null;
    }
    public ArrayList<Student> findByName(ArrayList<Student> list, String targetName) {
        ArrayList<Student> list1 = new ArrayList<>();
        for (Student student : list) {
            if (student.getStudentName().toLowerCase().contains(targetName.toLowerCase().trim())) {
                list1.add(student);
            }
        }
        return list1;
    }
    //=====================Sort=====================
    public void sortByName1(ArrayList<Student> list){
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student student, Student t1) {
                return student.getStudentName().compareToIgnoreCase(t1.getStudentName());
            }
        });
    }
    //=====================Delete=====================
    public void deleteSpecific(Student studentWanted){
        for(int i =0 ; i< list.size();i++){
            if(list.get(i).getId().compareToIgnoreCase(studentWanted.getId())==0){
                if(list.get(i).getSemester()==studentWanted.getSemester()){
                    if(list.get(i).getCourseName().compareToIgnoreCase(studentWanted.getCourseName())==0){
                        list.remove(i);
                        System.out.println("Success!");
                        current--;
                        return;
                    }
                }
            }
        }
        System.out.println("Record not found");
    }
    //=====================Validator=====================

    boolean isCourseNameValid(String courseName) {
        String[] pattern = {"JAVA", ".NET", "C/C++"};
        for (String i : pattern) {
            if (i.compareToIgnoreCase(courseName) == 0) return true;
        }
        return false;
    }
    boolean isRecordDuplicate(Student record){
        for(Student i : list){
            if(i.getId().compareToIgnoreCase(record.getId())==0){
                if(i.getSemester() == record.getSemester()){
                    if(i.getCourseName().compareToIgnoreCase(record.getCourseName())==0){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    boolean isStudentNameValid(String name) {
        if(name.isEmpty()) return false;
        Pattern pattern = Pattern.compile("[^a-zA-Z\\s]");
        Matcher matcher = pattern.matcher(name);
        return !matcher.find();
    }
    //=====================Miscellaneous=====================
    ArrayList<Student> getList() {
        return list;
    }
}