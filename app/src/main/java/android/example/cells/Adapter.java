package android.example.cells;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {
    private List<ModelClass> userList;
    private  ItemClickListener clickListner;
    public Adapter(List<ModelClass>userList){this.userList=userList;}
    @NonNull
    @Override
    public Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.viewHolder holder, int position) {
        int resource = userList.get(position).getImageview1();
        String name = userList.get(position).getTextview1();
        String dep = userList.get(position).getTextview3();
        String pname = userList.get(position).getTextview2();
        String line = userList.get(position).getDivider();

        holder.setData(resource,name,dep,pname,line);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
    public void  setClickListner(ItemClickListener itemClickListener){
        this.clickListner = itemClickListener;
    }

    public class viewHolder  extends  RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView textView;
        private TextView textView2;
        private  TextView textView3;
        private  TextView divider;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview1);
            textView = itemView.findViewById(R.id.textview);
            textView2 = itemView.findViewById(R.id.textview2);
            textView3 = itemView.findViewById(R.id.textview3);
            divider = itemView.findViewById(R.id.divider);
            itemView.setOnClickListener(this);

        }

        public void setData(int resource, String name, String dep, String pname, String line) {
            imageView.setImageResource(resource);
            textView.setText("Name :"+name);
            textView2.setText("Department :"+pname);
            textView3.setText("President's Name :"+dep);
            divider.setText(line);
        }
        @Override
        public void onClick(View view){
            if(clickListner!=null)clickListner.onClick(view,getAbsoluteAdapterPosition());
        }
    }
}
