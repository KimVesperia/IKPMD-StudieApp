package kimv.loginregister;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCourse1Activity extends AppCompatActivity {

    EditText editTextName;
    Spinner spinnerYear;
    Spinner spinnerPeriod;
    EditText editTextEC;
    EditText editTextGrade;
    EditText editTextComments;
    Spinner spinnerSpecial;
    Spinner spinnerStatus;
    Button buttonAddCourse;

    DatabaseReference databaseCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course1);

        databaseCourses = FirebaseDatabase.getInstance().getReference("courses/year1");

        editTextName = (EditText) findViewById(R.id.editTextName);
        spinnerYear = (Spinner) findViewById(R.id.spinnerYear);
        spinnerYear.setEnabled(false);
        spinnerPeriod = (Spinner) findViewById(R.id.spinnerPeriod);
        editTextEC = (EditText) findViewById(R.id.editTextEC);
        editTextGrade = (EditText) findViewById(R.id.editTextGrade);
        editTextComments = (EditText) findViewById(R.id.editTextComments);
        spinnerSpecial = (Spinner) findViewById(R.id.spinnerSpecial);
        spinnerSpecial.setEnabled(false);
        spinnerStatus = (Spinner) findViewById(R.id.spinnerStatus);
        buttonAddCourse = (Button) findViewById(R.id.buttonAddCourse);

        buttonAddCourse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addCourse();
            }
        });
    }

    private void addCourse(){
        String name = editTextName.getText().toString().trim();
        String year = spinnerYear.getSelectedItem().toString();
        String period = spinnerPeriod.getSelectedItem().toString();
        String ec = editTextEC.getText().toString().trim();
        String grade = editTextGrade.getText().toString().trim();
        String comments = editTextComments.getText().toString().trim();
        String special = spinnerSpecial.getSelectedItem().toString();
        String status = spinnerStatus.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){

            String id = databaseCourses.push().getKey();

            Course course = new Course(id, name, year, period, ec, grade, comments, special, status);

            databaseCourses.child(name).setValue(course);

            Toast.makeText(this, "Vak Succesvol Toegevoegd", Toast.LENGTH_LONG).show();
            finish();

        }else{
            Toast.makeText(this, "Voer het vaknaam in", Toast.LENGTH_LONG).show();
        }
    }
}
