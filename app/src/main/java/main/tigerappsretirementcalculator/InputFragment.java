package main.tigerappsretirementcalculator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * A placeholder fragment containing a simple view.
 */
public class InputFragment extends android.support.v4.app.Fragment implements SeekBar.OnSeekBarChangeListener {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private TextView mcurrentAgeSeekBarResult;
    private TextView mRetirementAgeSeekBarResult;
    private TextView mLifeExpectancySeekBarResult;
    private TextView mPreTaxAssetsSeekBarResult;
    private TextView mPreTaxContributionsSeekBarResult;
    private TextView mPostTaxAssetsSeekBarResult;
    private TextView mPostTaxContributionsSeekBarResult;
    private TextView mSocialSecuritySeekBarResult;
    private TextView mSocialSecurityAgeSeekBarResult;
    private TextView mAfterTaxWithdrawalSeekBarResult;
    private TextView mInputCashflowResults;

    private SeekBar mcurrentAgeSeekbar;
    private SeekBar mRetirementAgeSeekbar;
    private SeekBar mLifeExpectancySeekbar;
    private SeekBar mPreTaxAssetsSeekbar;
    private SeekBar mPreTaxContributionsSeekBar;
    private SeekBar mPostTaxAssetsSeekbar;
    private SeekBar mPostTaxContributionsSeekBar;
    private SeekBar mSocialSecuritySeekBar;
    private SeekBar mSocialSecurityAgeSeekBar;
    private SeekBar mAfterTaxWithdrawalSeekBar;

