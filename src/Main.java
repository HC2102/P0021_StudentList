import java.util.ArrayList;
import java.util.Scanner;
//@author HE170417 and HE173136
public class Main {
    public static void main(String[] args) {
        MyStudentList mtl = new MyStudentList();
        Scanner sc = new Scanner(System.in);
        int input;
        String temp;
        while (true) {
            try {
                printMenu();
                System.out.print("Your input: ");
                input = Integer.parseInt(sc.nextLine());
                switch (input) {
                    case 1:
                        int amount;
                        System.out.println("----- Create -----");
                        System.out.println("Enter amount of student:");
                        amount = Integer.parseInt(sc.nextLine());
                        mtl.CreateStudent(amount,true);
                        break;
                    case 2:
                        ArrayList<Student> students;
                        System.out.println("\n----- Find & Sort -----");
                        System.out.println("Please enter student name you want to find:");
                        temp = sc.nextLine();
                        if (mtl.findByName(mtl.getList(), temp) == null) {
                            System.out.print("Name does not exist!");
                            break;
                        } else {
                            students = mtl.findByName(mtl.getList(), temp);
                            mtl.sortByName1(students);
                            mtl.traverse(students);
                        }
                        break;
                    case 3:
                        Student studentWanted;
                        String subOption;
                        System.out.println("\n----- Update & Delete -----");
                        System.out.println("Please enter student ID you want to find:");
                        temp = sc.nextLine();
                        if (mtl.findById(mtl.getList(),temp) == null) {
                            System.out.println("ID not found");
                        } else {
                            //return the list of record according to that id
                            mtl.traverseListByID(temp);
                            //specific the record user want to delete
                            System.out.println("The record I wish to find has:");
                            studentWanted = mtl.inputTargetStudent(temp);
                            System.out.println("Do you want to update or delete? (U/D)");
                            subOption = sc.nextLine();
                            if (subOption.compareToIgnoreCase("D") == 0) {
                                mtl.deleteSpecific(studentWanted);
                            } else if (subOption.compareToIgnoreCase("U") == 0) {
                                mtl.deleteSpecific(studentWanted);
                                mtl.CreateStudent(1,false);
                            }
                        }
                        break;
                    case 4:
                        System.out.println("----- Report -----");
                        mtl.report();
                        break;
                    case 5:
                        System.out.print("Exit!");
                        return;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error occur: " + e);
            }
        }
    }
    public static void printMenu() {
        System.out.println("\nWELCOME TO STUDENT MANAGER" +
                "\n 1. Create" +
                "\n 2. Find and sort" +
                "\n 3. Update and Delete" +
                "\n 4. Report" +
                "\n 5. Exits" +
                "\n---------------------------------------");
    }
}