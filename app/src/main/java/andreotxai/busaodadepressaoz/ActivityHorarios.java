package andreotxai.busaodadepressaoz;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityHorarios extends AppCompatActivity {

    Calendar cal;

    private Button btnData;
    private Button btnProximo;
    private TextView txtViewData;
    private DialogFragment dateFragment;

    final private static String STRING_MENSAGEM = "Data inválida!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.btnProximo = (Button) findViewById(R.id.botaoAvancar);
        this.btnProximo.setOnClickListener(this.createBotaoProximoClickListener());
        this.btnData = (Button) findViewById(R.id.botaoData);
        this.btnData.setOnClickListener(this.createBotaoDataClickListener());
        this.txtViewData = (TextView) findViewById(R.id.textViewData);

        this.cal = Calendar.getInstance();
        updateTextViewContent(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
    }

    public void showDatePickerDialog(View v) {
        dateFragment = new DatePickerFragment();
        dateFragment.show(getFragmentManager(), "datePicker");
    }

    private String formatDataText(int day, int month, int year) {
        return day + "/" + (month + 1) + "/" + year;
    }

    private boolean checkValidData(int day, int month, int year) {
        return ((year <= cal.get(Calendar.YEAR))
                && (month <= cal.get(Calendar.MONTH))
                && (day <= cal.get(Calendar.DAY_OF_MONTH)));
    }

    private void updateTextViewContent(int day, int month, int year) {
        this.txtViewData.setText(this.formatDataText(day,month,year));
    }

    private View.OnClickListener createBotaoDataClickListener () {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        };
    }

    private View.OnClickListener createBotaoProximoClickListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 Intent it = new Intent(ActivityHorarios.this, ActivityAvaliacao.class);
                 startActivity(it);
            }

        };
    }

    class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            if (checkValidData(day, month, year)) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, day);

                updateTextViewContent(day, month, year);
            } else {
                Toast.makeText(ActivityHorarios.this,STRING_MENSAGEM, Toast.LENGTH_LONG).show();
            }
        }
    }
}

