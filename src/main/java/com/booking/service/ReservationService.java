package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class ReservationService {

	public static void createReservation(List<Person> personList, List<Service> serviceList, List<Reservation> reservationList) {

		Customer customer = validateInputCustomer(personList);
		Employee employee = validateInputEmployee(personList);
		List<Service> services = validateInputService(serviceList);
		MenuService.getSc().nextLine();

		String reservationID = "";

		if (reservationList.size() == 0) {
			reservationID = "Rsv-01";

		} else {

			int lastIndex = Integer.valueOf(reservationList.get(reservationList.size() - 1).getReservationId().substring(4)) + 1;

			if (lastIndex <= 9) {
				reservationID = "Rsv-0" + String.valueOf(lastIndex);
			} else {
				reservationID = "Rsv-" + String.valueOf(lastIndex);
			}

		}

		reservationList.add(new Reservation(reservationID, customer, employee, services, "In Progrees"));

		double reservationPrice = 0;
		for (int i = 0; i < services.size(); i++) {
			reservationPrice += services.get(i).getPrice();

		}
		System.out.println("Berhasil menambahkan reservasi");
		System.out.print("Total Biaya Booking : Rp." + String.format("%,.0f", (double) reservationPrice));
	}

//	public static void getCustomerByCustomerId(){}

	public static void editReservationWorkstage(List<Reservation> reservationList, List<Person> personList, List<Reservation> reservatioHistory) {

		PrintService.showRecentReservation(reservationList);

		if (reservationList.size() != 0) {
			Reservation reservation = validateInputReservation(reservationList);

			System.out.print("Selesaikan reservasi:");
			String workstage = MenuService.getSc().nextLine();

			String rid = "";

			reservation.setWorkstage(workstage);

			if (workstage.equalsIgnoreCase("Finish")) {

				for (int i = 0; i < personList.size(); i++) {

					if (personList.get(i).getId().equalsIgnoreCase(reservation.getCustomer().getId())) {

						double currentWallet = ((Customer) personList.get(i)).getWallet() - reservation.getReservationPrice();

						((Customer) personList.get(i)).setWallet(currentWallet);
					}

				}

			}

			rid = reservation.getReservationId();

			if (reservatioHistory.size() == 0) {
				reservation.setReservationId("Hrsv-01");
			} else {
				int index = Integer.valueOf(reservatioHistory.get(reservatioHistory.size() - 1).getReservationId().substring(5)) + 1;

				if (index < 10) {
					reservation.setReservationId("Hrsv-0" + String.valueOf(index));
				} else {
					reservation.setReservationId("Hrsv-" + String.valueOf(index));
				}
			}

			reservatioHistory.add(reservation);
			reservationList.remove(reservation);

			System.out.println("Reservasi dengan ID " + rid + " sudah " + workstage);
		}

	}

	public static void getReservationHistory(List<Reservation> reservationHistory) {

		PrintService.showHistoryReservation(reservationHistory);
	}

	private static Reservation validateInputReservation(List<Reservation> reservationList) {
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

	private static Employee validateInputEmployee(List<Person> personList) {
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

	private static List<Service> validateInputService(List<Service> serviceList) {

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
			if (x.equalsIgnoreCase("Y")) {
				isNext = false;
			} else {
				isNext = true;
			}

		} while (!isNext);

		return listService;
	}

	public static Customer validateInputCustomer(List<Person> personList) {

		String customerID = "";
		Customer customer = null;

		PrintService.showAllCustomer(personList);

		boolean status = false;
		System.out.print("   Masukkan Customer ID:");

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
}
