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

public class ActivityPesquisaResultado extends AppCompatActivity {


    private RatingBar nota;
    private TextView textComentario;
    private TextView textHora;
    private TextView textLinha;
    private TextView textDia;
    private TextView textEmpresa;

    //Fiz um teste pra ver o que acontece com um texto grande
    private final String stringComentario = "Merda de onibus lotado, a capacidade seria 45 pessoas sentadas, e 44 em pé, mas tinham pelo menos 105 pessoas em pé. Algumas ficavam para fora das janelas, enquanto outras coladas na porta. O onibus era velho e fazia muito barulho. A cobradora era mal educada, já o motorista não sabia frear.";
    private final String stringLinha = "Velho - Reaproveitado/Superfaturada";
    private final String stringDia = "14/05/98";
    private final String stringEmpresa = "Superfaturada";
    private final String stringHora = "07:00";
    private float valorNota = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_resultado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        defineAsStrings();


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
