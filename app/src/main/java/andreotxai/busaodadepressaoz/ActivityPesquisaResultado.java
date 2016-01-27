package andreotxai.busaodadepressaoz;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import org.xmlpull.v1.XmlPullParser;

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
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_resultado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.avaliacoes = getIntent().getExtras().getStringArrayList("avaliacoes");
        this.comentarios = getIntent().getExtras().getStringArrayList("comentarios");


        Bundle gotBasket = getIntent().getExtras();

        //CRIA O SCROLL VIEW COM UM LINEAR LAYOUT FIXO
        ScrollView sv = new ScrollView(this);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);
        funcaoTeste(ll);

        //END



        this.setContentView(sv);

    }

    private void funcaoTeste(LinearLayout ll) {
        for (int i = 0; i < this.avaliacoes.size() ; i++) {
            preencheStrings(this.avaliacoes.get(i));
            stringComentario = this.comentarios.get(i);
            comentario(stringComentario, stringLinha, stringDia, stringEmpresa, stringHora, valorNota, ll, i);
        }
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



    public void comentario(String Comentario, String Linha, String Dia, String Empresa, String Hora, float valorNota, LinearLayout ll, int i) {

        TextView textHora = new TextView(this);
        textHora.setText("Horario: " + Hora);
        textHora.setTextSize(17);
        textHora.setWidth(172);
        textHora.setTypeface(null, Typeface.BOLD);
        textHora.setId(i);
        ll.addView(textHora);

        TextView textDia = new TextView(this);
        textDia.setTextSize(17);
        textDia.setWidth(172);
        textDia.setTypeface(null, Typeface.BOLD);
        textDia.setText("Dia: " + Dia);
        textDia.setId(1 + i);
        ll.addView(textDia);

        TextView textEmpresa = new TextView(this);
        textEmpresa.setTextSize(15);
        textEmpresa.setWidth(172);
        textEmpresa.setTypeface(null, Typeface.BOLD);
        textEmpresa.setText("Empresa: " + Empresa);
        textEmpresa.setId(2 + i);
        ll.addView(textEmpresa);

        TextView textLinha = new TextView(this);
        textLinha.setTextSize(14);
        textLinha.setWidth(172);
        textLinha.setTypeface(null, Typeface.ITALIC);
        textLinha.setText("Linha: " + Linha);
        textLinha.setId(3 + i);
        ll.addView(textLinha);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) LinearLayout.LayoutParams.WRAP_CONTENT,
                (int) LinearLayout.LayoutParams.WRAP_CONTENT);

        RatingBar nota = new RatingBar(this);
        Drawable drawable = nota.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#FFD800"), PorterDuff.Mode.MULTIPLY);

        nota.setLayoutParams(params);
        nota.setNumStars(5);
        nota.setRating(valorNota);
        nota.setId(4 + i);
        nota.setClickable(false);
        nota.setIsIndicator(true);
        ll.addView(nota);

        TextView textComentario = new TextView(this);
        textComentario.setText(Comentario);
        textComentario.setMaxLines(7);
        textComentario.setHeight(144);
        textComentario.setId(5 + i);
        ll.addView(textComentario);

    }


}


