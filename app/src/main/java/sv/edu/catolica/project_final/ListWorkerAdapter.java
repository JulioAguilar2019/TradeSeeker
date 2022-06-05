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

public class ListWorkerAdapter extends RecyclerView.Adapter<ListWorkerAdapter.ViewHolder> implements View.OnClickListener {
    private List<ListWorker> workers;
    private View.OnClickListener clickListener;
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

        view.setOnClickListener(this);

        return new ListWorkerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListWorkerAdapter.ViewHolder holder, final int position){
        holder.bindData(workers.get(position));
    }

    public void setItems(List<ListWorker> items) { workers = items; }

    public void setOnClickListener(View.OnClickListener listener){
        this.clickListener = listener;
    }

    @Override
    public void onClick(View view) {
        if (clickListener != null){
            clickListener.onClick(view);
        }
    }

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
            name.setText(item.worker.getName());
            info.setText(item.getWork().getProfession() + " - " + item.getWork().getState().getName());
            money.setText(item.getWork().getPrice());
            date.setText("5 dias atras");
        }
    }
}
