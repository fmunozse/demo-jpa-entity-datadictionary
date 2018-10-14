package cloud.fmunozse.demojpaentitydatadictionary.model.iso2022;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ISOCreditTransferTransaction {

    private String id;
    private String creditPartyAgentId;
    private String debitPartyAgentId;
    private BigDecimal paymentAmount;

    private ISOSuplementaryData isoSuplementaryData;

}
