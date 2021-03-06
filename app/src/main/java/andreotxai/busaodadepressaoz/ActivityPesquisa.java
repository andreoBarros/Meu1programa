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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import andreotxai.busaodadepressaoz.DAO.AvaliacoesDAO;
import andreotxai.busaodadepressaoz.util.DataBaseValuesConvert;
import andreotxai.busaodadepressaoz.util.DataTree;

public class ActivityPesquisa extends AppCompatActivity {

    Calendar cal;


    private Button btnPesquisar;
    private EditText textPesquisar;

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
        //CAIXA DE TEXTO
        this.textPesquisar = (EditText) findViewById(R.id.textPesquisar);

    }


    //PARA BUSCAR A AVALICAO
    private View.OnClickListener createBotaoPesquisarClickListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String stringPesquisar = textPesquisar.getText().toString();
                try {
                    String atributosAvaliacao = DataBaseValuesConvert.dataTree.pesquisaPorPalavra(ActivityPesquisa.this, stringPesquisar);
                    // Toast.makeText(ActivityPesquisa.this, atributosAvaliacao, Toast.LENGTH_LONG).show();
                    splitAvalicao(atributosAvaliacao);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(ActivityPesquisa.this, "problema ao ler avaliacao!", Toast.LENGTH_LONG).show();
                }

            }

        };
    }

    private void splitAvalicao(String avaliacoes) {
        ArrayList<String> avaliacoesAtributos = new ArrayList<String>();
        ArrayList<String> comentarios = new ArrayList<String>();
        String comentario = "";
        String[] avaliacaoSplit = avaliacoes.split("\n");
        for (int i = 0; i < avaliacaoSplit.length; i++) {
            Matcher matcher = Pattern.compile(DataBaseValuesConvert.REGEX_COMENTARIO).matcher(avaliacaoSplit[i]);
            if (matcher.find()) {
                comentario = matcher.group(0);
            }
            avaliacoesAtributos.add(avaliacaoSplit[i].replace("<[" + comentario + "]> ", ""));
            comentarios.add(comentario);
        }
        Intent it = new Intent(ActivityPesquisa.this, ActivityPesquisaResultado.class);
        it.putStringArrayListExtra("avaliacoes", avaliacoesAtributos);
        it.putStringArrayListExtra("comentarios", comentarios);
        startActivity(it);
    }
    //END

}