package kimv.loginregister;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CourseList extends ArrayAdapter<Course>{

    private Activity context;
    private List<Course> courseList;

    public CourseList(Activity context, List<Course> courseList){
        super(context, R.layout.list_layout, courseList);
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewYear = (TextView) listViewItem.findViewById(R.id.textViewYear);
        TextView textViewEC = (TextView) listViewItem.findViewById(R.id.textViewEC);
        TextView textViewGrade = (TextView) listViewItem.findViewById(R.id.textViewGrade);
        TextView textViewComments = (TextView) listViewItem.findViewById(R.id.textViewComments);
        TextView textViewSpecial = (TextView) listViewItem.findViewById(R.id.textViewSpecial);
        TextView textViewStatus = (TextView) listViewItem.findViewById(R.id.textViewStatus);

        Course course = courseList.get(position);

        textViewName.setText(course.getCourseName() + " | " + course.getCoursePeriod());
        textViewYear.setText(course.getCourseYear());
        textViewEC.setText(course.getCourseEC());
        textViewGrade.setText(course.getCourseGrade());
        textViewComments.setText(course.getCourseComments());
        textViewSpecial.setText(course.getCourseSpecial());
        textViewStatus.setText(course.getCourseStatus());

        return listViewItem;
    }
}
