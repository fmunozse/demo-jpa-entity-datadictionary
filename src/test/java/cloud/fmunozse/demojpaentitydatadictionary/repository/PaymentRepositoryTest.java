package cloud.fmunozse.demojpaentitydatadictionary.repository;

import cloud.fmunozse.demojpaentitydatadictionary.model.DDFieldsISO;
import cloud.fmunozse.demojpaentitydatadictionary.model.Payment;
import cloud.fmunozse.demojpaentitydatadictionary.model.iso2022.ISOCreditTransferTransaction;
import cloud.fmunozse.demojpaentitydatadictionary.model.iso2022.ISOSuplementaryData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentRepositoryTest {

    @Autowired
    PaymentRepository paymentRepository;

    @Test
    public void whenInsertPayloadComplete_thenCanSearchAndRetrieve() {

        //Given
        ISOSuplementaryData isoSuplementaryData = new ISOSuplementaryData();
        isoSuplementaryData.setName("supl-name");

        ISOCreditTransferTransaction trn = new ISOCreditTransferTransaction();
        trn.setCreditPartyAgentId("credit");
        trn.setIsoSuplementaryData(isoSuplementaryData);

        Payment payment = new Payment();
        payment.setName("name");
        payment.setPayloadDataDictionaryFromObject(trn);

        //When
        payment = paymentRepository.saveAndFlush(payment);
        payment = paymentRepository.findOne(payment.getId());


        //Then
        assertThat(payment.getName(),is("name"));
        assertThat(payment.getPayloadDataDirectoryTyped(ISOCreditTransferTransaction.class).getCreditPartyAgentId(), is("credit"));
        assertThat(payment.getDataDictionaryString(DDFieldsISO.CREDITTRASFER_CREDIT_PARTY_AGENT_ID), is("credit"));
        assertThat(payment.getDataDictionaryString(DDFieldsISO.CREDITTRASFER_DEBIT_PARTY_AGENT_ID), is(nullValue()));
        assertThat(payment.getDataDictionaryString(DDFieldsISO.CREDITTRASFER_SUPPLEMENTARY_DATA_NAME), is("supl-name"));
    }

    @Test
    public void whenCreateDataDirectoryFromScratch_thenCanSaveAndRetrieveLikeObjectTyped() {
        Payment payment = new Payment();
        payment.setName("name");
        payment.setDataDictionaryString("/creditPartyAgentId", "credit");
        payment.setDataDictionaryString("/isoSuplementaryData/name", "supl-name");

        assertThat(payment.getDataDictionaryString(DDFieldsISO.CREDITTRASFER_CREDIT_PARTY_AGENT_ID), is("credit"));

        //TODO - See note in Payment (setData... to resolved creation of multinode)
        //assertThat(payment.getDataDictionaryString("/isoSuplementaryData/name"), is("supl-name"));

        System.out.println(payment.getDataDictionary());
    }
}