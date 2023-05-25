package model.service;

import java.sql.Date;
import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;

public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

public void processContract(Contract contract,Integer months) {
	
	double basicCuota = contract.getTotalValue() / months;
	
	for (int i = 1; i<= months;i++) {
		LocalDate duoDate = contract.getDate().plusMonths(i); //função para adicionar os meses
		
		double interest = onlinePaymentService.interest(basicCuota, i);
		double fee = onlinePaymentService.paymentFee(basicCuota + interest); // taxa tem que ser aplicada em cima do valor com juros
		double cuota = basicCuota + interest + fee;
		
		contract.getInstallments().add(new Installment(duoDate,cuota));
	}
	
	
	
	//contract.getInstallments().add(new Installment(LocalDate.of(2022, 05, 25),214.05));
	//contract.getInstallments().add(new Installment(LocalDate.of(2022, 06, 25),220.05));
}
	
}
