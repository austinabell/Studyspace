package com.austinabell8.studyspace.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.austinabell8.studyspace.R;
import com.austinabell8.studyspace.model.Post;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class PostCreateActivity extends AppCompatActivity {

    private EditText mPriceText;
    private Button mCreateButton;
    private Spinner mCourseSpinner;
    private EditText mDescriptionText;
    private String current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_create);

        current = "";

        mPriceText = findViewById(R.id.price_edit_text);
        mPriceText.setRawInputType(Configuration.KEYBOARD_12KEY);
        mPriceText.addTextChangedListener(new TextWatcher(){
            DecimalFormat dec = new DecimalFormat("0.00");
            @Override
            public void afterTextChanged(Editable arg0) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(current)){
                    mPriceText.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[$,.]", "");

                    double parsed = Double.parseDouble(cleanString);
                    String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));

                    current = formatted;
                    mPriceText.setText(formatted);
                    mPriceText.setSelection(formatted.length());

                    mPriceText.addTextChangedListener(this);
                }
            }
        });
//        mPriceText.addTextChangedListener(new NumberTextWatcher(mPriceText, "#,###"));
        mCourseSpinner = findViewById(R.id.course_spinner);
        mCreateButton = findViewById(R.id.create_button);
        mDescriptionText = findViewById(R.id.description_edit_text);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(PostCreateActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subject_arrays));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCourseSpinner.setAdapter(adapter);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stores values of spinner and editText on a searchButton event
                Post newPost = new Post();
                newPost.setDescription(mDescriptionText.getText().toString());
                newPost.setCourse(mCourseSpinner.getItemAtPosition(mCourseSpinner.getSelectedItemPosition()).toString());
                newPost.setPrice(mPriceText.getText().toString());
                Intent returnIntent = new Intent();
                returnIntent.putExtra("post_item", newPost);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }


    public boolean validate() {
//        boolean valid = true;
//
//        String name = mNameText.getText().toString().trim();
//        String username = mUsernameText.getText().toString().trim();
//        String email = mEmailText.getText().toString().trim();
//        String password = mPasswordText.getText().toString().trim();
//
//        if (name.isEmpty() || name.length() < 3) {
//            mNameText.setError("must be at least 3 characters");
//            valid = false;
//        } else {
//            mNameText.setError(null);
//        }
//
//        if (username.isEmpty() || username.length() < 5) {
//            mUsernameText.setError("must be at least 5 characters");
//            valid = false;
//        } else {
//            mNameText.setError(null);
//        }
//
//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            mAgeText.setError("enter a valid email address");
//            valid = false;
//        } else {
//            mAgeText.setError(null);
//        }
//
//        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
//            mPasswordText.setError("between 4 and 10 alphanumeric characters");
//            valid = false;
//        } else {
//            mPasswordText.setError(null);
//        }
//
//        return valid;
        return true;
    }

}
