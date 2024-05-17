package com.booking.service;

import java.util.List;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class ReservationService {

	public static void createReservation(List<Person> personList, List<Service> serviceList, List<Reservation> reservationList) {

		Customer customer = ValidationService.validateInputCustomer(personList);
		Employee employee = ValidationService.validateInputEmployee(personList);
		List<Service> services = ValidationService.validateInputService(serviceList);
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
		
		System.out.println("   Berhasil menambahkan reservasi");
		System.out.print("   Total Biaya Booking : Rp." + String.format("%,.0f", (double) reservationPrice));
	}

//	public static void getCustomerByCustomerId(){}

	public static void editReservationWorkstage(List<Reservation> reservationList, List<Person> personList, List<Reservation> reservatioHistory) {

		PrintService.showRecentReservation(reservationList);

		if (reservationList.size() != 0) {
			Reservation reservation = ValidationService.validateInputReservation(reservationList);

			System.out.print("   Selesaikan reservasi:");
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
				

			}

			
			reservationList.remove(reservation);

			System.out.println("   Reservasi dengan ID " + rid + " sudah " + workstage);
		}

	}

	public static void getReservationHistory(List<Reservation> reservationHistory) {

		PrintService.showHistoryReservation(reservationHistory);
	}

	

	
}
