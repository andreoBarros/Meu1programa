package andreotxai.busaodadepressaoz;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;

import andreotxai.busaodadepressaoz.DAO.AvaliacoesDAO;

public class ActivityPesquisa extends AppCompatActivity {

    Calendar cal;


    private Button psqBtnData;
    private Button btnPesquisar;
    private TextView txtViewData;
    private Spinner pesquisaHorarios;
    private DialogFragment dateFragment;
    public RadioGroup radioPesquisa;
    private RadioButton radioHorario, radioDia, radioEmpresa;
    private CheckBox Carris, Unibus, Sts, Conorte;

    final private String[] arraySpinner = new String[] {
            "<none>","08:00", "08:20", "08:40", "09:00", "09:20"
    };
    final private static String STRING_MENSAGEM = "Data inválida!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.Carris = (CheckBox) findViewById(R.id.checkCarris);
        this.Conorte = (CheckBox) findViewById(R.id.checkConorte);
        this.Sts = (CheckBox) findViewById(R.id.checkSTS);
        this.Unibus = (CheckBox) findViewById(R.id.checkUnibus);

        //DEFINIÇÕES PARA O DATEPIKCER
        this.psqBtnData = (Button) findViewById(R.id.pesquisaData);
        this.psqBtnData.setOnClickListener(this.createBotaoDataClickListener());
        this.txtViewData = (TextView) findViewById(R.id.pesquisaViewData);
        this.cal = Calendar.getInstance();
        this.updateTextViewContent(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
        //END
        //HORARIOS
        this.pesquisaHorarios = (Spinner) findViewById(R.id.pesquisaHorarios);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, this.arraySpinner);
        this.pesquisaHorarios.setAdapter(adapter);
        //END
        //OS SELECIONADORES DE PESQUISA

        RadioGroup radioPesquisa = (RadioGroup) findViewById(R.id.radioPesquisa);

        int id = radioPesquisa.getCheckedRadioButtonId();


        if (id == -1) {
            psqBtnData.setClickable(false);
            pesquisaHorarios.setClickable(false);
            Carris.setClickable(false);
            Conorte.setClickable(false);
            Sts.setClickable(false);
            Unibus.setClickable(false);
        } else {
            if (id == R.id.radioHorario) {
                psqBtnData.setClickable(false);
                pesquisaHorarios.setClickable(true);
                Carris.setClickable(false);
                Conorte.setClickable(false);
                Sts.setClickable(false);
                Unibus.setClickable(false);
                psqBtnData.setVisibility(View.INVISIBLE);
                pesquisaHorarios.setVisibility(View.VISIBLE);
                Carris.setVisibility(View.INVISIBLE);
                Conorte.setVisibility(View.INVISIBLE);
                Sts.setVisibility(View.INVISIBLE);
                Unibus.setVisibility(View.INVISIBLE);
            }

            //END
            //BOTAO DE PESQUISAR
            //this.btnPesquisar.setOnClickListener(this.createBotaoPesquisarClickListener());
            //END
        }
    }

    //DATE PICKER DIALOG
    private View.OnClickListener createBotaoDataClickListener () {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        };
    }
    public void showDatePickerDialog(View v) {
        dateFragment = new DatePickerFragment();
        dateFragment.show(getFragmentManager(), "datePicker");
    }
    private boolean checkValidData(int day, int month, int year) {
        return ((year <= cal.get(Calendar.YEAR))
                && (month <= cal.get(Calendar.MONTH))
                && (day <= cal.get(Calendar.DAY_OF_MONTH)));
    }
    private void updateTextViewContent(int day, int month, int year) {
        this.txtViewData.setText(this.formatDataText(day,month,year));
    }
    private String formatDataText(int day, int month, int year) {
        return day + "/" + (month + 1) + "/" + year;
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
                Toast.makeText(ActivityPesquisa.this, STRING_MENSAGEM, Toast.LENGTH_LONG).show();
            }
        }
    }
    //END
        //PARA BUSCAR A AVALICAO
  /*  private View.OnClickListener createBotaoPesquisarClickListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                readAvaliacao();
            }

        };
    }
    private void readAvaliacao() {
        AvaliacoesDAO dao = new AvaliacoesDAO();
        try {
            String teste = dao.lerAvaliacaoDataBase(this);
            Toast.makeText(this, teste, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Problema de leitura!", Toast.LENGTH_LONG).show();
        }
    }
    //END */

}