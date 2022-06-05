package sv.edu.catolica.project_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ListWorkerAdapter extends RecyclerView.Adapter<ListWorkerAdapter.ViewHolder> {
    private List<ListWorker> workers;
    private LayoutInflater mInflater;
    private Context context;

    public ListWorkerAdapter(List<ListWorker> workers, Context context) {
        this.workers = workers;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount(){ return workers.size(); }

    @Override
    public  ListWorkerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListWorkerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListWorkerAdapter.ViewHolder holder, final int position){
        holder.bindData(workers.get(position));
    }

    public void setItems(List<ListWorker> items) { workers = items; }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView name, info, money, date;

        ViewHolder(View itemView){
            super(itemView);

            iconImage = itemView.findViewById(R.id.profile_image);
            name = itemView.findViewById(R.id.nombre);
            info = itemView.findViewById(R.id.information);
            money = itemView.findViewById(R.id.money);
            date = itemView.findViewById(R.id.date);
        }

        void bindData(final ListWorker item){
            name.setText(item.getNombre());
            info.setText(item.getProfesion() + " - " + item.getDepartamento());
            money.setText(item.getPrecio());
            date.setText("Hace 5 dias");
        }
    }
}
