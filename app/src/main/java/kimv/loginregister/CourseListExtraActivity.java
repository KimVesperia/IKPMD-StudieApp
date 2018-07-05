package kimv.loginregister;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CourseListExtraActivity extends AppCompatActivity {


    ListView listViewCourses;
    List<Course> courses;
    DatabaseReference databaseCourses;
    Query query;

    private ProgressDialog progressDialog;

    TextView textViewYearTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courselist);

        databaseCourses = FirebaseDatabase.getInstance().getReference("courses/extra");

        FloatingActionButton fab = findViewById(R.id.addCourse);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CourseListExtraActivity.this, AddCourseExtraActivity.class));
            }
        });

        listViewCourses = (ListView) findViewById(R.id.listViewCourses);

        courses = new ArrayList<>();

        listViewCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Course course = courses.get(i);

                showUpdateDialog(course.getCourseID(), course.getCourseName(), course.getCourseEC(), course.getCourseGrade(), course.getCourseComments());

            }
        });

    }

    private void showUpdateDialog(final String courseId, final String courseName, String courseEC, String courseGrade, String courseComments){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog5, null);

        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
        editTextName.setText(courseName);
        editTextName.setEnabled(false);
        final Spinner spinnerYear = (Spinner) dialogView.findViewById(R.id.spinnerYear);
        final Spinner spinnerPeriod = (Spinner) dialogView.findViewById(R.id.spinnerPeriod);
        final EditText editTextEC = (EditText) dialogView.findViewById(R.id.editTextEC);
        editTextEC.setText(courseEC);
        final EditText editTextGrade = (EditText) dialogView.findViewById(R.id.editTextGrade);
        editTextGrade.setText(courseGrade);
        final EditText editTextComments = (EditText) dialogView.findViewById(R.id.editTextComments);
        editTextComments.setText(courseComments);
        final Spinner spinnerSpecial = (Spinner) dialogView.findViewById(R.id.spinnerSpecial);
        spinnerSpecial.setEnabled(false);
        final Spinner spinnerStatus = (Spinner) dialogView.findViewById(R.id.spinnerStatus);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdate);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDelete);

        dialogBuilder.setTitle("Aanpassingen");

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String year = spinnerYear.getSelectedItem().toString();
                String period = spinnerPeriod.getSelectedItem().toString();
                String ec = editTextEC.getText().toString().trim();
                String grade = editTextGrade.getText().toString().trim();
                String comments = editTextComments.getText().toString().trim();
                String special = spinnerSpecial.getSelectedItem().toString();
                String status = spinnerStatus.getSelectedItem().toString();

                if(TextUtils.isEmpty(name)){
                    editTextName.setError("Voer een keuzevak in...");
                    return;
                }

                updateCourse(courseId, name, year, period, ec, grade, comments, special, status);

                alertDialog.dismiss();

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCourse(courseName);
                alertDialog.dismiss();
            }
        });

    }

    private void deleteCourse(String courseName){
        DatabaseReference drCourse = FirebaseDatabase.getInstance().getReference("courses/extra").child(courseName);
        drCourse.removeValue();

        Toast.makeText(this, "Verwijdering Succesvol", Toast.LENGTH_LONG).show();
    }

    private boolean updateCourse(String id, String name, String year, String period, String ec, String grade, String comments, String special, String status){

        DatabaseReference databaseCourses = FirebaseDatabase.getInstance().getReference("courses/extra").child(name);

        Course course = new Course(id, name, year, period, ec, grade, comments, special, status);

        databaseCourses.setValue(course);

        Toast.makeText(this, "Keuzevak Succesvol Aangepast", Toast.LENGTH_LONG).show();

        return true;
    }

    @Override
    protected void onStart() {

        super.onStart();

        Query query = databaseCourses.orderByChild("coursePeriod");
        query.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){

                courses.clear();

                for(DataSnapshot courseSnapshot: dataSnapshot.getChildren()){
                    Course course = courseSnapshot.getValue(Course.class);

                    courses.add(course);
                }

                CourseList adapter = new CourseList(CourseListExtraActivity.this, courses);
                listViewCourses.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });
    }
}
