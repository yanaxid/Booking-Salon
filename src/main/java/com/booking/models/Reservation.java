package com.booking.models;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Reservation {
	private String reservationId;
	private Customer customer;
	private Employee employee;
	private List<Service> services;
	private double reservationPrice;
	private String workstage;
	// workStage (In Process, Finish, Canceled)

	public Reservation(String reservationId, Customer customer, Employee employee, List<Service> services) {

		this.reservationId = reservationId;
		this.customer = customer;
		this.employee = employee;
		this.services = services;
		this.reservationPrice = calculateReservationPrice();
		this.workstage = "In Progress";
		
		
	};

	private double calculateReservationPrice() {
		double reservationPrice = 0;
		
		for (int i = 0; i < getServices().size(); i++) {
			reservationPrice += getServices().get(i).getPrice();
		}
		
		double percentage = 0;
		if(getCustomer().getMember().getMembershipName().equalsIgnoreCase("silver")) {
			percentage = 0.05 * reservationPrice;
		}else if(getCustomer().getMember().getMembershipName().equalsIgnoreCase("gold")) {
			percentage = 0.1 * reservationPrice;
		}
		
		return reservationPrice-percentage;
	}
}
