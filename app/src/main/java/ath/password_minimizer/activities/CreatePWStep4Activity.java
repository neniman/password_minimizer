package ath.password_minimizer.activities;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import Util.Constants;
import ath.password_minimizer.R;
import model.NumberGridGenerator;
import model.Vector2;

public class CreatePWStep4Activity extends AppCompatActivity implements View.OnTouchListener
{
    private TextView centerNumberView;
    private TextView backgroundNumberMatrixView;
    private ViewGroup root;
    private float xDelta;
    private float yDelta;
    private float scale;
    private NumberGridGenerator numberGridGenerator;

    private Vector2 startPosition = new Vector2();
    private Vector2 positionDifference = new Vector2();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pwstep4);
        numberGridGenerator = new NumberGridGenerator(this);

        root = (ViewGroup) findViewById(R.id.root);

        this.scale = getResources().getDisplayMetrics().density;

        ImageView numberGridView = findViewById(R.id.numberGrid);
        numberGridView.setOnTouchListener(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        Bundle bundle = getIntent().getExtras();
        Uri imageUri = (Uri) bundle.get(Constants.CHOSEN_IMAGE);
        String chosenNumber = (String) bundle.get(Constants.CHOSEN_NUM);

        Bitmap passwordImage = getPasswordImage(imageUri);
        setDataAndViewElements(passwordImage, chosenNumber);
        numberGridGenerator.generateHighlightedNumberMatrix(Integer.parseInt(chosenNumber), (ImageView)findViewById(R.id.numberGrid));

        ImageView numberGridView = findViewById(R.id.numberGrid);

        startPosition.x = numberGridView.getX();
        startPosition.y = numberGridView.getY();
    }

    private void setDataAndViewElements(Bitmap passwordImage, String chosenNumber)
    {
        ImageView passwordImageContainer;

        passwordImageContainer = findViewById(R.id.passwordImageContainer);
        passwordImageContainer.setImageBitmap(passwordImage);

    }

    private Bitmap getPasswordImage(Uri imageUri)
    {
        String[] filePath = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(imageUri, filePath, null, null, null);
        cursor.moveToFirst();
        String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

        // At the end remember to close the cursor or you will end with the RuntimeException!
        cursor.close();

        return bitmap;
    }

    public boolean onTouch(View view, MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                xDelta = view.getX() - event.getRawX();
                yDelta = view.getY() - event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                view.animate()
                        .x(event.getRawX() + xDelta)
                        .y(event.getRawY() + yDelta)
                        .setDuration(0)
                        .start();
                break;
            case MotionEvent.ACTION_UP:
                positionDifference.x = view.getX() - startPosition.x;
                positionDifference.y = view.getY() - startPosition.y;
                findViewById(R.id.numberGrid).setOnTouchListener(null);
                break;
            default:
                return false;
        }
        return true;
    }

}
