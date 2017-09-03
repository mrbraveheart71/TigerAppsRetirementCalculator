package main.tigerappsretirementcalculator;

/**
 * Created by Hartmut on 7/28/2017.
 */

public class RetirementPlanner {
    private int CurrentAge;
    private int RetirementAge;
    private int LifeExpectancy;

    private double PreTaxAssets;
    private double PreTaxContributions;
    private double PostTaxAssets;
    private double PostTaxContributions;
    private double SocialSecurityIncome;
    private int SocialSecurityWithdrawalAge;

    private double AfterTaxWithdrawal;

    private final double FederalIncomeTax = 0.33;
    private final double StateIncomeTax = 0.06;
    private final double CapitalGainsTax = 0.15;
    private final double DividendYieldTax = 0.15;

    private final double StockYield = 0.05; //real yield
    private final double RiskFreeYield = 0.00; //risk free yield

    private final double PreRetirementYield = 0.05;
    private final double PostRetirementYield = 0.00;

    private RetirementSnapshot[] Cashflows;

    public int getCurrentAge() {
        return CurrentAge;
    }

    public void setCurrentAge(int currentAge) {
        CurrentAge = currentAge;
    }

    public int getRetirementAge() {
        return RetirementAge;
    }

    public void setRetirementAge(int retirementAge) {
        RetirementAge = retirementAge;
    }

    public int getLifeExpectancy() {
        return LifeExpectancy;
    }

    public void setLifeExpectancy(int lifeExpectancy) {
        LifeExpectancy = lifeExpectancy;
    }

    public RetirementSnapshot getCashflow(int age) {
        int i=0;
        while ((i < Cashflows.length) && (Cashflows[i].getAge() != age))
            i++;
        return Cashflows[i];
    }

    public RetirementPlanner(int currentAge,
                             int retirementAge,
                             int lifeExpectancy,
                             double preTaxAssets,
                             double preTaxContributions,
                             double postTaxAssets,
                             double postTaxContributions,
                             double socialSecurityIncome,
                             int socialSecurityWithdrawalAge,
                             double afterTaxWithdrawal) {
        this.CurrentAge = currentAge;
        this.RetirementAge = retirementAge;
        this.LifeExpectancy = lifeExpectancy;
        this.PreTaxAssets = preTaxAssets;
        this.PreTaxContributions = preTaxContributions;
        this.PostTaxAssets = postTaxAssets;
        this.PostTaxContributions = postTaxContributions;
        this.SocialSecurityIncome = socialSecurityIncome;
        this.SocialSecurityWithdrawalAge = socialSecurityWithdrawalAge;
        this.AfterTaxWithdrawal = afterTaxWithdrawal;
        Cashflows = new RetirementSnapshot[100];

    }

    public double GetEndOfLifeAssets() {
        return Cashflows[this.LifeExpectancy].getPostIncomeTaxAssets()+ Cashflows[this.LifeExpectancy].getPreIncomeTaxAssets();
    }

    public void Calculate() {
        // Everything is zero before the Current Age;
        for (int i=0; i < CurrentAge;i++) {
            Cashflows[i] = new RetirementSnapshot(0,i);
        }
        // At time of asset build up (Current Age to Retirement Age)
        Cashflows[CurrentAge] = new RetirementSnapshot(0,CurrentAge);
        Cashflows[CurrentAge].setPreIncomeTaxAssets(PreTaxAssets);
        Cashflows[CurrentAge].setPostIncomeTaxAssets(PostTaxAssets);
        //
        for (int i=CurrentAge+1; i <= RetirementAge;i++) {
            Cashflows[i] = new RetirementSnapshot(i-CurrentAge,i);
            Cashflows[i].setPreIncomeTaxAssets(Cashflows[i-1].getPreIncomeTaxAssets() * (1+this.PreRetirementYield) + this.PreTaxContributions);
            Cashflows[i].setPreIncomeTaxAssetsContribution(this.PreTaxContributions);
            Cashflows[i].setPostIncomeTaxAssets(Cashflows[i-1].getPostIncomeTaxAssets() * (1+this.PreRetirementYield) + this.PostTaxContributions);
            Cashflows[i].setPostIncomeTaxAssetsContribution(this.PostTaxContributions);
        }
        //
        // after Retirement
        for (int i=RetirementAge+1; i <= LifeExpectancy;i++) {
            Cashflows[i] = new RetirementSnapshot(i-CurrentAge,i);
            Cashflows[i].setPostIncomeTaxAssets(Cashflows[i-1].getPostIncomeTaxAssets());
            Cashflows[i].setPreIncomeTaxAssets(Cashflows[i-1].getPreIncomeTaxAssets());
            double remainingWithdrawal = this.AfterTaxWithdrawal*12;
            // First try social security withdrawal
            if (i> this.SocialSecurityWithdrawalAge) {
                remainingWithdrawal = remainingWithdrawal - this.SocialSecurityIncome*12;
                Cashflows[i].setSocialSecurityIncomeWithdrawal(this.SocialSecurityIncome*12);
            }
            // Then try post income tax assets
            if (remainingWithdrawal > Cashflows[i].getPostIncomeTaxAssets()) {
                remainingWithdrawal = remainingWithdrawal - Cashflows[i].getPostIncomeTaxAssets();
                Cashflows[i].setPostIncomeTaxAssets(0);
                // Now try pre income tax assets
                if ( remainingWithdrawal > Cashflows[i].getPreIncomeTaxAssets() * (1-this.FederalIncomeTax)) {
                    remainingWithdrawal = remainingWithdrawal - Cashflows[i].getPreIncomeTaxAssets() * (1-this.FederalIncomeTax);
                    Cashflows[i].setPreIncomeTaxAssets(0);
                } else {
                    Cashflows[i].setPreIncomeTaxAssets(Cashflows[i].getPreIncomeTaxAssets()-remainingWithdrawal/(1-this.FederalIncomeTax));
                    remainingWithdrawal = 0;
                }
            } else {
                Cashflows[i].setPostIncomeTaxAssets(Cashflows[i].getPostIncomeTaxAssets()-remainingWithdrawal);
                remainingWithdrawal = 0;
            }
            // Now after retirement yield application
            Cashflows[i].setPreIncomeTaxAssets(Cashflows[i].getPreIncomeTaxAssets() * (1+this.PostRetirementYield) );
            Cashflows[i].setPostIncomeTaxAssets(Cashflows[i].getPostIncomeTaxAssets() * (1+this.PostRetirementYield) );
            Cashflows[i].setTotalAfterTaxWithdrawal(this.AfterTaxWithdrawal*12-remainingWithdrawal);

        }
    }

}
