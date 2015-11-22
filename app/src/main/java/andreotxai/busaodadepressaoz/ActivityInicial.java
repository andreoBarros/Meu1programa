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
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import andreotxai.busaodadepressaoz.DAO.EmpresasDAO;
import andreotxai.busaodadepressaoz.model.Empresa;
import andreotxai.busaodadepressaoz.util.DataBaseValuesConvert;

public class ActivityInicial extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerEmpresa;
    private Spinner spinnerLinha;
    private AppCompatButton botaoProximo;
    private List<Empresa> listaEmpresa;
    private String spEmpresa = "";
    private String spLinha = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        try {
            DataBaseValuesConvert.mountDataTree(this);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Problema de leitura do banco de dados!", Toast.LENGTH_LONG).show();
        }

        // TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // END

        // SPINNERS
        this.spinnerEmpresa = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, DataBaseValuesConvert.arraySpinnerEmpresa);
        this.spinnerEmpresa.setAdapter(adapter);
        // SPINNERLINHA ESCUTA SPINNEREMPRESA
        this.spinnerLinha = (Spinner) findViewById(R.id.spinner2);
        spinnerEmpresa.setOnItemSelectedListener(this);
        this.spinnerLinha.setOnItemSelectedListener(this.createSpinnerLinhaListener());
        // END
        // END

        //BOTÃO PROXIMO
        this.botaoProximo = (AppCompatButton) findViewById(R.id.botaoProximo);
        //Isso faz o botao só ativar quando algo é selecionado no spinnerEmpresa 2
        this.botaoProximo.setOnClickListener(this.createBotaoProximoClickListener());
        //END

        // carregarEmpresas();
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
        Intent it = new Intent(ActivityInicial.this, ActivityPesquisa.class);
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
                spLinha = String.valueOf(spinnerLinha.getSelectedItem());

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
                it.putExtra("stringEmpresa", spEmpresa);
                it.putExtra("stringLinha", spLinha);
                startActivity(it);
            }

        };
    }

    // aqui é onde a mágica dos spinners acontece
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        spEmpresa = String.valueOf(spinnerEmpresa.getSelectedItem());
//        Toast.makeText(this, sp1, Toast.LENGTH_SHORT).show(); teste para ver o real valor de sp1

        switch(spEmpresa) {
            case "<none>":
                ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, DataBaseValuesConvert.vazio);
                this.spinnerLinha.setAdapter(adapter3);

                break;

            case "Carris":
                ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, DataBaseValuesConvert.arraySpinner2Carris);
                this.spinnerLinha.setAdapter(adapter4);
                break;

            case "Conorte":
                ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, DataBaseValuesConvert.arraySpinner2Conorte);
                this.spinnerLinha.setAdapter(adapter5);
                break;

            case "STS":
                ArrayAdapter<String> adapter6 = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, DataBaseValuesConvert.arraySpinner2Sts);
                this.spinnerLinha.setAdapter(adapter6);
                break;

            case "Unibus":
                ArrayAdapter<String> adapter7 = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, DataBaseValuesConvert.arraySpinner2Unibus);
                this.spinnerLinha.setAdapter(adapter7);
                break;
        }

    }
    //end

    private void carregarEmpresas() {
        EmpresasDAO dao = new EmpresasDAO();
        try {
            String empresaDBvalue = dao.lerDataBase(this);
            if (empresaDBvalue.length() == 0) {
                dao.insereEmpresas(this);
                dao.lerDataBase(this);
            } else {
                this.listaEmpresa = DataBaseValuesConvert.databaseToApplicationEmpresas(empresaDBvalue);
                String[] empresas = new String[listaEmpresa.size()+1];
                empresas[0] = "<none>";
                for(int i = 0 ; i <= (listaEmpresa.size()-1) ; i++) {
                    empresas[i+1] = listaEmpresa.get(i).getNome();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, empresas);
                this.spinnerEmpresa.setAdapter(adapter);
                // Toast.makeText(this, listaEmpresa.size(), Toast.LENGTH_LONG).show();
            }
            // Toast.makeText(this, String.valueOf(empresaDBvalue.length()), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Problema de leitura!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
