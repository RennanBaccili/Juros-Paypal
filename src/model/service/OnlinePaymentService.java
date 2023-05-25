package model.service;

public interface OnlinePaymentService {
	double paymentFee(Double amount); //metodo
	double interest(Double amount,int months);
	
}
