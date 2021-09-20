package com.android.tutorial.brave_db_managment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditUserActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.android.tutorial.brave_db_managment.EXTRA_ID";
    public static final String EXTRA_NAME = "com.android.tutorial.brave_db_managment.EXTRA_NAME";
    public static final String EXTRA_EMAIL = "com.android.tutorial.brave_db_managment.EXTRA_EMAIL";
    public static final String EXTRA_PHONE = "com.android.tutorial.brave_db_managment.EXTRA_PHONE";
    public static final String EXTRA_ROLE = "com.android.tutorial.brave_db_managment.EXTRA_ROLE";

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private EditText editTextRole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        editTextName = findViewById(R.id.edit_text_name);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPhone = findViewById(R.id.edit_text_phone);
        editTextRole = findViewById(R.id.edit_text_role);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit User");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            editTextEmail.setText(intent.getStringExtra(EXTRA_EMAIL));
            editTextPhone.setText(intent.getStringExtra(EXTRA_PHONE));
            editTextRole.setText(intent.getStringExtra(EXTRA_ROLE));
        } else {
            setTitle("Add User");
        }

    }

    private void saveUser() {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhone.getText().toString();
        String role = editTextRole.getText().toString();
        if ( name.trim().isEmpty() || email.trim().isEmpty() || phone.trim().isEmpty() || role.isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data=  new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_EMAIL, email);
        data.putExtra(EXTRA_PHONE, phone);
        data.putExtra(EXTRA_ROLE, role);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if ( id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.save_user:
                saveUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}