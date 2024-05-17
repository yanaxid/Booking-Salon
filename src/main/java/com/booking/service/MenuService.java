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
	
	PersonRepository personRepository = new PersonRepository();
	ServiceRepository serviceRepository = new ServiceRepository();

	private List<Person> personList = personRepository.getAllPerson();
	private List<Service> serviceList = serviceRepository.getAllService();
	private List<Reservation> reservationList = new ArrayList<>();
	private List<Reservation> reservatioHistory = new ArrayList<>();

	private PrintService printService = new PrintService();
	private ReservationService reservationService = new ReservationService();
	private ValidationService validationService = new ValidationService();
	private Scanner sc = new Scanner(System.in);

	public void mainMenu() {

		String[] mainMenuArr = { "Show Data", "Create Reservation", "Complete/cancel reservation", "Exit" };
		String[] subMenuArr = { "Recent Reservation", "Show Customer", "Show Available Employee", "Tampilkan History Reservation + Total Keuntungan",
				"Back to main menu" };

		int optionMainMenu;
		int optionSubMenu;

		boolean backToMainMenu = true;

		do {

			boolean backToSubMenu = true;

			System.out.println();
			printService.printMenu("MAIN MENU", mainMenuArr);
			optionMainMenu = validationService.validateInputNumber("", sc);
			switch (optionMainMenu) {
				case 1:
					do {
						printService.printMenu("SHOW DATA", subMenuArr);
						optionSubMenu = validationService.validateInputNumber("", sc);

						switch (optionSubMenu) {
							case 1:
								printService.showRecentReservation(reservationList);
								break;
							case 2:
								printService.showAllCustomer(personList);
								break;
							case 3:
								printService.showAllEmployee(personList);
								break;
							case 4:
								reservationService.getReservationHistory(reservatioHistory, printService);
								break;
							case 0:
								backToSubMenu = false;
						}
					} while (backToSubMenu);
					break;
				case 2:
					reservationService.createReservation(personList, serviceList, reservationList, printService,validationService, sc);
					break;
				case 3:
					reservationService.editReservationWorkstage(reservationList, personList, reservatioHistory, printService,validationService, sc);
					break;
				case 0:
					backToMainMenu = false;
					break;
			}
		} while (backToMainMenu);

		System.out.println("Terimakasih");

	}

}
