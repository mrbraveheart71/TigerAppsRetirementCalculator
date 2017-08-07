package main.tigerappsretirementcalculator;

/**
 * Created by Hartmut on 7/28/2017.
 */

public class RetirementSnapshot {

    private int Period;
    private int Age;

    private double PreIncomeTaxAssets;
    private double PreIncomeTaxAssetsContribution;
    private double PreIncomeTaxAssetsWithdrawal;

    private double PostIncomeTaxAssets;
    private double PostIncomeTaxAssetsContribution;
    private double PostIncomeTaxAssetsWithdrawal;

    private double SocialSecurityIncomeWithdrawal;

    private double TotalAfterTaxWithdrawal;

    public RetirementSnapshot(int period,
                              int age) {
        this.Period = period;
        this.Age  = age;
        this.PreIncomeTaxAssets = 0;
        this.PreIncomeTaxAssetsContribution = 0;
        this.PreIncomeTaxAssetsWithdrawal = 0;
        this.PostIncomeTaxAssets = 0;
        this.PostIncomeTaxAssetsContribution = 0;
        this.PostIncomeTaxAssetsWithdrawal = 0;
        this.SocialSecurityIncomeWithdrawal = 0;
        this.TotalAfterTaxWithdrawal = 0;
    }

    public int getPeriod() {
        return Period;
    }

    public void setPeriod(int period) {
        Period = period;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public double getPreIncomeTaxAssets() {
        return PreIncomeTaxAssets;
    }

    public void setPreIncomeTaxAssets(double preIncomeTaxAssets) {
        PreIncomeTaxAssets = preIncomeTaxAssets;
    }

    public double getPreIncomeTaxAssetsContribution() {
        return PreIncomeTaxAssetsContribution;
    }

    public void setPreIncomeTaxAssetsContribution(double preIncomeTaxAssetsContribution) {
        PreIncomeTaxAssetsContribution = preIncomeTaxAssetsContribution;
    }

    public double getPreIncomeTaxAssetsWithdrawal() {
        return PreIncomeTaxAssetsWithdrawal;
    }

    public void setPreIncomeTaxAssetsWithdrawal(double preIncomeTaxAssetsWithdrawal) {
        PreIncomeTaxAssetsWithdrawal = preIncomeTaxAssetsWithdrawal;
    }

    public double getPostIncomeTaxAssets() {
        return PostIncomeTaxAssets;
    }

    public void setPostIncomeTaxAssets(double postIncomeTaxAssets) {
        PostIncomeTaxAssets = postIncomeTaxAssets;
    }

    public double getPostIncomeTaxAssetsContribution() {
        return PostIncomeTaxAssetsContribution;
    }

    public void setPostIncomeTaxAssetsContribution(double postIncomeTaxAssetsContribution) {
        PostIncomeTaxAssetsContribution = postIncomeTaxAssetsContribution;
    }

    public double getPostIncomeTaxAssetsWithdrawal() {
        return PostIncomeTaxAssetsWithdrawal;
    }

    public void setPostIncomeTaxAssetsWithdrawal(double postIncomeTaxAssetsWithdrawal) {
        PostIncomeTaxAssetsWithdrawal = postIncomeTaxAssetsWithdrawal;
    }

    public double getSocialSecurityIncomeWithdrawal() {
        return SocialSecurityIncomeWithdrawal;
    }

    public void setSocialSecurityIncomeWithdrawal(double socialSecurityIncomeWithdrawal) {
        SocialSecurityIncomeWithdrawal = socialSecurityIncomeWithdrawal;
    }

    public double getTotalAfterTaxWithdrawal() {
        return TotalAfterTaxWithdrawal;
    }

    public void setTotalAfterTaxWithdrawal(double totalAfterTaxWithdrawal) {
        TotalAfterTaxWithdrawal = totalAfterTaxWithdrawal;
    }
}
