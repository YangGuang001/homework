package com.yang.guava.demo;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Ordering;
import com.google.common.collect.Table;
import org.junit.Test;

import java.util.*;


public class PreconditionsTest {

    @Test
    public void PreconditionCheckTest() {
        System.out.println(PreconditionsSqrt(9));
        //System.out.println(PreconditionsSqrt(0));
        System.out.println(getValue(2));
    }

    private static double PreconditionsSqrt(double input) throws IllegalArgumentException {
        Preconditions.checkArgument(input > 0, "Illegal Argument passed: Negative value %s", input);
        return Math.sqrt(input);
    }

    public static int getValue(int input){
        int[] data = {1,6,3,4,5};
        int num = Preconditions.checkElementIndex(input,data.length,
                "Illegal Argument passed: Invalid index.");
        return num;
    }
    @Test
    public void testOdering() {
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(new Integer(5));
        numbers.add(new Integer(2));
        numbers.add(new Integer(15));
        numbers.add(new Integer(51));
        numbers.add(new Integer(53));
        numbers.add(new Integer(35));
        numbers.add(new Integer(45));
        numbers.add(new Integer(32));
        numbers.add(new Integer(43));
        numbers.add(new Integer(16));

        Ordering ordering = Ordering.natural();
        System.out.println("Input List: ");
        System.out.println(numbers);

        Collections.sort(numbers,ordering );
        System.out.println("Sorted List: ");
        System.out.println(numbers);

        System.out.println("======================");
        System.out.println("List is sorted: " + ordering.isOrdered(numbers));
        System.out.println("Minimum: " + ordering.min(numbers));
        System.out.println("Maximum: " + ordering.max(numbers));

        Collections.sort(numbers,ordering.reverse());
        System.out.println("Reverse: " + numbers);

        numbers.add(null);
        System.out.println("Null added to Sorted List: ");
        System.out.println(numbers);

        Collections.sort(numbers,ordering.nullsFirst());
        System.out.println("Null first Sorted List: ");
        System.out.println(numbers);
        System.out.println("======================");

        List<String> names = new ArrayList<String>();
        names.add("Ram");
        names.add("Shyam");
        names.add("Mohan");
        names.add("Sohan");
        names.add("Ramesh");
        names.add("Suresh");
        names.add("Naresh");
        names.add("Mahesh");
        names.add(null);
        names.add("Vikas");
        names.add("Deepak");

        System.out.println("Another List: ");
        System.out.println(names);

        Collections.sort(names,ordering.nullsFirst().reverse());
        System.out.println("Null first then reverse sorted list: ");
        System.out.println(names);
    }

    @Test
    public void testTable() {
        //Table<R,C,V> == Map<R,Map<C,V>>
      /*
      *  Company: IBM, Microsoft, TCS
      *  IBM 		-> {101:Mahesh, 102:Ramesh, 103:Suresh}
      *  Microsoft 	-> {101:Sohan, 102:Mohan, 103:Rohan }
      *  TCS 		-> {101:Ram, 102: Shyam, 103: Sunil }
      *
      * */
        //create a table
        Table<String, String, String> employeeTable = HashBasedTable.create();

        //initialize the table with employee details
        employeeTable.put("IBM", "101","Mahesh");
        employeeTable.put("IBM", "102","Ramesh");
        employeeTable.put("IBM", "103","Suresh");

        employeeTable.put("Microsoft", "111","Sohan");
        employeeTable.put("Microsoft", "112","Mohan");
        employeeTable.put("Microsoft", "113","Rohan");

        employeeTable.put("TCS", "121","Ram");
        employeeTable.put("TCS", "122","Shyam");
        employeeTable.put("TCS", "123","Sunil");

        //get Map corresponding to IBM
        Map<String,String> ibmEmployees =  employeeTable.row("IBM");

        System.out.println("List of IBM Employees");
        for(Map.Entry<String, String> entry : ibmEmployees.entrySet()){
            System.out.println("Emp Id: " + entry.getKey() + ", Name: " + entry.getValue());
        }

        //get all the unique keys of the table
        Set<String> employers = employeeTable.rowKeySet();
        System.out.print("Employers: ");
        for(String employer: employers){
            System.out.print(employer + " ");
        }
        System.out.println();

        //get all the unique keys of the table
        Set<String>  employerColumn= employeeTable.columnKeySet();
        System.out.print("Employers: ");
        for(String employer: employerColumn){
            System.out.print(employer + " ");
        }
        System.out.println();

        //get a Map corresponding to 102
        Map<String,String> EmployerMap =  employeeTable.column("102");
        for(Map.Entry<String, String> entry : EmployerMap.entrySet()){
            System.out.println("Employer: " + entry.getKey() + ", Name: " + entry.getValue());
        }

        Map<String, String> employerMap = employeeTable.row("102");
        for(Map.Entry<String, String> entry : EmployerMap.entrySet()){
            System.out.println("Employer: " + entry.getKey() + ", Name: " + entry.getValue());
        }
    }
}
