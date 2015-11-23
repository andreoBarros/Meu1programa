package andreotxai.busaodadepressaoz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import andreotxai.busaodadepressaoz.util.DataBaseValuesConvert;

public class ActivityPesquisaResultado extends AppCompatActivity {


    private RatingBar nota;
    private TextView textComentario;
    private TextView textHora;
    private TextView textLinha;
    private TextView textDia;
    private TextView textEmpresa;

    //Fiz um teste pra ver o que acontece com um texto grande
    private String stringComentario = "";
    private String stringLinha = "";
    private String stringDia = "";
    private String stringEmpresa = "";
    private String stringHora = "";
    private float valorNota = 0;
    private ArrayList<String> avaliacoes;
    private ArrayList<String> comentarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_resultado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.avaliacoes = getIntent().getExtras().getStringArrayList("avaliacoes");
        this.comentarios = getIntent().getExtras().getStringArrayList("comentarios");
        funcaoTeste();
        defineAsStrings();

    }

    private void funcaoTeste() {
        //for (int i = 0; i < this.avaliacoes.size() ; i++) {
            preencheStrings(this.avaliacoes.get(0));
            stringComentario = this.comentarios.get(0);
        //}
    }

    private void preencheStrings(String avaliacao) {
        String[] auxiliar = avaliacao.split(" ");
        this.stringEmpresa = DataBaseValuesConvert.arraySpinnerEmpresa[Integer.parseInt(auxiliar[0])];
        this.valorNota = Float.parseFloat(auxiliar[1]);
        switch(this.stringEmpresa) {
            case "Carris":
                this.stringLinha = DataBaseValuesConvert.arraySpinner2Carris[Integer.parseInt(auxiliar[2])];
                break;
            case "Conorte":
                this.stringLinha = DataBaseValuesConvert.arraySpinner2Conorte[Integer.parseInt(auxiliar[2])];
                break;
            case "STS":
                this.stringLinha = DataBaseValuesConvert.arraySpinner2Sts[Integer.parseInt(auxiliar[2])];
                break;
            case "Unibus":
                this.stringLinha = DataBaseValuesConvert.arraySpinner2Unibus[Integer.parseInt(auxiliar[2])];
                break;
        }
        this.stringHora = DataBaseValuesConvert.arraySpinnerHorario[Integer.parseInt(auxiliar[3])];
        this.stringDia = auxiliar[4];
    }

    public void defineAsStrings(){
        RatingBar nota = (RatingBar) findViewById(R.id.barraNota);
        TextView textLinha = (TextView) findViewById(R.id.tituloLinha);
        TextView textDia = (TextView) findViewById(R.id.tituloDia);
        TextView textEmpresa = (TextView) findViewById(R.id.tituloEmpresa);
        TextView textHora = (TextView) findViewById(R.id.tituloHora);
        TextView textComentario = (TextView) findViewById(R.id.textComentario);
        textLinha.setText(stringLinha);
        textDia.setText(stringDia);
        textEmpresa.setText(stringEmpresa);
        textHora.setText(stringHora);
        textComentario.setText(stringComentario);
        nota.setRating(valorNota);
    }

}
