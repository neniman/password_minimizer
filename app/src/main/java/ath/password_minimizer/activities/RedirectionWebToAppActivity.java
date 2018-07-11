package ath.password_minimizer.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import Util.Constants;
import ath.password_minimizer.R;
import model.PasswordStrength;
import model.PicturePassword;

public class RedirectionWebToAppActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirection_web_to_app);

        List<String> path = getIntent().getData().getPathSegments();
        Uri uri = Uri.parse("smsto:" + path.get(1));
        PasswordStrength correctPassWordStrength = PasswordStrength.SIMPLE;
        displayCorrectPicturePassword(getAccordingPicturePassword(correctPassWordStrength));
    }

    /**
     * Returns the first picture password with the according strength.
     *
     * @param passwordStrength passed by website.
     * @return first picture pw with the according pw strength.
     */
    private PicturePassword getAccordingPicturePassword(PasswordStrength passwordStrength) {
        PicturePassword picturePasswordToShow = null;
        for (PicturePassword picturePassword : Constants.getCurrentPicturePasswordList(this)) {
            if (picturePassword.getPasswordStrength() == passwordStrength) {
                picturePasswordToShow = picturePassword;
            }
        }
        return picturePasswordToShow;
    }

    /**
     * Displays a picture password, which the user needs to unlock.
     *
     * @param currentPicturePassword the picture password which should be shown to the user.
     */
    private void displayCorrectPicturePassword(PicturePassword currentPicturePassword) {

    }
}