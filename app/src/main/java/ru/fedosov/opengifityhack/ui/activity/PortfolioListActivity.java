package ru.fedosov.opengifityhack.ui.activity;

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
import ru.fedosov.opengifityhack.client.model.Portfolio;
import ru.fedosov.opengifityhack.ui.presenter.PortfolioListPresenter;
import ru.fedosov.opengifityhack.ui.view.PortfolioListView;

public class PortfolioListActivity extends AppCompatActivity implements PortfolioListView {

    PortfolioListPresenter mPortfolioPresenter;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        ButterKnife.bind(this);
        mPortfolioPresenter = new PortfolioListPresenter(this);
        mPortfolioPresenter.getPortfolios();
    }

    @OnClick(R.id.add_portfolio)
    public void createPortfolio() {

    }

    @OnClick(R.id.change_balance)
    public void changeBlance() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPortfolioPresenter.getPortfolios();
    }


    public void onGetPortfoioResult(List<Portfolio> portfolios) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PorfolioAdapter(portfolios));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
        }

        @Override
        public int getItemCount() {
            return portfolios.size();
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
            realBalance.setText(portfolio.getRealBalance());
        }
    }

}
