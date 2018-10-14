package cloud.fmunozse.demojpaentitydatadictionary;

import cloud.fmunozse.demojpaentitydatadictionary.model.Payment;
import cloud.fmunozse.demojpaentitydatadictionary.model.iso2022.ISOCreditTransferTransaction;
import cloud.fmunozse.demojpaentitydatadictionary.model.iso2022.ISOSuplementaryData;
import cloud.fmunozse.demojpaentitydatadictionary.repository.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoJpaEntityDatadictionaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoJpaEntityDatadictionaryApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData (PaymentRepository paymentRepository) {

		return (args -> {
			ISOSuplementaryData isoSuplementaryData = new ISOSuplementaryData();
			isoSuplementaryData.setName("supl-name");

			ISOCreditTransferTransaction trn = new ISOCreditTransferTransaction();
			trn.setCreditPartyAgentId("credit");
			trn.setIsoSuplementaryData(isoSuplementaryData);

			Payment payment = new Payment();
			payment.setName("name");
			payment.setPayloadDataDictionaryFromObject(trn);


			payment = paymentRepository.saveAndFlush(payment);
		});

	}
}
