package andreotxai.busaodadepressaoz;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
                Fragment fragFragment = new Fragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragFragment);
                fragmentTransaction.commit();
            }
        });
        //END
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
