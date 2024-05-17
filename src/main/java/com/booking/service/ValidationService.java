package com.booking.service;

import java.util.Scanner;

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
}
