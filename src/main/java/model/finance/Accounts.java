package model.finance;

import java.util.Map;

public class Accounts {

    private final long siteId;
    private final Map<Long, Account> accountMap;

    public Accounts(long siteId, Map<Long, Account> accountMap){
        this.siteId = siteId;
        this.accountMap = accountMap;
    }

}
