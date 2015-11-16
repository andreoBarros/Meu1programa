package andreotxai.busaodadepressaoz;

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

public class ActivityInicial extends AppCompatActivity {

    private Spinner spinnerEmpresa;
    private Spinner spinnerLinha;
    private AppCompatButton botaoProximo;

    final private String[] arraySpinner = new String[] {
            "<none>","Carris", "2", "3", "4", "5"
    };
    final private String[] arraySpinner2 = new String[] {
            "<none>","353", "343", "T9", "D43", "T10"
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
                android.R.layout.simple_spinner_item, arraySpinner);
        spinnerEmpresa.setAdapter(adapter);

        this.spinnerLinha = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySpinner2);
        spinnerLinha.setAdapter(adapter2);
        spinnerLinha.setOnItemSelectedListener(this.createSpinnerLinhaListener());
        // END

        //BOTÃO PROXIMO
        this.botaoProximo = (AppCompatButton) findViewById(R.id.botaoProximo);
        //Isso faz o botao só ativar quando algo é selecionado no spinnerEmpresa 2
        this.botaoProximo.setOnClickListener(this.createBotaoProximoClickListener());
        //END
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Isso faz o spinnerEmpresa 2 só ativar quando algo é selecionado no spinnerEmpresa 1
        spinnerEmpresa.setOnItemSelectedListener(this.createSpinnerEmpresaListener());
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

    private AdapterView.OnItemSelectedListener createSpinnerEmpresaListener() {
        return new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
                    spinner2.setEnabled(false);
                } else {
                    spinnerLinha.setEnabled(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        };
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

}
