package andreotxai.busaodadepressaoz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityInicial extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerEmpresa;
    Spinner spinnerLinha;
    private AppCompatButton botaoProximo;

    final private String[] arraySpinner = new String[] {
            "<none>","Carris", "Conorte", "STS", "Unibus"
    };
    final private String[] vazio = new String[] {
            "<nenhuma empresa selecionada>"
    };
    final private String[] arraySpinner2Carris = new String[] {
            "<none>","353 - IPIRANGA / PUC", "343 - CAMPUS / IPIRANGA", "T9 - PUC"
            , "D43 - UNIVERSITARIA-DIRETA", "T10 - TRIANGULO / ANTONIO DE CARVALHO"
            , "T1 - TRANSVERSAL1", "T1D - T1 DIRETA"
    };
    final private String[] arraySpinner2Conorte = new String[] {
            "<none>", "637 - CHACARA DAS PEDRAS", "B09 - AEROPORTO/IGUATEMI"
            , "TR60 - TRONCAL TRIANGULO", "608 - IAPI", "611 - LINDOIA"
            , "615 - SARANDI", "617 - IGUATEMI"
    };
    final private String[] arraySpinner2Sts = new String[] {
            "<none>", "165 - COHAB", "178 - PRAIA DE BELAS", "179 - SERRARIA"
            , "187 - PADRE REUS", "209 - RESTINGA", "260 - BELEM VELHO/OSCAR PEREIRA"
            , "267 - ORFANATROFIO"
    };
    final private String[] arraySpinner2Unibus = new String[] {
            "<none>","314 - PUC RESTINGA", "340 - JARDIM BOTANICO", "344 - SANTA MARIA"
            , "346 - SAO JOSE", "347 - ALAMEDA", "348 - JARDIM BENTO GONCALVES"
            , "360 - I P E"
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);


        // TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // END

        // SPINNERS
        this.spinnerEmpresa = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, this.arraySpinner);
        this.spinnerEmpresa.setAdapter(adapter);

        this.spinnerLinha = (Spinner) findViewById(R.id.spinner2);
        spinnerEmpresa.setOnItemSelectedListener(this);
        this.spinnerLinha.setOnItemSelectedListener(this.createSpinnerLinhaListener());
        // END

        //BOTÃO PROXIMO
        this.botaoProximo = (AppCompatButton) findViewById(R.id.botaoProximo);
        //Isso faz o botao só ativar quando algo é selecionado no spinnerEmpresa 2
        this.botaoProximo.setOnClickListener(this.createBotaoProximoClickListener());
        //END
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_inicial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent it = new Intent(ActivityInicial.this, ActivityHorarios.class);
        //     it.setClass(null, ActivityHorarios.class);
        startActivity(it);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private AdapterView.OnItemSelectedListener createSpinnerLinhaListener() {
        return new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    botaoProximo.setEnabled(false);
                } else {
                    botaoProximo.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        };
    }

    private View.OnClickListener createBotaoProximoClickListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivityInicial.this, ActivityHorarios.class);
                startActivity(it);
            }

        };
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String sp1 = String.valueOf(spinnerEmpresa.getSelectedItem());
//        Toast.makeText(this, sp1, Toast.LENGTH_SHORT).show(); teste para ver o real valor de sp1

        switch(sp1) {
            case "<none>":
                ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, this.vazio);
                this.spinnerLinha.setAdapter(adapter3);
                break;

            case "Carris":
                ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, this.arraySpinner2Carris);
                this.spinnerLinha.setAdapter(adapter4);
                break;

            case "Conorte":
                ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, this.arraySpinner2Conorte);
                this.spinnerLinha.setAdapter(adapter5);
                break;

            case "STS":
                ArrayAdapter<String> adapter6 = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, this.arraySpinner2Sts);
                this.spinnerLinha.setAdapter(adapter6);
                break;

            case "Unibus":
                ArrayAdapter<String> adapter7 = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, this.arraySpinner2Unibus);
                this.spinnerLinha.setAdapter(adapter7);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
