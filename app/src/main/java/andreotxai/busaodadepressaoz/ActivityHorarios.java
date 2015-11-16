package andreotxai.busaodadepressaoz;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class ActivityHorarios extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener dpickerListner = this.createDataListener();
    private Button btnData;
    private Button btnProximo;
    private int year_x, month_x,day_x;

    public static final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.btnProximo = (Button) findViewById(R.id.botaoAvancar);
        this.btnProximo.setOnClickListener(this.createBotaoProximoClickListener());

        final Calendar cal = Calendar.getInstance();
        this.year_x = cal.get(Calendar.YEAR);
        this.month_x = cal.get(Calendar.MONTH);
        this.day_x = cal.get(Calendar.DAY_OF_MONTH);

        this.mostraDialogOnButtonClick();
    }

    public void mostraDialogOnButtonClick(){
        this.btnData = (Button) findViewById(R.id.botaoData);

        this.btnData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);
                    }
                });
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == DIALOG_ID){
            return new DatePickerDialog(this, dpickerListner, year_x, month_x, day_x);
        }
        else{
            return null;
        }
    }

    private DatePickerDialog.OnDateSetListener createDataListener() {
        return  new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                year_x = year;
                month_x = monthOfYear + 1;
                day_x = dayOfMonth;
                Toast.makeText(ActivityHorarios.this,day_x + "/" + month_x + "/" + year_x, Toast.LENGTH_LONG).show();
            }
        };
    }

    private View.OnClickListener createBotaoProximoClickListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 Intent it = new Intent(ActivityHorarios.this, ActivityAvaliacao.class);
                 startActivity(it);
            }

        };
    }
}

