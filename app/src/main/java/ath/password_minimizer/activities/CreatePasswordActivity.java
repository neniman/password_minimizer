package ath.password_minimizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import Util.Constants;
import ath.password_minimizer.R;
import model.PasswordStrength;

/**
 * User chooses password strength.
 */
public class CreatePasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        setupOnClickEvents();
        //TODO: check which password types already exist and show here only the possible ones

        initBurgerMenu();
    }

    private void setupOnClickEvents() {
        findViewById(R.id.linearLayout_pw_simple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatePasswordActivity.this, CreatePWStep1Activity.class);
                intent.putExtra(Constants.CHOSEN_PW_STRENGTH, PasswordStrength.SIMPLE);
                startActivity(intent);
            }
        });

        findViewById(R.id.linearLayout_pw_middle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatePasswordActivity.this, CreatePWStep1Activity.class);
                intent.putExtra(Constants.CHOSEN_PW_STRENGTH, PasswordStrength.MIDDLE);
                startActivity(intent);
            }
        });

        findViewById(R.id.linearLayout_pw_strong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatePasswordActivity.this, CreatePWStep1Activity.class);
                intent.putExtra(Constants.CHOSEN_PW_STRENGTH, PasswordStrength.STRONG);
                startActivity(intent);
            }
        });
    }
}
