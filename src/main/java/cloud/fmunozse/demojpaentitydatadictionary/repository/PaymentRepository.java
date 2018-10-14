package cloud.fmunozse.demojpaentitydatadictionary.repository;

import cloud.fmunozse.demojpaentitydatadictionary.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
