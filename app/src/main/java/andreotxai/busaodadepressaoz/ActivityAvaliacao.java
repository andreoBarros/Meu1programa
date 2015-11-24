package andreotxai.busaodadepressaoz;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
import andreotxai.busaodadepressaoz.model.Empresa;
import andreotxai.busaodadepressaoz.model.Horarios;
import andreotxai.busaodadepressaoz.model.Linha;
import andreotxai.busaodadepressaoz.util.DataBaseValuesConvert;
import andreotxai.busaodadepressaoz.util.DataTree;

public class ActivityAvaliacao extends ActionBarActivity {

    private AppCompatButton btnEnviar;
    private Button btnTeste;
    private RatingBar nota;
    private Avalicoes avalicao;
    private EditText text;

    private  String stringLinha = "";
    private  String stringDia = "";
    private  String stringEmpresa = "";
    private  String stringHora = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_avaliacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //SETA DE VOLTAR
        //VALORES DA ACTIVITY ANTERIOR
        stringLinha = getIntent().getExtras().getString("finalLinha","defaultKey");
        stringEmpresa = getIntent().getExtras().getString("finalEmpresa","defaultKey");
        stringHora = getIntent().getExtras().getString("finalHorario","defaultKey");
        stringDia = getIntent().getExtras().getString("finalData","defaultKey");

        this.btnEnviar = (AppCompatButton) findViewById(R.id.btnEnviar);
        this.nota = (RatingBar) findViewById(R.id.nota);
        this.text = (EditText) findViewById(R.id.caixaTexto);
        this.btnTeste = (Button) findViewById(R.id.buttonTeste);
        this.btnTeste.setVisibility(View.INVISIBLE);

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
        this.avalicao.setNota(this.nota.getRating());
        this.avalicao.setData(this.stringDia);
    }

    private Empresa createEmpresa() {
        int idEmpresa = DataBaseValuesConvert.getEmpresaIndex(stringEmpresa);
        return new Empresa(idEmpresa, stringEmpresa);
    }

    private Linha createLinha(Empresa empresa) {
        int idLinha = DataBaseValuesConvert.getLinhaIndex(stringLinha, empresa.getNome());
        return new Linha(idLinha, stringEmpresa, empresa.getIdEmpresa());
    }

    private Horarios createHorario() {
        int idHorario = DataBaseValuesConvert.getHorarioIndex(stringHora);
        return new Horarios(idHorario, stringHora);
    }

    private void sendAvaliacao() {
        this.createAvaliacao();
        Empresa empresa = this.createEmpresa();
        Linha linha = this.createLinha(empresa);
        Horarios horario = this.createHorario();
        AvaliacoesDAO dao = new AvaliacoesDAO(this.avalicao);
        dao.setEmpresa(empresa);
        dao.setLinha(linha);
        dao.setHorario(horario);
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
