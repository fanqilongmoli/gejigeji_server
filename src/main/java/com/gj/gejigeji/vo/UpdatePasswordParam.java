package com.gj.gejigeji.vo;

public class UpdatePasswordParam {
    private String accountID;
    private String formerPas;
    private String newPas;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getFormerPas() {
        return formerPas;
    }

    public void setFormerPas(String formerPas) {
        this.formerPas = formerPas;
    }

    public String getNewPas() {
        return newPas;
    }

    public void setNewPas(String newPas) {
        this.newPas = newPas;
    }
}
