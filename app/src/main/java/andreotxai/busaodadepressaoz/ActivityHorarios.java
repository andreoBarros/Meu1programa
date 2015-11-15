package andreotxai.busaodadepressaoz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityHorarios extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener dpickerListner = this.createDataListener();
    private Button btn;
    private int year_x, month_x,day_x;

    public static final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_horarios);

        final Calendar cal = Calendar.getInstance();

        this.year_x = cal.get(Calendar.YEAR);
        this.month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        mostraDialogOnButtonClick();
    }

    public void mostraDialogOnButtonClick(){
        btn = (Button) findViewById(R.id.samuseucu);

        btn.setOnClickListener(
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
}

