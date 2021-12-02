package controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidatePhoneNumberTest {

	private PlaceOrderController place_order_controller;
	
	@BeforeEach
	void setUp() throws Exception {
		this.place_order_controller = new PlaceOrderController();
	}

	/**
	 * Test case 1: Null input
	 */
	@Test
	void testValidatePhoneNumber1() {
		assertEquals(false, this.place_order_controller.validatePhoneNumber(null));
	}
	
	/**
	 * Test case 2: Not have length of 10
	 */
	@Test
	void testValidatePhoneNumber2() {
		assertEquals(false, this.place_order_controller.validatePhoneNumber("0988"));
		assertEquals(false, this.place_order_controller.validatePhoneNumber("095998589437"));
	}
	
	/**
	 * Test case 3: Not start with 0
	 */
	@Test
	void testValidatePhoneNumber3() {
		assertEquals(false, this.place_order_controller.validatePhoneNumber("9887654321"));
	}
	
	/**
	 * Test case 3: Contains character other than numbers
	 */
	@Test
	void testValidatePhoneNumber4() {
		assertEquals(false, this.place_order_controller.validatePhoneNumber("98a765%321"));
	}
	
	/**
	 * Test case 4: Valid input
	 */
	@Test
	void testValidatePhoneNumber5() {
		assertEquals(true, this.place_order_controller.validatePhoneNumber("0987654321"));
	}

}
