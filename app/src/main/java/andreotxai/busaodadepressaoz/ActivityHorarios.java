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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import andreotxai.busaodadepressaoz.util.DataBaseValuesConvert;

public class ActivityHorarios extends AppCompatActivity {

    Calendar cal;


    private Button btnData;
    private Button btnProximo;
    private TextView txtViewData;
    private Spinner spinnerHorarios;
    private DialogFragment dateFragment;

    private String tmpEmpresa;
    private String tmpLinha;
    private String data;
    private String horario;

    final private static String STRING_MENSAGEM = "Data inválida!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //SETA DE VOLTAR
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tmpEmpresa = getIntent().getExtras().getString("stringEmpresa","defaultKey");
        tmpLinha = getIntent().getExtras().getString("stringLinha","defaultKey");


        this.btnProximo = (Button) findViewById(R.id.botaoAvancar);
        this.btnProximo.setOnClickListener(this.createBotaoProximoClickListener());

        this.btnData = (Button) findViewById(R.id.botaoData);
        this.btnData.setOnClickListener(this.createBotaoDataClickListener());

        this.txtViewData = (TextView) findViewById(R.id.textViewData);

        this.spinnerHorarios = (Spinner) findViewById(R.id.spinnerHorarios);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, DataBaseValuesConvert.arraySpinnerHorario);
        this.spinnerHorarios.setAdapter(adapter);
        this.spinnerHorarios.setOnItemSelectedListener(this.createSpinnerEmpresaListener());

        this.cal = Calendar.getInstance();
        this.updateTextViewContent(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));

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

    private AdapterView.OnItemSelectedListener createSpinnerEmpresaListener() {
        return new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                horario = String.valueOf(spinnerHorarios.getSelectedItem());

                if (position == 0) {
                    btnProximo.setEnabled(false);
                } else {
                    btnProximo.setEnabled(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        };
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
                String strData = txtViewData.getText().toString();
                Intent it = new Intent(ActivityHorarios.this, ActivityAvaliacao.class);
                it.putExtra("finalEmpresa", tmpEmpresa);
                it.putExtra("finalLinha", tmpLinha);
                it.putExtra("finalHorario", horario);
                it.putExtra("finalData", strData);
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

            final Calendar ce = Calendar.getInstance();
            final int yearDeHoje = ce.get(Calendar.YEAR);
            final int monthDeHoje = ce.get(Calendar.MONTH);
            final int dayDeHoje = ce.get(Calendar.DAY_OF_MONTH);

            if (checkValidData(day, month, year)) {
                cal.set(Calendar.YEAR, yearDeHoje);
                cal.set(Calendar.MONTH, monthDeHoje);
                cal.set(Calendar.DAY_OF_MONTH, dayDeHoje);

                updateTextViewContent(day, month, year);
            } else {
                Toast.makeText(ActivityHorarios.this,STRING_MENSAGEM, Toast.LENGTH_LONG).show();
            }
        }
    }
}

