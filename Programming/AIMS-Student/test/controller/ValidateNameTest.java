package controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidateNameTest {

	private PlaceOrderController place_order_controller;
	
	@BeforeEach
	void setUp() throws Exception {
		this.place_order_controller = new PlaceOrderController();
	}

	/**
	 * Test case 1: Null input
	 */
	@Test
	void testValidateName1() {
		assertEquals(false, this.place_order_controller.validatePhoneNumber(null));
	}
	
	/**
	 * Test case 2: Contains special characters or numbers
	 */
	@Test
	void testValidateName2() {
		assertEquals(false, this.place_order_controller.validatePhoneNumber("Nguyen Van @"));
		assertEquals(false, this.place_order_controller.validatePhoneNumber("Nguyen 1Van @"));
		assertEquals(false, this.place_order_controller.validatePhoneNumber("Ngu7yen Van A"));
	}

	/**
	 * Test case 3: Valid name
	 */
	@Test
	void testValidateName3() {
		assertEquals(true, this.place_order_controller.validatePhoneNumber("Nguyen Van A"));
		assertEquals(true, this.place_order_controller.validatePhoneNumber("Donald Trump"));
	}
}
