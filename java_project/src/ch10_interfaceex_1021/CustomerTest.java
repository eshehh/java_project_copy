package ch10_interfaceex_1021;

public class CustomerTest {

	public static void main(String[] args) {

		Customer customer = new Customer();
	
		Buy buyer = customer;
		buyer.buy();
		buyer.order();
		
		Sell seller = customer;
		seller.sell();
		seller.order();
		
		if( seller instanceof Customer){
			Customer customer2 = (Customer)seller;
			customer2.buy();
			customer2.sell();
		}
		
		customer.order();
	}
}


