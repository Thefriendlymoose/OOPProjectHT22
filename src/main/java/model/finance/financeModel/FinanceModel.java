package model.finance.financeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FinanceModel {

    private Map<Long, SiteFinanceModel> financeModels;

    public FinanceModel(Map<Long, SiteFinanceModel> financeModels){
        this.financeModels = financeModels;
    }

    public SiteFinanceModel getSiteFinanceModel(long id) throws Exception {
        if (!financeModels.containsKey(id)){
            throw new Exception("Finance model with site id " + id + " doesn't exist");
        }
        return financeModels.get(id);
    }

    public void addNewSiteFinanceModel(long id) throws Exception {
        if (financeModels.containsKey(id)){
            throw new Exception("Finance model with site id " + id + " is already exists");
        }
        financeModels.put(id, new SiteFinanceModel(id, new HashMap<>(), new ArrayList<>()));
    }
}
