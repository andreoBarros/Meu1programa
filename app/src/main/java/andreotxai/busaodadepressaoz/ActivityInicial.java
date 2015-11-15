package andreotxai.busaodadepressaoz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

    private Spinner spinner;
    private Spinner spinner2;
    private String[] arraySpinner;
    private String[] arraySpinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//valores do spinner 1
        this.arraySpinner = new String[] {
                "<none>","Carris", "2", "3", "4", "5"
        };
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        spinner.setAdapter(adapter);

//end
//valores do spinner 2
        this.arraySpinner2 = new String[] {
                "<none>","353", "343", "T9", "D43", "T10"
        };
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner2);
        spinner2.setAdapter(adapter2);

//end

//BOTÃO PROXIMO
        final AppCompatButton botaoProximo = (AppCompatButton) findViewById(R.id.botaoProximo);

//Isso faz o botao só ativar quando algo é selecionado no spinner 2
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    botaoProximo.setEnabled(false);
                } else botaoProximo.setEnabled(true);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
//END
        botaoProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivityInicial.this, ActivityZ.class);
                //     it.setClass(null, ActivityZ.class);
                startActivity(it);

            }
        });
//END
    }

    @Override
    protected void onStart() {


        super.onStart();

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner = (Spinner) findViewById(R.id.spinner);

//Isso faz o spinner 2 só ativar quando algo é selecionado no spinner 1
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
                    spinner2.setEnabled(false);
                } else spinner2.setEnabled(true);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
//END
    };
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
        Intent it = new Intent(ActivityInicial.this, ActivityZ.class);
        //     it.setClass(null, ActivityZ.class);
        startActivity(it);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
