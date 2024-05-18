package com.booking;



import java.util.ArrayList;
import java.util.List;

import com.booking.service.MenuService;

public class Main {
	public static void main(String[] args) {
		MenuService.mainMenu();
	}

	
	public void createTable(List<String> string) {
		List<String[]> dataTable = new ArrayList<String[]>();

		//
		String[] heading = { "No", "ID", "Name" };
		int no = 1;
		for (String e : string) {
			if (dataTable.size() == 0) {
				dataTable.add(heading);
			}
			String[] body = { String.valueOf(no),  };
			dataTable.add(body);
			no++;
		}

		//
		int[] listColumnWidht = getColumnWidth(dataTable);
		String lines = createLines(listColumnWidht);

		String columnWidth = "";
		for (int i = 0; i < listColumnWidht.length; i++) {
			columnWidth += "|" + " %-" + listColumnWidht[i] + "s ";
		}
		columnWidth += "|";

		//
		System.out.println("   " + lines);
		for (int i = 0; i < dataTable.size(); i++) {
			if (i == 0) {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2]);
				System.out.println();
				System.out.println("   " + lines);
			} else {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2]);
				System.out.println();
			}
		}
		System.out.println("   " + lines);
	}

	public int[] getColumnWidth(List<String[]> dataTable) {
		int[] listColumnWidht = new int[dataTable.get(0).length];

		for (int x = 0; x < dataTable.get(0).length; x++) {

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

	public String createLines(int[] listColumnWidht) {
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
	
}