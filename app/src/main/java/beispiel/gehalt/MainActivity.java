package beispiel.gehalt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.IllegalFormatConversionException;

public class MainActivity extends AppCompatActivity {

    private Resources res;

    private EditText valStunden;
    private EditText valGehalt;
    private TextView lblLohn;

    private TextWatcher onGehaltChange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            double lohn = 0;

            try {
                double stunden = Double.parseDouble(valStunden.getText().toString());
                double gehalt = Double.parseDouble(valGehalt.getText().toString());
                lohn = gehalt / (stunden * 4);

            } catch (NumberFormatException ignored) {
            }

            String newLabel = res.getString(R.string.du_verdienst_d_euro_stunde, lohn);
            lblLohn.setText(newLabel);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = getResources();

        valStunden = findViewById(R.id.valStunden);
        valGehalt = findViewById(R.id.valGehalt);
        lblLohn = findViewById(R.id.lblLohn);

        valGehalt.addTextChangedListener(onGehaltChange);
        valStunden.addTextChangedListener(onGehaltChange);
        onGehaltChange.afterTextChanged(null);
    }


}
