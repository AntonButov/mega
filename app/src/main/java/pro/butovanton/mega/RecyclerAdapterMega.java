package pro.butovanton.mega;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pro.butovanton.mega.R;

class RecyclerAdapterMega extends RecyclerView.Adapter<RecyclerAdapterMega.ViewHolderMega> {

    private ItemClickListener itemClickListener;
    private List<MModel> listModel;
    private final LayoutInflater mInflater;
    private Context context;

    public RecyclerAdapterMega(ItemClickListener clickListener, Context context) {
        this.itemClickListener = clickListener;
        mInflater = LayoutInflater.from(context);
        listModel = new ArrayList<>();
        this.context = context;
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
        holder.idTextView.setText(listModel.get(position).name);

        Picasso
                .get()
                .load(listModel.get(position).img)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(listModel.get(position).id);
            }
        });

    }

    public void set(List<MModel> models) {
        listModel = models;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (listModel == null) return 0;
        return listModel.size();
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