    public InputFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static InputFragment newInstance(int sectionNumber) {
        InputFragment fragment = new InputFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mcurrentAgeSeekbar = rootView.findViewById(R.id.currentAgeSeekbar);
        mcurrentAgeSeekBarResult = rootView.findViewById(R.id.currentAgeSeekBarResult);
        mcurrentAgeSeekbar.setOnSeekBarChangeListener(this);
        mRetirementAgeSeekbar = rootView.findViewById(R.id.RetirementAgeSeekbar);
        mRetirementAgeSeekBarResult = rootView.findViewById(R.id.RetirementAgeSeekBarResult);
        mRetirementAgeSeekbar.setOnSeekBarChangeListener(this);
        mLifeExpectancySeekbar = rootView.findViewById(R.id.LifeExpectancySeekbar);
        mLifeExpectancySeekBarResult = rootView.findViewById(R.id.LifeExpectancySeekBarResult);
        mLifeExpectancySeekbar.setOnSeekBarChangeListener(this);

        mPreTaxAssetsSeekbar = rootView.findViewById(R.id.PreTaxAssetsSeekbar);
        mPreTaxAssetsSeekBarResult = rootView.findViewById(R.id.PreTaxAssetsSeekBarResult);
        mPreTaxAssetsSeekbar.setOnSeekBarChangeListener(this);
        mPreTaxContributionsSeekBar = rootView.findViewById(R.id.PreTaxContributionsSeekbar);
        mPreTaxContributionsSeekBarResult = rootView.findViewById(R.id.PreTaxContributionsSeekBarResult);
        mPreTaxAssetsSeekBarResult.setText(new DecimalFormat("$##,##0").format(mPreTaxAssetsSeekbar.getProgress()*10000));
        mPreTaxContributionsSeekBar.setOnSeekBarChangeListener(this);
        mPreTaxContributionsSeekBarResult.setText(new DecimalFormat("$##,##0").format(mPreTaxContributionsSeekBar.getProgress()*1000));

        mPostTaxAssetsSeekbar = rootView.findViewById(R.id.PostTaxAssetsSeekbar);
        mPostTaxAssetsSeekBarResult = rootView.findViewById(R.id.PostTaxAssetsSeekBarResult);
        mPostTaxAssetsSeekbar.setOnSeekBarChangeListener(this);
        mPostTaxContributionsSeekBar = rootView.findViewById(R.id.PostTaxContributionsSeekbar);
        mPostTaxContributionsSeekBarResult = rootView.findViewById(R.id.PostTaxContributionsSeekBarResult);
        mPostTaxAssetsSeekBarResult.setText(new DecimalFormat("$##,##0").format(mPostTaxAssetsSeekbar.getProgress()*10000));
        mPostTaxContributionsSeekBar.setOnSeekBarChangeListener(this);
        mPostTaxContributionsSeekBarResult.setText(new DecimalFormat("$##,##0").format(mPostTaxContributionsSeekBar.getProgress()*1000));

        mSocialSecuritySeekBar= rootView.findViewById(R.id.SocialSecuritySeekbar);
        mSocialSecuritySeekBarResult= rootView.findViewById(R.id.SocialSecuritySeekBarResult);
        mSocialSecuritySeekBar.setOnSeekBarChangeListener(this);
        mSocialSecuritySeekBarResult.setText(new DecimalFormat("$##,##0").format(mSocialSecuritySeekBar.getProgress()*100));
        mSocialSecurityAgeSeekBar= rootView.findViewById(R.id.SocialSecurityAgeSeekbar);
        mSocialSecurityAgeSeekBarResult = rootView.findViewById(R.id.SocialSecurityAgeSeekBarResult);
        mSocialSecurityAgeSeekBar.setOnSeekBarChangeListener(this);
        mSocialSecurityAgeSeekBarResult.setText(new DecimalFormat("##").format(mSocialSecurityAgeSeekBar.getProgress()+62));

        mAfterTaxWithdrawalSeekBar = rootView.findViewById(R.id.AfterTaxWithdrawalSeekbar);
        mAfterTaxWithdrawalSeekBarResult = rootView.findViewById(R.id.AfterTaxWithdrawalSeekBarResult);
        mAfterTaxWithdrawalSeekBar.setOnSeekBarChangeListener(this);
        mAfterTaxWithdrawalSeekBarResult.setText(new DecimalFormat("##").format(mAfterTaxWithdrawalSeekBar.getProgress()*100));

        mInputCashflowResults = rootView.findViewById(R.id.InputCashflowResults);
        recalculateResult();

        return rootView;
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.currentAgeSeekbar:
                mcurrentAgeSeekBarResult.setText(String.format(getString(R.string.current_age_seek_bar_result_string), mcurrentAgeSeekbar.getProgress()));
                break;
            case R.id.RetirementAgeSeekbar:
                mRetirementAgeSeekBarResult.setText(String.format(getString(R.string.retirement_age_seek_bar_result_string), mRetirementAgeSeekbar.getProgress()));
                break;
            case R.id.LifeExpectancySeekbar:
                mLifeExpectancySeekBarResult.setText(String.format(getString(R.string.life_expectancy_seek_bar_result_string), mLifeExpectancySeekbar.getProgress()));
                break;
            case R.id.PreTaxAssetsSeekbar:
                mPreTaxAssetsSeekBarResult.setText(new DecimalFormat("$##,##0").format(mPreTaxAssetsSeekbar.getProgress()*10000));
                break;
            case R.id.PreTaxContributionsSeekbar:
                mPreTaxContributionsSeekBarResult.setText(new DecimalFormat("$##,##0").format(mPreTaxContributionsSeekBar.getProgress()*1000));
                break;
            case R.id.PostTaxAssetsSeekbar:
                mPostTaxAssetsSeekBarResult.setText(new DecimalFormat("$##,##0").format(mPostTaxAssetsSeekbar.getProgress()*10000));
                break;
            case R.id.PostTaxContributionsSeekbar:
                mPostTaxContributionsSeekBarResult.setText(new DecimalFormat("$##,##0").format(mPostTaxContributionsSeekBar.getProgress()*1000));
                break;
            case R.id.SocialSecuritySeekbar:
                mSocialSecuritySeekBarResult.setText(new DecimalFormat("$##,##0").format(mSocialSecuritySeekBar.getProgress()*100));
                break;
            case R.id.SocialSecurityAgeSeekbar:
                mSocialSecurityAgeSeekBarResult.setText(new DecimalFormat("##").format(mSocialSecurityAgeSeekBar.getProgress()+62));
                break;
            case R.id.AfterTaxWithdrawalSeekbar:
                mAfterTaxWithdrawalSeekBarResult.setText(new DecimalFormat("##").format(mAfterTaxWithdrawalSeekBar.getProgress()*100));
                break;
        }
        // calculate the cashflows
        recalculateResult();
    }

    // Recalculate results and show it
    public void recalculateResult() {
        RetirementPlanner myRetirement = new RetirementPlanner(mcurrentAgeSeekbar.getProgress(), mRetirementAgeSeekbar.getProgress(),
                mLifeExpectancySeekbar.getProgress(), mPreTaxAssetsSeekbar.getProgress() * 10000, mPreTaxContributionsSeekBar.getProgress() * 1000,
                mPostTaxAssetsSeekbar.getProgress() * 10000, mPostTaxContributionsSeekBar.getProgress() * 1000, mSocialSecuritySeekBar.getProgress() * 100,
                mSocialSecurityAgeSeekBar.getProgress() + 62, mAfterTaxWithdrawalSeekBar.getProgress() * 100);
        myRetirement.Calculate();
        double endOfLifeAssets = myRetirement.GetEndOfLifeAssets();
        if (endOfLifeAssets > 0)
            mInputCashflowResults.setText(String.format(getString(R.string.input_result), endOfLifeAssets));
        else
            mInputCashflowResults.setText(R.string.input_result_out_of_money);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}

