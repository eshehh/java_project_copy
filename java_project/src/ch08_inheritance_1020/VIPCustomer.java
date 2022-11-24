package ch08_inheritance_1020;

public class VIPCustomer extends Customer{

	private int agentID;
	double saleRatio;
	
	public VIPCustomer()
	{
		//부모클래스의 생성자가 먼저 호출이 됩니다. 사실
		// super() 예약어 생략이 되어 있음.
		super();
		customerGrade = "VIP";
		bonusRatio = 0.05;
		saleRatio = 0.1;
		System.out.println("VIPCusomer() 생성자 호출");
	}
	
	public VIPCustomer(int customerID, String customerName, int agentID){
		super(customerID, customerName);
		customerGrade = "VIP";
		bonusRatio = 0.05;
		saleRatio = 0.1;
		this.agentID = agentID;
		System.out.println("VIPCusomer(int, String) 생성자 호출");
	}
	
	
	public int calcPrice(int price){
		System.out.println("vip clac 메서드입니다.");
		bonusPoint += price * bonusRatio;
		return price - (int)(price * saleRatio);
	}
	
	public int getAgentID(){
		return agentID;
	}
	
	
}
