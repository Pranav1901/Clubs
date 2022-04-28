package android.example.cells;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
    EditText name ;
    Button delete;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        name = findViewById(R.id.name);
        delete = findViewById(R.id.btndelete);
        DB = new DBHelper(this);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = name.getText().toString();
                boolean checkdeletedata = DB.deleteclubdata(nameTxt);
                Intent intent1 = new Intent(DeleteActivity.this,AdminPanel.class);
                if(checkdeletedata==true){
                    Toast.makeText(DeleteActivity.this,"Entry Deleted",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(DeleteActivity.this,"Entry Not Deleted",Toast.LENGTH_SHORT).show();
                }
                startActivity(intent1);
                finish();
            }
        });
    }
}