package kimv.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileYearActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_year);

        Button btn1 = (Button)findViewById(R.id.buttonYear1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileYearActivity.this, CourseListYear1Activity.class));
            }
        });

        Button btn2 = (Button)findViewById(R.id.buttonYear2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileYearActivity.this, CourseListYear2Activity.class));
            }
        });

        Button btn3 = (Button)findViewById(R.id.buttonYear3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileYearActivity.this, CourseListYear3Activity.class));
            }
        });

        Button btn4 = (Button)findViewById(R.id.buttonYear4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileYearActivity.this, CourseListYear4Activity.class));
            }
        });

        Button btn5 = (Button)findViewById(R.id.buttonExtra);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileYearActivity.this, CourseListExtraActivity.class));
            }
        });

    }
}
