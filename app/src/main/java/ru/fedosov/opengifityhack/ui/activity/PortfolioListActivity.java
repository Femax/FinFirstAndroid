package ru.fedosov.opengifityhack.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.fedosov.opengifityhack.R;
import ru.fedosov.opengifityhack.client.RestClient;
import ru.fedosov.opengifityhack.client.model.Portfolio;
import ru.fedosov.opengifityhack.ui.presenter.PortfolioListPresenter;
import ru.fedosov.opengifityhack.ui.view.PortfolioListView;
import ru.fedosov.opengifityhack.utils.PrefUtils;

public class PortfolioListActivity extends AppCompatActivity implements PortfolioListView {


    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.change_balance_button_text)
    TextView mBalanceButtonText;

    PortfolioListPresenter mPortfolioPresenter;
    private PorfolioAdapter mPortfoliosAdapter;

    private boolean isBalanceRub = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        ButterKnife.bind(this);
        mPortfolioPresenter = new PortfolioListPresenter(this);
    }

    @OnClick(R.id.add_portfolio)
    public void createPortfolio() {
        startActivity(new Intent(this, CreatePortfolioActivity.class));
        finish();
    }

    @OnClick(R.id.change_balance)
    public void changeBlance() {
        mPortfolioPresenter.getCurrency();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBalanceButtonText.setText(isBalanceRub ? getString(R.string.rub) : getString(R.string.usd));
        mPortfolioPresenter.getPortfolios(PrefUtils.getString(R.string.pref_user_id));
    }


    public void onGetPortfoioResult(List<Portfolio> portfolios) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPortfoliosAdapter = new PorfolioAdapter(portfolios);
        recyclerView.setAdapter(mPortfoliosAdapter);
        if (portfolios.size() == 0)
            Toast.makeText(this, R.string.no_portfolio_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetCurrency(Double currency) {
        mPortfoliosAdapter.updateBalance(currency);
        mBalanceButtonText.setText(isBalanceRub ? getString(R.string.rub) : getString(R.string.usd));
    }

    private class PorfolioAdapter extends RecyclerView.Adapter {
        List<Portfolio> portfolios;

        public PorfolioAdapter(List<Portfolio> portfolios) {
            this.portfolios = portfolios;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PortfolioViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_portfolio, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((PortfolioViewHolder) holder).applyData(portfolios.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),DetailPortfolio.class));
                }
            });
        }

        @Override
        public int getItemCount() {
            return portfolios.size();
        }

        public void updateBalance(Double currency) {
            for (Portfolio i : portfolios) {
                if (isBalanceRub)
                    i.setRealBalance(Math.round(Double.parseDouble(i.getRealBalance()) / currency) + "");
                else
                    i.setRealBalance(Math.round(Double.parseDouble(i.getRealBalance()) * currency) + "");
            }
            isBalanceRub = !isBalanceRub;
            notifyDataSetChanged();
        }
    }

    class PortfolioViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.account)
        TextView account;
        @Bind(R.id.realBalance)
        TextView realBalance;


        public PortfolioViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void applyData(Portfolio portfolio) {
            name.setText(portfolio.getName());
            account.setText(portfolio.getType() ? "" : getString(R.string.demo_account));
            realBalance.setText(isBalanceRub ? getString(R.string.balanceRuType, Double.parseDouble(portfolio.getRealBalance()))
                    : getString(R.string.balanceUsdType, Double.parseDouble(portfolio.getRealBalance())));
        }
    }

}
