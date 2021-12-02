package controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidateAddressTest {
	
	private PlaceOrderController place_order_controller;

	@BeforeEach
	void setUp() throws Exception {
		this.place_order_controller = new PlaceOrderController();
	}

	/**
	 * Case 1: Null value input
	 */
	@Test
	void testValidateAddress1() {
		assertEquals(false, this.place_order_controller.validateAddress(null));
	}
	
	/**
	 * Case 2: Contains not allowed character
	 */
	@Test
	void testValidateAddress2() {
		assertEquals(false, this.place_order_controller.validateAddress("09 - Dong D@ - Ha N*i"));
	}
	
	/**
	 * Case 3: Valid address
	 */
	@Test
	void testValidateAddress3() {
		assertEquals(true, this.place_order_controller.validateAddress("09, Dong Da, Ha Noi"));
	}

}
