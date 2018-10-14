package cloud.fmunozse.demojpaentitydatadictionary.model.iso2022;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ISOReturnTransaction {

    private String idReturn;
    private String originalTransactionId;
    private String rTransactionQualification;
    private BigDecimal paymentAmount;

}
