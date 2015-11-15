package andreotxai.busaodadepressaoz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.media.RatingCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;

public class ActivityAvaliacao extends AppCompatActivity {

    private AppCompatButton btnEnviar;
    private RatingBar estrelinha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_avaliacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.btnEnviar = (AppCompatButton) findViewById(R.id.btnEnviar);
        this.estrelinha = (RatingBar) findViewById(R.id.estrelinha);
        //botao enviar
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //END
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
