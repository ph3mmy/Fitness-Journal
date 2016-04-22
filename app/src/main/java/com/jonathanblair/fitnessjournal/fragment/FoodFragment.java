package com.jonathanblair.fitnessjournal.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.jonathanblair.fitnessjournal.R;

public class FoodFragment extends Fragment {

    Listener mListener;
    HorizontalScrollView hsv;
    ScrollView sv;
    static final String FRAG_CODE = "FF";

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof Listener) {
            mListener = (Listener)activity;
        }
    }

    public String getTitle(){
        return "Food";
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] row = new String[23];
        String[] column = new String[9];
        for (int i = 0; i < 9; ++i) {
            column[i] = "         " + (i + 1) + "         ";
        }
        int rl = row.length;
        int cl = column.length;
        Log.d("--", ("R-Lenght--" + rl + "   " + "C-Lenght--" + cl));

        sv = new ScrollView(getActivity());
        TableLayout tableLayout = createTableLayout(row, column, rl, cl);
        hsv = new HorizontalScrollView(getActivity());
        hsv.addView(tableLayout);
        sv.addView(hsv);
        return sv;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListener.getView(hsv, sv, FRAG_CODE);
    }

    private TableLayout createTableLayout(String[] rv, String[] cv, int rowCount, int columnCount) {
        // Create a tableLayout and its params
        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        TableLayout tableLayout = new TableLayout(getActivity());
        tableLayout.setBackgroundResource(R.drawable.cover_photo);
//        tableLayout.setBackgroundColor(Color.BLACK);

        // create tableRow params
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

                editText.requestFocus();
                editText.setGravity(Gravity.CENTER);

                String s1 = Integer.toString(i);
                String s2 = Integer.toString(j);
                String s3 = s1 + s2;

                if (i ==0 && j==0){
                    editText.setText("Today's Plan");
                    editText.setTextColor(Color.parseColor("#000000"));
                    editText.setEnabled(false);
                    editText.setBackgroundColor(Color.parseColor("#ffffff"));

                } else if (i == 0) {

                    //set Column Headers
                    editText.setText(cv[j - 1]);
                    editText.setEnabled(false);
                    editText.setTextColor(Color.parseColor("#ffffff"));
                    editText.setBackgroundColor(this.getActivity().getResources().getColor(R.color.primary));
                } else if (j == 0) {

                    //Set Row Headers"
                    editText.setText(rv[i - 1]);
                    editText.setEnabled(false);
                    editText.setTextColor(Color.parseColor("#ffffff"));
                    editText.setBackgroundColor(this.getActivity().getResources().getColor(R.color.primary));
                    if (i == 2 || i == 3 || i == 4 || i == 5) {
                        editText.setText("Breakfast");
                    } else if (i == 7 || i == 8 || i == 9 || i == 10) {
                        editText.setText("Lunch");
                    } else if (i == 12 || i == 13 || i == 14 || i == 15) {
                        editText.setText("Dinner");
                    } else if (i == 17 || i == 18 || i == 19 || i == 20) {
                        editText.setText("Snacks");
                    } else if (i == 1 || i == 16 || i == 11 || i == 21) {
                        editText.setText("    ");
                        editText.setEnabled(false);
                    } else if (i == 22) {
                        editText.setText("Total");
                    }
                } else if (j == 2 && i == 1) {
                    editText.setText("Calories");
                    editText.setEnabled(false);
                    editText.setTextColor(Color.parseColor("#000000"));
                } else if (j == 1 && i == 1) {
                    editText.setText("Food/Beverage");
                    editText.setEnabled(false);
                    editText.setTextColor(Color.parseColor("#000000"));
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
            tableLayout.addView(tableRow,tableLayoutParams);
        }
        return tableLayout;
    }

    public interface Listener{
        void getView(HorizontalScrollView view, ScrollView view2, String fragmentCode);
    }
}