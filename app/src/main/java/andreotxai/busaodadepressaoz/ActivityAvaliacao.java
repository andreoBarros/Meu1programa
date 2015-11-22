package andreotxai.busaodadepressaoz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import andreotxai.busaodadepressaoz.DAO.AvaliacoesDAO;
import andreotxai.busaodadepressaoz.model.Avalicoes;

public class ActivityAvaliacao extends AppCompatActivity {

    private AppCompatButton btnEnviar;
    private Button btnTeste;
    private RatingBar nota;
    private Avalicoes avalicao;
    private EditText text;

    private final String stringLinha = "Velho - Reaproveitado/Superfaturada";
    private final String stringDia = "14/05/98";
    private final String stringEmpresa = "Superfaturada";
    private final String stringHora = "07:00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_avaliacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //SETA DE VOLTAR
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        this.btnEnviar = (AppCompatButton) findViewById(R.id.btnEnviar);
        this.nota = (RatingBar) findViewById(R.id.nota);
        this.text = (EditText) findViewById(R.id.caixaTexto);
        this.btnTeste = (Button) findViewById(R.id.buttonTeste);

        defineAsStrings();

        //botao enviar
        btnEnviar.setOnClickListener(this.createBotaoEnviarListener());
        //END
        btnTeste.setOnClickListener(this.createBotaoTesteListener());
    }

    private View.OnClickListener createBotaoEnviarListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAvaliacao();
            }
        };
    }

    private View.OnClickListener createBotaoTesteListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readAvaliacao();
            }
        };
    }

    private void createAvaliacao() {
        this.avalicao = new Avalicoes();
        this.avalicao.setComentario(this.text.getText().toString());
        this.avalicao.setNota(this.nota.getRating()); // não implementado ainda, valor padrão para não dar erro
        this.avalicao.setIdRelLinhaHorarios(1); // não implementado ainda, valor padrão para não dar erro
        this.avalicao.setIdUsuario(1); // não implementado ainda, valor padrão para não dar erro
    }

    private void sendAvaliacao() {
        this.createAvaliacao();
        AvaliacoesDAO dao = new AvaliacoesDAO(this.avalicao);
        if (dao.insereDataBase(this)) {
            Toast.makeText(ActivityAvaliacao.this, "Comentário adicionado com aucesso!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(ActivityAvaliacao.this, "Ocorreu algum problema!", Toast.LENGTH_LONG).show();
        }
    }

    private void readAvaliacao() {
        AvaliacoesDAO dao = new AvaliacoesDAO();
        try {
            String teste = dao.lerDataBase(this);
            Toast.makeText(ActivityAvaliacao.this, teste, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(ActivityAvaliacao.this, "Problema de leitura!", Toast.LENGTH_LONG).show();
        }
    }

    public void defineAsStrings(){
        TextView textLinha = (TextView) findViewById(R.id.avalLinha);
        TextView textDia = (TextView) findViewById(R.id.avalDia);
        TextView textEmpresa = (TextView) findViewById(R.id.avalEmpresa);
        TextView textHora = (TextView) findViewById(R.id.avalHora);
        textLinha.setText(stringLinha);
        textDia.setText(stringDia);
        textEmpresa.setText(stringEmpresa);
        textHora.setText(stringHora);
    }

}
