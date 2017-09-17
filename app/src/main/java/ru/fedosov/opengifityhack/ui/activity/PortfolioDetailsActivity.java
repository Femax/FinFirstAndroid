package ru.fedosov.opengifityhack.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.fedosov.opengifityhack.R;
import ru.fedosov.opengifityhack.client.model.History;
import ru.fedosov.opengifityhack.ui.view.PortfolioDetailsView;

public class PortfolioDetailsActivity extends AppCompatActivity implements PortfolioDetailsView {

    @Bind(R.id.portfolio_details)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_details);
        ButterKnife.bind(this);
    }

    private class HistoryAdapter extends RecyclerView.Adapter {
        List<History> history;

        public HistoryAdapter(List<History> history) {
            this.history = history;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new HistoryViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_history, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((HistoryViewHolder) holder).applyData(history.get(position));
        }


        @Override
        public int getItemCount() {
            return history.size();
        }
    }


    class HistoryViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.company_name)
        TextView companyName;
        @Bind(R.id.realBalance)
        TextView percent;


        public HistoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void applyData(History history) {

        }


    }
}
