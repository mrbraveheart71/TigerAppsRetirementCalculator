package main.tigerappsretirementcalculator;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableLayout;

import java.text.DecimalFormat;

/**
 * Created by Hartmut on 8/5/2017.
 */

public class CashflowFragment extends android.support.v4.app.Fragment implements SeekBar.OnSeekBarChangeListener {

    private TableLayout tableLayout;
    private TableLayout tableHeaderLayout;
    private View rootView;
    private View tableRow;
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CashflowFragment newInstance(int sectionNumber) {
        CashflowFragment fragment = new CashflowFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public void showRetirementCashflows() {
        RetirementPlanner myRetirement = ((MainActivity) getActivity()).getRetirementPlanner();
        tableLayout.removeAllViews();
        tableHeaderLayout.removeAllViews();

        TableRow tblRow ;
        TextView table_period;
        TextView table_pre_tax_assets;
        TextView table_post_tax_assets;
        TextView table_social_security_distribution ;
        TextView table_retirement_withdrawal ;

        tblRow = (TableRow) LayoutInflater.from(getActivity()).inflate(R.layout.table_item,tableLayout,false);
        table_period = (TextView) tblRow.findViewById(R.id.table_period);
        table_pre_tax_assets = (TextView) tblRow.findViewById(R.id.table_pre_tax_assets);
        table_post_tax_assets = (TextView) tblRow.findViewById(R.id.table_post_tax_assets);
        table_social_security_distribution = (TextView) tblRow.findViewById(R.id.table_social_security_distribution);
        table_retirement_withdrawal = (TextView) tblRow.findViewById(R.id.table_retirement_withdrawal);

        table_period.setText("Age");
        table_period.setTextColor (ContextCompat.getColor(this.getContext(), R.color.colorDarkBlue));
        table_pre_tax_assets.setText("Pre-Tax Assets");
        table_pre_tax_assets.setTextColor (ContextCompat.getColor(this.getContext(), R.color.colorDarkBlue));
        table_post_tax_assets.setText("Post-Tax Assets");
        table_post_tax_assets.setTextColor (ContextCompat.getColor(this.getContext(), R.color.colorDarkBlue));
        table_social_security_distribution.setText("Social Security");
        table_social_security_distribution.setTextColor (ContextCompat.getColor(this.getContext(), R.color.colorDarkBlue));
        table_retirement_withdrawal.setText("Withdrawal");
        table_retirement_withdrawal.setTextColor (ContextCompat.getColor(this.getContext(), R.color.colorDarkBlue));
        tableHeaderLayout.addView(tblRow);

        for (int i = myRetirement.getCurrentAge(); i <= myRetirement.getLifeExpectancy(); i++) {
            tblRow = (TableRow) LayoutInflater.from(getActivity()).inflate(R.layout.table_item,tableLayout,false);
            table_period = (TextView) tblRow.findViewById(R.id.table_period);
            table_pre_tax_assets = (TextView) tblRow.findViewById(R.id.table_pre_tax_assets);
            table_post_tax_assets = (TextView) tblRow.findViewById(R.id.table_post_tax_assets);
            table_social_security_distribution = (TextView) tblRow.findViewById(R.id.table_social_security_distribution);
            table_retirement_withdrawal = (TextView) tblRow.findViewById(R.id.table_retirement_withdrawal);

            table_period.setText("" + myRetirement.getCashflow(i).getAge());
            table_pre_tax_assets.setText(new DecimalFormat("$##,##0").format(myRetirement.getCashflow(i).getPreIncomeTaxAssets()));
            table_post_tax_assets.setText(new DecimalFormat("$##,##0").format(myRetirement.getCashflow(i).getPostIncomeTaxAssets()));
            table_social_security_distribution.setText(new DecimalFormat("$##,##0").format(myRetirement.getCashflow(i).getSocialSecurityIncomeWithdrawal()));
            table_retirement_withdrawal.setText(new DecimalFormat("$##,##0").format(myRetirement.getCashflow(i).getTotalAfterTaxWithdrawal()));
            tableLayout.addView(tblRow);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        showRetirementCashflows();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_table, container, false);
        tableLayout = (TableLayout) rootView.findViewById(R.id.cashflow_table);
        tableHeaderLayout = (TableLayout) rootView.findViewById(R.id.cashflow_table_header);
        showRetirementCashflows();
        return rootView;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }
}