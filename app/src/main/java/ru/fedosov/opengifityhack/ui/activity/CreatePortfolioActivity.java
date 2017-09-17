package ru.fedosov.opengifityhack.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.fedosov.opengifityhack.R;
import ru.fedosov.opengifityhack.client.model.AttrCreatePortfolio;
import ru.fedosov.opengifityhack.client.model.Portfolio;
import ru.fedosov.opengifityhack.ui.presenter.CreatePortfolioPresenter;
import ru.fedosov.opengifityhack.ui.presenter.PortfolioDetailsPresenter;
import ru.fedosov.opengifityhack.ui.view.CreatePortfolioView;
import ru.fedosov.opengifityhack.ui.view.PortfolioListView;
import ru.fedosov.opengifityhack.utils.PrefUtils;

public class CreatePortfolioActivity extends AppCompatActivity implements CreatePortfolioView {

    public Boolean currencyTypeUsd = true;

    @Bind(R.id.portfolio_name)
    EditText mPortfolioName;
    @Bind(R.id.active_switch)
    SwitchCompat mActiveSwitch;
    @Bind(R.id.chose_investments_duration)
    SeekBar mChoseInvestmentsDuration;
    @Bind(R.id.risky_risk)
    CheckedTextView mRiskyCheck;
    @Bind(R.id.optimal_risk)
    CheckedTextView mOptimalCheck;
    @Bind(R.id.conservative_risk)
    CheckedTextView mConservativeCheck;
    @Bind(R.id.currency)
    Switch mCurrency;
    @Bind(R.id.send_button)
    Button mSendButton;

    CreatePortfolioPresenter mCreatePortfolioPresenter;
    private ProgressDialog mProgresDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_portfolio);
        ButterKnife.bind(this);

        initBehavior();
        mCreatePortfolioPresenter = new CreatePortfolioPresenter(this);
    }

    @OnClick(R.id.send_button)
    public void onSend() {

        AttrCreatePortfolio attrCreatePortfolio = new AttrCreatePortfolio();
        attrCreatePortfolio.setAccountType(mActiveSwitch.isChecked());
        attrCreatePortfolio.setCurrency(mCurrency.isChecked() ? "usd" : "rub");
        attrCreatePortfolio.setPeriod(3);
        attrCreatePortfolio.setId(PrefUtils.getString(R.string.pref_user_id));
        attrCreatePortfolio.setPortfelName(mPortfolioName.getText().toString());
        attrCreatePortfolio.setRisk(mConservativeCheck.isChecked() ? 0.0 :
                mOptimalCheck.isChecked() ? 0.5 :
                        mRiskyCheck.isChecked() ? 0.8 : 0.0);
        mCreatePortfolioPresenter.createPortfolio(attrCreatePortfolio);
        showProgressDialog();
    }

    public void showProgressDialog() {
        mProgresDialog = new ProgressDialog(this);
        mProgresDialog.setMessage(getString(R.string.on_create_portfolio_message));
        mProgresDialog.show();
    }

    private void initBehavior() {
        mRiskyCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckedTextView checkedTextView = (CheckedTextView) view;
                mRiskyCheck.setChecked(!mRiskyCheck.isChecked());
                if (checkedTextView.isChecked()) {
                    mOptimalCheck.setChecked(false);
                    mConservativeCheck.setChecked(false);
                }
            }
        });
        mConservativeCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckedTextView checkedTextView = (CheckedTextView) view;
                mConservativeCheck.setChecked(!mConservativeCheck.isChecked());
                if (checkedTextView.isChecked()) {
                    mOptimalCheck.setChecked(false);
                    mRiskyCheck.setChecked(false);
                }
            }
        });
        mOptimalCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckedTextView checkedTextView = (CheckedTextView) view;
                mOptimalCheck.setChecked(!mOptimalCheck.isChecked());
                if (checkedTextView.isChecked()) {
                    mRiskyCheck.setChecked(false);
                    mConservativeCheck.setChecked(false);
                }
            }
        });
    }

    @Override
    public void onCreateResult(boolean success) {
        mProgresDialog.dismiss();
        startActivity(new Intent(this, PortfolioListActivity.class));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
