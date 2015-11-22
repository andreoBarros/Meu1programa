package andreotxai.busaodadepressaoz;

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

public class ActivityPesquisa extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

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
        //SETA DE VOLTAR
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //BOTÃO PESQUISAR
        this.btnPesquisar = (Button) findViewById(R.id.btnPesquisar);
        this.btnPesquisar.setOnClickListener(this.createBotaoPesquisarClickListener());
        //DEFINIÇÕES PARA AS EMPRESAS
        this.Carris = (CheckBox) findViewById(R.id.checkCarris);
        this.Conorte = (CheckBox) findViewById(R.id.checkConorte);
        this.Sts = (CheckBox) findViewById(R.id.checkSTS);
        this.Unibus = (CheckBox) findViewById(R.id.checkUnibus);

        //END
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
        //LISTENERS
        btnPesquisar.setClickable(false);
        pesquisaHorarios.setOnItemSelectedListener(this);
        selecionaDia(0);
        //OS SELECIONADORES DE PESQUISA
        disabilitaTudoInicio();
        RadioGroup radioPesquisa = (RadioGroup) findViewById(R.id.radioPesquisa);
        radioPesquisa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            funcaoSwitch(checkedId);
            }
        });
        //END
        //enfim o botão faz algo :)
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
        this.txtViewData.setText(this.formatDataText(day, month, year));
    }
    private String formatDataText(int day, int month, int year) {
        return day + "/" + (month + 1) + "/" + year;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long checkedId) {
        habilitaPesquisar(position);
    }
    public boolean selecionaEmpresa(){
        if (Carris.isChecked()||Conorte.isChecked()||Sts.isChecked()||Unibus.isChecked()) {
            return true;
        }
        return false;
    }
    public boolean selecionaHorario(int position){
        if (position != 0){
            return true;
        }
        return false;
    }
    public boolean selecionaDia(int checkedId){
        switch (checkedId){
            case R.id.radioDia:
                return true;
            default:
                return false;
        }
    }

    public void habilitaPesquisar(int position){
        if(selecionaEmpresa()||selecionaHorario(position)||selecionaDia(position)){
            btnPesquisar.setClickable(true);
        }
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
    private View.OnClickListener createBotaoPesquisarClickListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                readAvaliacao();
                Intent it = new Intent(ActivityPesquisa.this, ActivityPesquisaResultado.class);
                startActivity(it);
            }

        };
    }
    //fazem a habilitação e desabilitação dos radio buttons
    public void disabilitaTudoInicio(){
        psqBtnData.setClickable(false);
        pesquisaHorarios.setClickable(false);
        Carris.setClickable(false);
        Conorte.setClickable(false);
        Sts.setClickable(false);
        Unibus.setClickable(false);
        btnPesquisar.setClickable(false);
        txtViewData.setVisibility(View.INVISIBLE);
        psqBtnData.setVisibility(View.INVISIBLE);
        pesquisaHorarios.setVisibility(View.INVISIBLE);
        Carris.setVisibility(View.INVISIBLE);
        Conorte.setVisibility(View.INVISIBLE);
        Sts.setVisibility(View.INVISIBLE);
        Unibus.setVisibility(View.INVISIBLE);
    }//disabilitaTudoInicio() TAMBÉM DESABILITA O BOTÃO PESQUISAR!!!!!!!
    public void tiraCheckbox(){
        Carris.setChecked(false);
        Conorte.setChecked(false);
        Sts.setChecked(false);
        Unibus.setChecked(false);
    }
    public void companiasDeOnibus(int checkedId){
        switch(checkedId) {
            case R.id.radioEmpresa:
                Carris.setVisibility(View.VISIBLE);
                Conorte.setVisibility(View.VISIBLE);
                Sts.setVisibility(View.VISIBLE);
                Unibus.setVisibility(View.VISIBLE);
                break;
            default:
                Carris.setVisibility(View.INVISIBLE);
                Conorte.setVisibility(View.INVISIBLE);
                Sts.setVisibility(View.INVISIBLE);
                Unibus.setVisibility(View.INVISIBLE);
        }
    }
    public void funcaoSwitch(int checkedId){
        switch(checkedId) {
            case R.id.radioHorario:
                psqBtnData.setClickable(false);
                pesquisaHorarios.setClickable(true);
                Carris.setClickable(false);
                Conorte.setClickable(false);
                Sts.setClickable(false);
                Unibus.setClickable(false);
                txtViewData.setVisibility(View.INVISIBLE);
                psqBtnData.setVisibility(View.INVISIBLE);
                pesquisaHorarios.setVisibility(View.VISIBLE);
                companiasDeOnibus(checkedId);
                habilitaPesquisar(checkedId);
                tiraCheckbox();
                break;
            case R.id.radioDia:
                habilitaPesquisar(0);
                psqBtnData.setClickable(true);
                pesquisaHorarios.setClickable(false);
                Carris.setClickable(false);
                Conorte.setClickable(false);
                Sts.setClickable(false);
                Unibus.setClickable(false);
                txtViewData.setVisibility(View.VISIBLE);
                psqBtnData.setVisibility(View.VISIBLE);
                pesquisaHorarios.setVisibility(View.INVISIBLE);
                companiasDeOnibus(checkedId);
                habilitaPesquisar(checkedId);
                break;
            case R.id.radioEmpresa:
                habilitaPesquisar(0);
                psqBtnData.setClickable(false);
                pesquisaHorarios.setClickable(false);
                Carris.setClickable(true);
                Conorte.setClickable(true);
                Sts.setClickable(true);
                Unibus.setClickable(true);
                txtViewData.setVisibility(View.INVISIBLE);
                psqBtnData.setVisibility(View.INVISIBLE);
                pesquisaHorarios.setVisibility(View.INVISIBLE);
                companiasDeOnibus(checkedId);
                habilitaPesquisar(checkedId);
                tiraCheckbox();
                break;

        }
    }
    //END

    private void readAvaliacao() {
        AvaliacoesDAO dao = new AvaliacoesDAO();
        try {
            String teste = dao.lerDataBase(this);
            Toast.makeText(this, teste, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Problema de leitura!", Toast.LENGTH_LONG).show();
        }
    }
    //END

}