package andreotxai.busaodadepressaoz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import andreotxai.busaodadepressaoz.DAO.AvaliacoesDAO;
import andreotxai.busaodadepressaoz.model.Avalicoes;

public class ActivityAvaliacao extends AppCompatActivity {

    private AppCompatButton btnEnviar;
    private RatingBar nota;
    private Avalicoes avalicao;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_avaliacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.btnEnviar = (AppCompatButton) findViewById(R.id.btnEnviar);
        this.nota = (RatingBar) findViewById(R.id.nota);
        this.text = (EditText) findViewById(R.id.caixaTexto);

        //botao enviar
        btnEnviar.setOnClickListener(this.createBotaoEnviarListener());
        //END
    }

    private View.OnClickListener createBotaoEnviarListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAvaliacao();
            }
        };
    }

    private void createAvaliacao() {
        this.avalicao = new Avalicoes();
        this.avalicao.setComentario(this.text.getText().toString());
        this.avalicao.setNota(22.0); // não implementado ainda, valor padrão para não dar erro
        this.avalicao.setIdRelLinhaHorarios(1); // não implementado ainda, valor padrão para não dar erro
        this.avalicao.setIdUsuario(1); // não implementado ainda, valor padrão para não dar erro
    }

    private void sendAvaliacao() {
        this.createAvaliacao();
        AvaliacoesDAO dao = new AvaliacoesDAO(this.avalicao);
        if (dao.insereAvaliacaoDataBase(this)) {
            Toast.makeText(ActivityAvaliacao.this, "Comentário adicionado com aucesso!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(ActivityAvaliacao.this, "Ocorreu algum problema!", Toast.LENGTH_LONG).show();
        }
    }
}
