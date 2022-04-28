package android.example.cells;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username,password;
    Button btnlgn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnlgn = findViewById(R.id.login);
        btnlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTXT = username.getText().toString().trim();
                String passwordTXT = password.getText().toString().trim();
                if(usernameTXT.equals("admin")&&passwordTXT.equals("admin")){
                    Intent intent = new Intent(Login.this,AdminPanel.class);
                    startActivity(intent);
                }
                else if(usernameTXT.equals("user")&&passwordTXT.equals("user")){
                    Intent intent = new Intent(Login.this,ViewActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login.this,"Invalid UserName Password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}