package cloud.fmunozse.demojpaentitydatadictionary.model;

public enum DDFieldsISO {
    CREDITTRASFER_CREDIT_PARTY_AGENT_ID("/creditPartyAgentId"),
    CREDITTRASFER_DEBIT_PARTY_AGENT_ID("/debitPartyAgentId"),
    CREDITTRASFER_SUPPLEMENTARY_DATA_NAME("/isoSuplementaryData/name");

    private String path;

    DDFieldsISO(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }

}
