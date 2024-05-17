package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ServiceRepository;



public class MenuService {
	
	private static List<Person> personList = PersonRepository.getAllPerson();
	private static List<Service> serviceList = ServiceRepository.getAllService();
	private static List<Reservation> reservationList = new ArrayList<>();
	private static List<Reservation> reservatioHistory = new ArrayList<>();
	
	private static Scanner sc = new Scanner(System.in);

	public static void mainMenu() {

		String[] mainMenuArr = { "Show Data", "Create Reservation", "Complete/cancel reservation", "Exit" };
		String[] subMenuArr = { "Recent Reservation", "Show Customer", "Show Available Employee", "Tampilkan History Reservation + Total Keuntungan", "Back to main menu" };

		int optionMainMenu;
		int optionSubMenu;

		
		boolean backToMainMenu = true;
		
		do {
			

			boolean backToSubMenu = true;
			
			System.out.println();
			PrintService.printMenu("MAIN MENU", mainMenuArr);
			optionMainMenu = ValidationService.validateInputNumber("Data harus angka");
			switch (optionMainMenu) {
				case 1:
					do {
						PrintService.printMenu("SHOW DATA", subMenuArr);
						optionSubMenu = ValidationService.validateInputNumber("Data harus angka");

						switch (optionSubMenu) {
							case 1:
								PrintService.showRecentReservation(reservationList);
								break;
							case 2:
								PrintService.showAllCustomer(personList);
								break;
							case 3:
								PrintService.showAllEmployee(personList);
								break;
							case 4:
								ReservationService.getReservationHistory(reservatioHistory);
								break;
							case 0:
								backToSubMenu = false;
						}
					} while (backToSubMenu);
					break;
				case 2:
					ReservationService.createReservation(personList, serviceList, reservationList);
					break;
				case 3:
					ReservationService.editReservationWorkstage(reservationList, personList, reservatioHistory);
					break;
				case 0:
					backToMainMenu = false;
					break;
			}
		} while (backToMainMenu) ;

		System.out.println("Terimakasih");

	}

	public static Scanner getSc() {
		return sc;
	}

	

}
