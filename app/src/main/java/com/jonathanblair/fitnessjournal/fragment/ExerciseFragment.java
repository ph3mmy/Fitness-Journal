package com.jonathanblair.fitnessjournal.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.jonathanblair.fitnessjournal.R;

/**
 * Created by oluwafemi.bamisaye on 4/16/2016.
 */
public class ExerciseFragment extends Fragment {


    HorizontalScrollView hsv;
    ScrollView sv;
    Listener sListener;
    static final String FRAG_CODE = "EXF";


    public String getTitle(){
        return "Exercise";
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof Listener){
            sListener = (Listener) activity;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_exercise,container,false);


        String[] row = new String[33];

                /*{ "ROW1", "ROW2", "Row3", "Row4", "Row 5", "Row 6",
                "Row 7" };*/
        String[] column = new String[9];

        for (int i = 0; i < 9; i ++){
            column[i] = "         "+(i+1)+"         ";
        }

        int rl=row.length;
        int cl=column.length;

        Log.d("--", "R-Lenght--" + rl + "   " + "C-Lenght--" + cl);

        sv = new ScrollView(getActivity());
        TableLayout tableLayout = createTableLayout(row, column,rl, cl);
        hsv = new HorizontalScrollView(getActivity());

        hsv.addView(tableLayout);
        sv.addView(hsv);
        return sv;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sListener.passView(hsv, sv, FRAG_CODE);
    }

    private TableLayout createTableLayout(String [] rv, String [] cv,int rowCount, int columnCount) {

        // Create a tableLayout and its params
        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        TableLayout tableLayout = new TableLayout(getActivity());
        tableLayout.setBackgroundColor(Color.BLACK);

        // 2) create tableRow params
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.setMargins(1, 1, 1, 1);
        tableRowParams.weight = 1;

        for (int i = 0; i < rowCount; i++) {
            // 3) create tableRow
            TableRow tableRow = new TableRow(getActivity());
            tableRow.setBackgroundColor(Color.BLACK);

            for (int j= 0; j < columnCount; j++) {
                final EditText editText = new EditText(getActivity());
                editText.setBackgroundColor(Color.parseColor("#ffffff"));

                if (i==0 || j==0){
                    editText.setBackgroundColor(Color.parseColor("#ffffff"));
                }

                editText.setGravity(Gravity.CENTER);
                String s1 = Integer.toString(i);
                String s2 = Integer.toString(j);
                String s3 = s1 + s2;
                int id = Integer.parseInt(s3);

                if (i ==0 && j==0){
                    editText.setText("Today's Plan");
                    editText.setTextColor(Color.parseColor("#000000"));
                    editText.setEnabled(false);
                    editText.setBackgroundColor(Color.parseColor("#ffffff"));

                } else if(i==0){
                    Log.d("TAAG", "set Column Headers");
                    editText.setText(cv[j - 1]);
                    editText.setEnabled(false);
                    editText.setTextColor(Color.parseColor("#ffffff"));
                    editText.setBackgroundColor(getActivity().getResources().getColor(R.color.primary));

                }else if (i==1) {

                    if (j == 0) {
                        editText.setText("Exercise");
                        editText.setEnabled(false);
                        editText.setTextColor(Color.parseColor("#000000"));
                    } else if (j == 1) {
                        editText.setText("Name or #");
                        editText.setEnabled(false);
                        editText.setTextColor(Color.parseColor("#000000"));
                    } else if (j == 2) {
                        editText.setText("    Seat    ");
                        editText.setEnabled(false);
                        editText.setTextColor(Color.parseColor("#000000"));
                    } else if (j == 3) {
                        editText.setText("SET 1 Lbs");
                        editText.setEnabled(false);
                        editText.setTextColor(Color.parseColor("#000000"));
                    } else if (j == 4) {
                        editText.setText("SET 1 Reps");
                        editText.setEnabled(false);
                        editText.setTextColor(Color.parseColor("#000000"));
                    } else if (j == 5) {
                        editText.setText("SET 2 Lbs");
                        editText.setEnabled(false);
                        editText.setTextColor(Color.parseColor("#000000"));
                    } else if (j == 6) {
                        editText.setText("SET 2 Reps");
                        editText.setEnabled(false);
                        editText.setTextColor(Color.parseColor("#000000"));
                    } else if (j == 7) {
                        editText.setText("SET 3 Lbs");
                        editText.setEnabled(false);
                        editText.setTextColor(Color.parseColor("#000000"));
                    } else if (j == 8) {
                        editText.setText("SET 3 Reps");
                        editText.setEnabled(false);
                        editText.setTextColor(Color.parseColor("#000000"));
                    }
                }

                else if( j==0){
                    editText.setText(rv[i - 1]);
                    editText.setEnabled(false);
                    editText.setTextColor(Color.parseColor("#ffffff"));
                    editText.setBackgroundColor(getActivity().getResources().getColor(R.color.primary));

                    if (i==2) {
                        editText.setText("[Chest]Bench Press");
                    }else if (i==3) {
                        editText.setText("[Chest]Incline Chest Press");
                    }else if (i==4) {
                        editText.setText("[Chest]DB Chest Press");
                    }else if (i==5) {
                        editText.setText("[Chest]Push-Ups");
                    }else if (i==6) {
                        editText.setText("[Back]Lat. Pulldown");
                    }else if (i==7) {
                        editText.setText("[Back]Seated Row");
                    }else if (i==8) {
                        editText.setText("[Back]Rear Deltoid Fly");
                    }else if (i==9) {
                        editText.setText("[Back]Prone Cobra/back");
                    }else if (i==10) {
                        editText.setText("[Back]Pull-Ups");
                    }else if (i==11) {
                        editText.setText("[Legs]Squats");
                    }else if (i==12) {
                        editText.setText("[Legs]Leg Extensions");
                    }else if (i==13) {
                        editText.setText("[Legs]Hamstring Curl");
                    }else if (i==14) {
                        editText.setText("[Legs]Lunge");
                    }else if (i==15) {
                        editText.setText("[Shoulders]Shoulder Press");
                    }else if (i==16) {
                        editText.setText("[Shoulders]Side Lateral Raise");
                    }else if (i==17) {
                        editText.setText("[Shoulders]Front Raise");
                    }else if (i==18) {
                        editText.setText("[Shoulders]Shrugs");
                    }else if (i==19) {
                        editText.setText("[Biceps]Standing Curls");
                    }else if (i==20) {
                        editText.setText("[Biceps]Preacher Curls");
                    }else if (i==21) {
                        editText.setText("[Biceps]Hammer Curls");
                    }else if (i==22) {
                        editText.setText("[Triceps]Pressdowns");
                    }else if (i==23) {
                        editText.setText("[Triceps]Kickbacks");
                    }else if (i==24) {
                        editText.setText("[Triceps]Extensions");
                    }else if (i==25) {
                        editText.setText("[Abs & Core]Reverse Ab Curl");
                    }else if (i==26) {
                        editText.setText("[Abs & Core]Bicycle-Elbow Knee");
                    }else if (i==27) {
                        editText.setText("[Abs & Core]Prone Bridge");
                    }else if (i==28) {
                        editText.setText("[Abs & Core]Torso Hold");
                    }else if (i==29) {
                        editText.setText("[Abs & Core]Forward Ball Roll");
                    }else if (i==30) {
                        editText.setText("[Abs & Core]Ball Crunches");
                    }else if (i==31) {
                        editText.setText(" ");
                        editText.setEnabled(false);
                        editText.setFocusable(false);
                    }else if (i==32) {
                        editText.setText("Date");
                    }
                }
                else {

                }

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (!TextUtils.isEmpty(editText.getText().toString())){
                            editText.setBackgroundColor(Color.parseColor("#999999"));
                        }else
                            editText.setBackgroundColor(Color.parseColor("#ffffff"));

                    }
                });
                tableRow.addView(editText, tableRowParams);
            }
            //add tableRow to tableLayout
            tableLayout.addView(tableRow, tableLayoutParams);
        }

        return tableLayout;
    }

    public interface Listener{
        void passView(HorizontalScrollView view, ScrollView view2, String fragCode);
    }
}
