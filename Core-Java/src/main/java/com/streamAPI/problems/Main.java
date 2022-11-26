package com.streamAPI.problems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		
		List<Employee> list = new ArrayList<Employee>();
		
		list.add(new Employee(101,"udit",101,"active",1000));
		list.add(new Employee(102,"abhinav",101,"active",2000));
		list.add(new Employee(103,"sachin",102,"inactive",3000));
		list.add(new Employee(104,"tarun",102,"inactive",4000));
		list.add(new Employee(105,"umesh",103,"active",5000));
		list.add(new Employee(106,"vivek",103,"inactive",6000));
		list.add(new Employee(107,"sumit",104,"active",7000));
		
		// to print employee details based on department
		Map<Integer,List<Employee>> empBasedOnDept = list.stream().collect(Collectors.groupingBy(Employee::getDeptId, Collectors.toList()));
		empBasedOnDept.entrySet().forEach(entry -> System.out.println("Dept ID : "+entry.getKey()+" ====> "+entry.getValue()));
		
		// to print employee count based on department
		Map<Integer,Long> empCountBasedOnDept =  list.stream().collect(Collectors.groupingBy(Employee::getDeptId, Collectors.counting()));
		empCountBasedOnDept.entrySet().forEach(entry -> System.out.println("Department ID : "+entry.getKey()+" has "+entry.getValue()+" employees"));
		
		// to print count of active employee
		Long activeEmpCnt = list.stream().filter(e -> e.getStatus().equals("active")).count();
		System.out.println("Active Employee Count is "+activeEmpCnt);
		
		// to print count of inactive employee
		Long inactiveEmpCnt = list.stream().filter(e -> e.getStatus().equals("inactive")).count();
		System.out.println("Inactive Employee Count is "+inactiveEmpCnt);
		
		// to print employee with maximum salary
		Optional<Employee> empWidMaxSal = list.stream().max(Comparator.comparing(Employee::getSalary));
		System.out.println("Employee with max salary ====> "+empWidMaxSal);
		
		// to print employee with minimum salary
		Optional<Employee> empWidMinSal = list.stream().min(Comparator.comparing(Employee::getSalary));
		System.out.println("Employee with min salary ====> "+empWidMinSal);
		
		// to print maximum salary department wise
		Map<Integer,Optional<Employee>> topSalEmpDeptWise = list.stream().collect(Collectors.groupingBy(Employee::getDeptId, Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Employee::getSalary)))));
		topSalEmpDeptWise.forEach((k,v) -> System.out.println("Dept ID - "+k+" , Max Salary - "+v));
		
		// to print minimum salary department wise
		Map<Integer,Optional<Employee>> lowSalEmpDeptWise = list.stream().collect(Collectors.groupingBy(Employee::getDeptId, Collectors.reducing(BinaryOperator.minBy(Comparator.comparing(Employee::getSalary)))));
		lowSalEmpDeptWise.forEach((k,v) -> System.out.println("Dept ID - "+k+" , Min Salary - "+v));
		
		System.out.println("\n=============================================================================================\n");
		
		// print occurrences of each character in a string (perfect way)
		String str = "my name is udit yadav";
		
		List<Character> l = new ArrayList<Character>();
		
		str.chars().forEach(c -> {
			char tmp = (char) c;
			if(!l.contains(tmp)) { 
			long count = str.chars().filter(f -> f == c).count();
			System.out.println(tmp+" count is = "+count);
			l.add(tmp);
			}
		});
		
		Optional<Employee> employee = list.stream().filter(emp -> emp.getEmpName().equals("umesh")).findFirst();
		if(employee.isPresent()) {
			System.out.println("Employee ID -> "+employee.get().getEmpId());
		}
		
	}

}
