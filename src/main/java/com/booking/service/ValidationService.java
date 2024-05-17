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

	public  void validateInput() {
	}

	public  void validateCustomerId() {
	}

	public  int validateInputNumber(String message, Scanner sc) {
		boolean isValid = true;

		String numberInput = "";

		while (isValid) {
			numberInput = sc.next();
			if (numberInput.matches("[0-9]+")) {
				isValid = false;
			} else {
				isValid = true;
				
				if(message.equalsIgnoreCase("")) {
					message += "Inputan harus angka!";
				}
				System.out.println(message);
				System.out.print("-> ");
			}
		}
		return Integer.valueOf(numberInput);
	}

	public  Customer validateInputCustomer(List<Person> personList, PrintService printService, Scanner sc) {

		String customerID = "";
		Customer customer = null;

		printService.showAllCustomer(personList);

		boolean status = false;
		System.out.print("   Masukkan Customer ID: ");

		customerID = sc.nextLine();

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
			customerID = sc.nextLine();

			for (int i = 0; i < customers.size(); i++) {
				if (customerID.equalsIgnoreCase(customers.get(i).getId())) {
					status = true;
					customer = customers.get(i);
				}
			}
		}

		return customer;
	}

	public  Reservation validateInputReservation(List<Reservation> reservationList, Scanner sc) {
		String reservationID = "";
		Reservation reservation = null;

		boolean status = false;
		System.out.print("   Masukkan Reservation ID: ");
		reservationID = sc.nextLine();

		for (int i = 0; i < reservationList.size(); i++) {
			if (reservationID.equalsIgnoreCase(reservationList.get(i).getReservationId())) {
				status = true;
				reservation = reservationList.get(i);
			}
		}

		while (status == false) {
			System.out.println("   ID Reservation tidak terdaftar");
			System.out.print("   Masukkan Reservation ID: ");
			reservationID = sc.nextLine();

			for (int i = 0; i < reservationList.size(); i++) {
				if (reservationID.equalsIgnoreCase(reservationList.get(i).getReservationId())) {
					status = true;
					reservation = reservationList.get(i);
				}
			}
		}

		return reservation;
	}

	public  Employee validateInputEmployee(List<Person> personList, PrintService printService, Scanner sc) {
		String employeeID = "";
		Employee employee = null;

		printService.showAllEmployee(personList);

		boolean status = false;
		System.out.print("   Masukkan Employee ID: ");
		employeeID = sc.nextLine();

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
			employeeID = sc.nextLine();

			for (int i = 0; i < employees.size(); i++) {
				if (employeeID.equalsIgnoreCase(employees.get(i).getId())) {
					status = true;
					employee = employees.get(i);
				}
			}
		}

		return employee;
	}

	public  List<Service> validateInputService(List<Service> serviceList, PrintService printService, Scanner sc) {

		String serviceID = "";

		List<Service> listService = new ArrayList<Service>();
		printService.printServices(serviceList);

		boolean isNext = false;

		do {

			boolean status = false;
			System.out.print("   Masukkan Service Id: ");

			serviceID = sc.next();

			for (int i = 0; i < serviceList.size(); i++) {
				if (serviceID.equalsIgnoreCase(serviceList.get(i).getServiceId())) {
					status = true;
					listService.add(serviceList.get(i));
				}
			}

			while (status == false) {
				System.out.println("   ID Service tidak terdaftar");
				System.out.print("   Masukkan Service ID:");

				serviceID = sc.next();

				for (int i = 0; i < serviceList.size(); i++) {
					if (serviceID.equalsIgnoreCase(serviceList.get(i).getServiceId())) {
						status = true;
						listService.add(serviceList.get(i));
					}
				}
			}

			System.out.print("   Ingin pilih service yang lain (Y/T)? ");

			String x = sc.next();
			if (x.equalsIgnoreCase("y")) {
				isNext = false;
			} else if (x.equalsIgnoreCase("t")) {
				isNext = true;

			}

		} while (!isNext);

		return listService;
	}
}
