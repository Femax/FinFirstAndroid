package ru.fedosov.opengifityhack.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ru.fedosov.opengifityhack.R;
import ru.fedosov.opengifityhack.client.model.Portfolio;
import ru.fedosov.opengifityhack.ui.view.LoginView;
import ru.fedosov.opengifityhack.ui.view.NewPortfolioView;

public class NewPortfolioActivity extends AppCompatActivity implements NewPortfolioView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_portfolio);
    }

    @Override
    public void onCreateResult(boolean success) {
        startActivity(new Intent(this, PortfolioDetailsActivity.class));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
