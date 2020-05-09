package pro.butovanton.mega;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pro.butovanton.mega.R;

class RecyclerAdapterMega extends RecyclerView.Adapter<RecyclerAdapterMega.ViewHolderMega> {

    private FirstFragment firstFragment;
    private List<MModel> listUsers;
    private final LayoutInflater mInflater;

    public RecyclerAdapterMega(FirstFragment firstFragment, Context context) {
        this.firstFragment = firstFragment;
        mInflater = LayoutInflater.from(context);
        listUsers = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolderMega onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item, parent, false);
        ViewHolderMega vh = new ViewHolderMega(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapterMega.ViewHolderMega holder, final int position) {
        holder.idTextView.setText(listUsers.get(position).id);

        Picasso
                .get()
                .load(listUsers
                .get(position)
                .img)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstFragment.onItemClick(listUsers.get(position).id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }


    public void adnotify(List<MModel> listUsers) {
        this.listUsers = listUsers;

        notifyDataSetChanged();
    }

    public class ViewHolderMega extends RecyclerView.ViewHolder {
        private final TextView idTextView;
        private final ImageView imageView;


        public ViewHolderMega(View view) {
            super(view);
            idTextView = (TextView) view.findViewById(R.id.name);
            imageView =  view.findViewById(R.id.imageView);

        }


    }

}
