package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class PrintService {

	public static void printMenu(String title, String[] menuArr) {

		int num = 1;
		System.out.println(title);
		for (int i = 0; i < menuArr.length; i++) {
			if (i == (menuArr.length - 1)) {
				num = 0;
			}
			System.out.println(num + ". " + menuArr[i]);
			num++;
		}

		System.out.print("-> ");
	}

	public static void printServices(List<Service> serviceList) {
			createTableServices(serviceList);
	}

	// Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
	public static void showRecentReservation(List<Reservation> reservationList) {
		if(reservationList.size() == 0) {
			System.out.println("Data masih kosong");
		}else {
			createTableReservation(reservationList);
		}
		

	}

	public static void showAllCustomer(List<Person> personList) {
		List<Customer> customers = personList.stream().filter(o -> o instanceof Customer).map(x -> (Customer) x).collect(Collectors.toList());
		createTableCustomer(customers);

	}

	public static void showAllEmployee(List<Person> personList) {
		List<Employee> employees = personList.stream().filter(o -> o instanceof Employee).map(x -> (Employee) x).collect(Collectors.toList());
		createTableEmployee(employees);
	}

	public static void showHistoryReservation(List<Reservation> reservatioHistory) {
		
		if(reservatioHistory.size() == 0) {
			System.out.println("Data masih kosong");
		}else {
			createTableReservationHistory(reservatioHistory);
		}
		
	}
	
	
	
	
	public static void createTableReservationHistory(List<Reservation> reservatioHistory) {

		String[] tableHeading = { "No", "ID", "Customer name", "Services", "Price", "Workstage" };
		List<String[]> dataTable = new ArrayList<>();
		
		
		
		double total = 0;
		int no = 1;
		for (Reservation e : reservatioHistory) {
			if (dataTable.size() == 0) {
				dataTable.add(tableHeading);
			}

			String services = "";
			for (int i = 0; i < e.getServices().size(); i++) {
				
				if(e.getServices().size()-1 == i) {
					services += e.getServices().get(i).getServiceName();
				}else {
					services += e.getServices().get(i).getServiceName() + ", ";
				}
			}
			
			

			String[] tableBody = { String.valueOf(no), e.getReservationId(), e.getCustomer().getName(), services,
					"Rp."+ String.format("%,.0f", (double)e.getReservationPrice()), e.getWorkstage() };
			dataTable.add(tableBody);
			
			
			if(e.getWorkstage().equalsIgnoreCase("finish")) {
				total += e.getReservationPrice();
			}
			
			
			
			if (dataTable.size() > reservatioHistory.size()) {

				String[] tableFooter = { "", "", "", "", "Total Keuntungan", String.format("%,.0f", (double) total) };
				dataTable.add(tableFooter);
			}
			
			
			no++;
		}
		//
		int columnCount = calculateColomn(tableHeading);
		int[] listColumnWidht = getColumnWidth(columnCount, dataTable);
		//
		String lines = createLines(listColumnWidht);
//		String columnWidth = setColumnWidht(listColumnWidht);
		
		String columnWidth = "";
		for (int i = 0; i < listColumnWidht.length; i++) {
			
			if(i == 4 || i== 5) {
				columnWidth += "|" + " %" + listColumnWidht[i] + "s ";
			}else {
				columnWidth += "|" + " %-" + listColumnWidht[i] + "s ";
			}
			
		}
		columnWidth += "|";
		//
		System.out.println("   " + lines);
		for (int i = 0; i < dataTable.size(); i++) {
			if (i == 0) {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3], dataTable.get(i)[4], dataTable.get(i)[5]);
				System.out.println();
				System.out.println("   " + lines);
			}else if (i > 0 && i < dataTable.size() - 1) {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3], dataTable.get(i)[4], dataTable.get(i)[5]);
				System.out.println();
				
			}else if (i == dataTable.size() - 1) {
			
			
				String columnWidthExtended = "";
				for (int x = 0; x < listColumnWidht.length; x++) {

					if (x == 5 || x == 6) {
						columnWidthExtended += "|" + " %" + listColumnWidht[x] + "s |";
					}else if (x == 0) {
						columnWidthExtended += "|" + " %" + listColumnWidht[x] + "s ";
					}
					
					else {
						columnWidthExtended += " " + " %" + listColumnWidht[x] + "s ";
					}
				}
				System.out.println("   " + lines);
				System.out.printf("   " + columnWidthExtended, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3], dataTable.get(i)[4], dataTable.get(i)[5]);
				System.out.println();
			
				
			}
		}
		System.out.println("   " + lines);
	}
	
	
	

	public static void createTableReservation(List<Reservation> reservationList) {

		String[] tableHeading = { "No", "ID", "Customer name", "Services", "Price", "Workstage" };
		List<String[]> dataTable = new ArrayList<>();

		int no = 1;

		for (Reservation e : reservationList) {
			if (dataTable.size() == 0) {
				dataTable.add(tableHeading);
			}

			
			
			String services = "";
			for (int i = 0; i < e.getServices().size(); i++) {
				
				if(e.getServices().size()-1 == i) {
					services += e.getServices().get(i).getServiceName();
				}else {
					services += e.getServices().get(i).getServiceName() + ", ";
				}
			
			}

			String[] tableBody = { String.valueOf(no), e.getReservationId(), e.getCustomer().getName(), services,
					"Rp."+ String.format("%,.0f", (double)e.getReservationPrice()), e.getWorkstage() };
			dataTable.add(tableBody);
			no++;
		}
		//
		int columnCount = calculateColomn(tableHeading);
		int[] listColumnWidht = getColumnWidth(columnCount, dataTable);
		//
		String lines = createLines(listColumnWidht);
//		String columnWidth = setColumnWidht(listColumnWidht);
		
		String columnWidth = "";
		for (int i = 0; i < listColumnWidht.length; i++) {
			
			if(i == 4) {
				columnWidth += "|" + " %" + listColumnWidht[i] + "s ";
			}else {
				columnWidth += "|" + " %-" + listColumnWidht[i] + "s ";
			}
			
		}
		columnWidth += "|";
		//
		System.out.println("   " + lines);
		for (int i = 0; i < dataTable.size(); i++) {
			if (i == 0) {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3], dataTable.get(i)[4], dataTable.get(i)[5]);
				System.out.println();
				System.out.println("   " + lines);
			} else {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3], dataTable.get(i)[4], dataTable.get(i)[5]);
				System.out.println();
			}
		}
		System.out.println("   " + lines);
	}

	public static void createTableServices(List<Service> serviceList) {

		String[] tableHeading = { "No", "ID", "Name", "Price" };
		List<String[]> dataTable = new ArrayList<>();

		int no = 1;
		for (Service e : serviceList) {
			if (dataTable.size() == 0) {
				dataTable.add(tableHeading);
			}

			String[] tableBody = { String.valueOf(no), e.getServiceId(), e.getServiceName(), "Rp. "+String.format("%,.0f",(double)e.getPrice()) };
			dataTable.add(tableBody);
			no++;
		}
		//
		int columnCount = calculateColomn(tableHeading);
		int[] listColumnWidht = getColumnWidth(columnCount, dataTable);
		//
		String lines = createLines(listColumnWidht);
//		String columnWidth = setColumnWidht(listColumnWidht);
		
		String columnWidth = "";
		for (int i = 0; i < listColumnWidht.length; i++) {
			
			if(i == 3) {
				columnWidth += "|" + " %" + listColumnWidht[i] + "s ";
			}else {
				columnWidth += "|" + " %-" + listColumnWidht[i] + "s ";
			}
			
		}
		columnWidth += "|";
		//
		System.out.println("   " + lines);
		for (int i = 0; i < dataTable.size(); i++) {
			if (i == 0) {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3]);
				System.out.println();
				System.out.println("   " + lines);
			} else {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3]);
				System.out.println();
			}
		}
		System.out.println("   " + lines);
	}

	//
	public static void createTableEmployee(List<Employee> employee) {

		String[] tableHeading = { "No", "ID", "Name", "Experience" };
		List<String[]> dataTable = new ArrayList<>();

		int no = 1;
		for (Employee e : employee) {
			if (dataTable.size() == 0) {
				dataTable.add(tableHeading);
			}

			String[] tableBody = { String.valueOf(no), e.getId(), e.getName(), String.valueOf(e.getExperience()) };
			dataTable.add(tableBody);
			no++;
		}
		//
		int columnCount = calculateColomn(tableHeading);
		int[] listColumnWidht = getColumnWidth(columnCount, dataTable);
		//
		String lines = createLines(listColumnWidht);
		String columnWidth = setColumnWidht(listColumnWidht);
		//
		System.out.println("   " + lines);
		for (int i = 0; i < dataTable.size(); i++) {
			if (i == 0) {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3]);
				System.out.println();
				System.out.println("   " + lines);
			} else {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3]);
				System.out.println();
			}
		}
		System.out.println("   " + lines);
	}

	public static void createTableCustomer(List<Customer> customer) {

		String[] tableHeading = { "No", "ID", "Name", "Adsress", "Member", "Wallet" };
		List<String[]> dataTable = new ArrayList<>();

		int no = 1;
		for (Customer e : customer) {
			if (dataTable.size() == 0) {
				dataTable.add(tableHeading);
			}

			String[] tableBody = { String.valueOf(no), e.getId(), e.getName(), e.getAddress(), e.getMember().getMembershipName(),
					"Rp. "+ String.format("%,.0f", (double)e.getWallet()) };
			dataTable.add(tableBody);
			no++;
		}
		//
		int columnCount = calculateColomn(tableHeading);
		int[] listColumnWidht = getColumnWidth(columnCount, dataTable);
		//
		String lines = createLines(listColumnWidht);
//		String columnWidth = setColumnWidht(listColumnWidht);
		
		String columnWidth = "";
		for (int i = 0; i < listColumnWidht.length; i++) {
			
			if(i == 5) {
				columnWidth += "|" + " %" + listColumnWidht[i] + "s ";
			}else {
				columnWidth += "|" + " %-" + listColumnWidht[i] + "s ";
			}
			
		}
		columnWidth += "|";
		//
		System.out.println("   " + lines);
		for (int i = 0; i < dataTable.size(); i++) {
			if (i == 0) {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3],
						dataTable.get(i)[4], dataTable.get(i)[5]);
				System.out.println();
				System.out.println("   " + lines);
			} else {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3],
						dataTable.get(i)[4], dataTable.get(i)[5]);
				System.out.println();
			}
		}
		System.out.println("   " + lines);
	}

	///
	public static int[] getColumnWidth(int columnCount, List<String[]> dataTable) {
		int[] listColumnWidht = new int[columnCount];

		for (int x = 0; x < columnCount; x++) {

			int[] columnConten = new int[dataTable.size()];
			for (int i = 0; i < dataTable.size(); i++) {
				columnConten[i] = dataTable.get(i)[x].length();
			}
			int max = 0;
			for (int i = 0; i < columnConten.length; i++) {
				if (max < columnConten[i] || max == 0) {
					max = columnConten[i];
				}
			}
			listColumnWidht[x] = max;
		}
		return listColumnWidht;
	}

	public static int calculateColomn(String[] tableHeading) {
		int columnCount = tableHeading.length;
		return columnCount;
	}

	public static String createLines(int[] listColumnWidht) {
		String lines = "+";

		for (int i = 0; i < listColumnWidht.length; i++) {
			String line = "--";
			for (int j = 0; j < listColumnWidht[i]; j++) {
				line += "-";
			}
			lines += line + "+";
		}

		return lines;
	}

	public static String setColumnWidht(int[] listColumnWidht) {
		String setting = "";
		for (int i = 0; i < listColumnWidht.length; i++) {
			setting += "|" + " %-" + listColumnWidht[i] + "s ";
		}
		setting += "|";
		return setting;
	}
}
