package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class ValidationService {

	public static void validateInput() {
	}

	public static void validateCustomerId() {
	}

	public static int validateInputNumber(String message) {
		Scanner sc = new Scanner(System.in);

		boolean isValid = true;
		String numberInput = "";

		while (isValid) {

			if (sc.hasNext()) {
				numberInput = sc.next();
				if (numberInput.matches("[0-9]+")) {
					isValid = false;
				} else {
					isValid = true;
					System.out.println(message);
					System.out.print("-> ");
				}

			} else {
				sc.close();
			}
		}

		return Integer.valueOf(numberInput);
	}
	
	
	public static Customer validateInputCustomer(List<Person> personList) {

		String customerID = "";
		Customer customer = null;

		PrintService.showAllCustomer(personList);

		boolean status = false;
		System.out.print("   Masukkan Customer ID: ");

		customerID = MenuService.getSc().nextLine();

		List<Customer> customers = new ArrayList<Customer>();
		customers = personList.stream().filter(o -> o instanceof Customer).map(x -> (Customer) x).collect(Collectors.toList());

		for (int i = 0; i < customers.size(); i++) {
			if (customerID.equalsIgnoreCase(customers.get(i).getId())) {
				status = true;
				customer = customers.get(i);
			}
		}

		while (status == false) {
			System.out.println("   ID Customer tidak terdaftar");
			System.out.print("   Masukkan Customer ID:");
			customerID = MenuService.getSc().nextLine();

			for (int i = 0; i < customers.size(); i++) {
				if (customerID.equalsIgnoreCase(customers.get(i).getId())) {
					status = true;
					customer = customers.get(i);
				}
			}
		}

		return customer;
	}
	
	
	public static Reservation validateInputReservation(List<Reservation> reservationList) {
		String reservationID = "";
		Reservation reservation = null;

		boolean status = false;
		System.out.print("   Masukkan Reservation ID: ");
		reservationID = MenuService.getSc().nextLine();

		for (int i = 0; i < reservationList.size(); i++) {
			if (reservationID.equalsIgnoreCase(reservationList.get(i).getReservationId())) {
				status = true;
				reservation = reservationList.get(i);
			}
		}

		while (status == false) {
			System.out.println("   ID Reservation tidak terdaftar");
			System.out.print("   Masukkan Reservation ID: ");
			reservationID = MenuService.getSc().nextLine();

			for (int i = 0; i < reservationList.size(); i++) {
				if (reservationID.equalsIgnoreCase(reservationList.get(i).getReservationId())) {
					status = true;
					reservation = reservationList.get(i);
				}
			}
		}

		return reservation;
	}

	public static Employee validateInputEmployee(List<Person> personList) {
		String employeeID = "";
		Employee employee = null;

		PrintService.showAllEmployee(personList);

		boolean status = false;
		System.out.print("   Masukkan Employee ID: ");
		employeeID = MenuService.getSc().nextLine();

		List<Employee> employees = personList.stream().filter(o -> o instanceof Employee).map(x -> (Employee) x).collect(Collectors.toList());

		for (int i = 0; i < employees.size(); i++) {
			if (employeeID.equalsIgnoreCase(employees.get(i).getId())) {
				status = true;
				employee = employees.get(i);
			}
		}

		while (status == false) {
			System.out.println("   ID Employee tidak terdaftar");
			System.out.print("   Masukkan Employee ID: ");
			employeeID = MenuService.getSc().nextLine();

			for (int i = 0; i < employees.size(); i++) {
				if (employeeID.equalsIgnoreCase(employees.get(i).getId())) {
					status = true;
					employee = employees.get(i);
				}
			}
		}

		return employee;
	}

	public static List<Service> validateInputService(List<Service> serviceList) {

		String serviceID = "";

		List<Service> listService = new ArrayList<Service>();
		PrintService.printServices(serviceList);

		boolean isNext = false;

		do {

			boolean status = false;
			System.out.print("   Masukkan Service Id: ");

			serviceID = MenuService.getSc().next();

			for (int i = 0; i < serviceList.size(); i++) {
				if (serviceID.equalsIgnoreCase(serviceList.get(i).getServiceId())) {
					status = true;
					listService.add(serviceList.get(i));
				}
			}

			while (status == false) {
				System.out.println("   ID Service tidak terdaftar");
				System.out.print("   Masukkan Service ID:");

				serviceID = MenuService.getSc().next();

				for (int i = 0; i < serviceList.size(); i++) {
					if (serviceID.equalsIgnoreCase(serviceList.get(i).getServiceId())) {
						status = true;
						listService.add(serviceList.get(i));
					}
				}
			}

			System.out.print("   Ingin pilih service yang lain (Y/T)? ");

			String x = MenuService.getSc().next();
			if (x.equalsIgnoreCase("y")) {
				isNext = false;
			} else if(x.equalsIgnoreCase("t")) {
				isNext = true;
				
			}

		} while (!isNext);

		return listService;
	}
}
