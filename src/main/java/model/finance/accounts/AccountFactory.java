package model.finance.accounts;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsibility: provide a predefined collection of FinancialAccounts
 * Uses: ArrayList, List, FinancialAccount, DebitAccount, CreditAccount
 * Used by: FinanceModel
 *
 * @author Simon Porsgaard
 *
 * */

public class AccountFactory {


    /*
    1xxx: Tillgångar uppdelade som 10xx immateriella anläggningstillgångar, 11xx-12xx materiella anläggningstillgångar, 13xx finansiella anläggningstillgångar (långfristiga fordringar), 14xx varulager med mera, 15xx-17xx kortfristiga fordringar, 18xx kortfristiga placeringar, 19xx kassa och bank.
    20xx: Eget kapital, 21xx obeskattade reserver, 22xx avsättningar, 23xx långfristiga skulder, 24xx-29xx kortfristiga skulder.
    3xxx: Intäkter uppdelade som 30xx-37xx nettoomsättning, 38xx-39xx övriga intäkter.
    4xxx: Kostnader för varor, material och vissa köpta tjänster.
    5xxx-6xxx: Övriga externa rörelsekostnader.
    70xx-76xx: Personalkostnader, 77xx nedskrivningar, 78xx avskrivningar, 79xx övriga rörelsekostnader.
    8xxx: Finansiella poster (exempelvis ränte-/valutaintäkter/-utgifter), bokslutsdispositioner, skatt och årets resultat.
    0xxx och 9xxx: Interna konton
    * */

    /**
     * Returns a list of a handful accounts from the Swedish BAS kontoplan
     * */
    public static List<FinancialAccount> getBasicSwedishBASAccountPlan(){
        List<FinancialAccount> accs = new ArrayList<>();
        // BALANCE SHEET
        accs.add(new DebitAccount("Patents", 1030));
        accs.add(new DebitAccount("Property", 1110));
        accs.add(new DebitAccount("Machines", 1210));
        accs.add(new DebitAccount("Cars and means of transportation", 1240));
        accs.add(new DebitAccount("Inventory of goods", 1460));
        accs.add(new DebitAccount("Accounts receivables", 1510));
        accs.add(new DebitAccount("Bank balance", 1900));
        accs.add(new CreditAccount("Restricted equity", 2080));
        accs.add(new CreditAccount("Reserve fund", 2086));
        accs.add(new CreditAccount("Free equity", 2090));
        accs.add(new CreditAccount("Last year's result", 2098));
        accs.add(new CreditAccount("This year's result", 2099));
        accs.add(new CreditAccount("Short term debt", 2330));
        accs.add(new CreditAccount("Long term debt", 2350));
        accs.add(new CreditAccount("Accounts payable", 2440));
        accs.add(new CreditAccount("Tax debt", 2510));
        accs.add(new CreditAccount("VAT payable", 2610));
        accs.add(new CreditAccount("VAT receivable", 2640));

        accs.add(new CreditAccount("Sales", 3000));
        accs.add(new CreditAccount("Other revenue", 3999));

        accs.add(new DebitAccount("Purchase of goods", 4000));
        accs.add(new DebitAccount("Costs of facilities", 5000));
        accs.add(new DebitAccount("Costs of vehicles", 5600));
        accs.add(new DebitAccount("Costs of salaries", 7000));
        accs.add(new DebitAccount("Write-down costs", 7700));
        accs.add(new DebitAccount("Depreciation costs", 7800));

        accs.add(new CreditAccount("Financial revenue", 8000));

        accs.add(new DebitAccount("Result", 8999));


        return accs;
    }
}
