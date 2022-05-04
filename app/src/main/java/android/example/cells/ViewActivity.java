package android.example.cells;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity implements ItemClickListener {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adapter adapter;
    DBHelper DB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initData();

        initRecyclerView();

    }
    private void initData() {
        userList = new ArrayList<>();
        DB = new DBHelper(this);
        Cursor res = DB.getclubdata();
        String divider = "--------------------------------------------------------------";
        if(res.getCount()==0){
            Toast.makeText(ViewActivity.this,"No Entry Exits",Toast.LENGTH_SHORT).show();
            return ;
        }
        while (res.moveToNext()){
            int img = givImg(res.getString(0).toString());
            userList.add(new ModelClass(img,res.getString(0),res.getString(3),res.getString(5),divider));
            System.out.println(res.getString(0).toString()+" "+res.getString(1).toString()+" "+res.getString(2).toString()+" "+res.getString(3).toString()+" "+res.getString(4).toString()+" ");
        }
//

    }
    private int givImg(String s){
        s.toLowerCase();
        System.out.println("checker ="+s);
        if(s.charAt(0)=='a'||s.charAt(0)=='A')return R.drawable.ic_a;
        else if(s.charAt(0)=='b'||s.charAt(0)=='B')return R.drawable.ic_b;
        else if(s.charAt(0)=='c'||s.charAt(0)=='C')return R.drawable.ic_c;
        else if(s.charAt(0)=='d'||s.charAt(0)=='D')return R.drawable.ic_d;
        else if(s.charAt(0)=='e'||s.charAt(0)=='E')return R.drawable.ic_e;
        else if(s.charAt(0)=='f'||s.charAt(0)=='F')return R.drawable.ic_f;
        else if(s.charAt(0)=='g'||s.charAt(0)=='G')return R.drawable.ic_g;
        else if(s.charAt(0)=='h'||s.charAt(0)=='H')return R.drawable.ic_h;
        else if(s.charAt(0)=='i'||s.charAt(0)=='I')return R.drawable.ic_i;
        else if(s.charAt(0)=='j'||s.charAt(0)=='J')return R.drawable.ic_j;
        else if(s.charAt(0)=='k'||s.charAt(0)=='K')return R.drawable.ic_k;
        else if(s.charAt(0)=='l'||s.charAt(0)=='L')return R.drawable.ic_l;
        else if(s.charAt(0)=='m'||s.charAt(0)=='M')return R.drawable.ic_m;
        else if(s.charAt(0)=='n'||s.charAt(0)=='N')return R.drawable.ic_n;
        else if(s.charAt(0)=='o'||s.charAt(0)=='O')return R.drawable.ic_o;
        else if(s.charAt(0)=='p'||s.charAt(0)=='P')return R.drawable.ic_p;
        else if(s.charAt(0)=='q'||s.charAt(0)=='Q')return R.drawable.ic_q;
        else if(s.charAt(0)=='r'||s.charAt(0)=='R')return R.drawable.ic_r;
        else if(s.charAt(0)=='s'||s.charAt(0)=='S')return R.drawable.ic_s;
        else if(s.charAt(0)=='t'||s.charAt(0)=='T')return R.drawable.ic_t;
        else if(s.charAt(0)=='u'||s.charAt(0)=='U')return R.drawable.ic_u;
        else if(s.charAt(0)=='v'||s.charAt(0)=='V')return R.drawable.ic_v;
        else if(s.charAt(0)=='w'||s.charAt(0)=='W')return R.drawable.ic_w;
        else if(s.charAt(0)=='x'||s.charAt(0)=='X')return R.drawable.ic_x;
        else if(s.charAt(0)=='y'||s.charAt(0)=='Y')return R.drawable.ic_y;
        else if(s.charAt(0)=='z'||s.charAt(0)=='Z')return R.drawable.ic_z;
        else return R.drawable.ic_bluelogo;
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new Adapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.setClickListner(this);
        adapter.notifyDataSetChanged();
    }
    public void onClick(View view,int position){

        final ModelClass model = userList.get(position);
        StringBuffer buffer = new StringBuffer();
        buffer.append(model.getTextview1());

        Cursor res = DB.getSpecificData(buffer.toString());


        if(res.getCount()==0){
            Toast.makeText(ViewActivity.this,"No Entry Exits",Toast.LENGTH_SHORT).show();
            return ;
        }
        StringBuffer buffer1 = new StringBuffer();
        buffer1.append("Name : "+res.getString(0)+"\n");
        buffer1.append("Domain : "+res.getString(1)+"\n");
        buffer1.append("Year of Formation : "+res.getString(2)+"\n");
        buffer1.append("Department : "+res.getString(3)+"\n");
        buffer1.append("Faculty Coordinator : "+res.getString(4)+"\n");
        buffer1.append("President Name : "+res.getString(5)+"\n");
        buffer1.append("No of Active Members :"+res.getString(6)+"\n");
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Club Details");
        builder.setMessage(buffer1);
        builder.show();
    }
}