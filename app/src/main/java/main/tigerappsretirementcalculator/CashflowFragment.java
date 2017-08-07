package main.tigerappsretirementcalculator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TableLayout;

import java.text.DecimalFormat;

/**
 * Created by Hartmut on 8/5/2017.
 */

public class CashflowFragment extends android.support.v4.app.Fragment implements SeekBar.OnSeekBarChangeListener {

    private TableLayout tableLayout;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_table, container, false);
        super.onCreate(savedInstanceState);
        TableLayout tableLayout = (TableLayout) rootView.findViewById(R.id.cashflow_table);

        for (int i = 0; i < 20; i++) {
            View tableRow = inflater.inflate(R.layout.table_item,null,false);
            TextView table_period = (TextView) tableRow.findViewById(R.id.table_period);
            TextView table_pre_tax_assets = (TextView) tableRow.findViewById(R.id.table_pre_tax_assets);
            TextView table_post_tax_assets = (TextView) tableRow.findViewById(R.id.table_post_tax_assets);
            TextView table_pre_tax_contribution = (TextView) tableRow.findViewById(R.id.table_pre_tax_contribution);

            table_period.setText("" + (i + 1));
            table_pre_tax_assets.setText("2016-09-09");
            table_post_tax_assets.setText("A00" + (i + 1));
            table_pre_tax_contribution.setText("" + (100 + (i + 1)));
            tableLayout.addView(tableRow);
        }
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