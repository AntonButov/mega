package pro.butovanton.mega;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import pro.butovanton.mega.R;
import pro.butovanton.mega.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private ImageView imageView;
    public FragmentSecondBinding binding;
    private ViewModelDetail viewModelDetail;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = binding.imageViewSecond;

        Bundle bundle = getArguments();
        String id = bundle.getString("id");

        viewModelDetail = new ViewModelDetail();
        viewModelDetail.getModelDetail(Integer.parseInt(id)).observe(getViewLifecycleOwner(), new Observer<ModelDetail>() {
            @Override
            public void onChanged(ModelDetail modelDetail) {
                binding.setModelDetail(modelDetail);
                Picasso
                        .get()
                        .load(modelDetail.img)
                        .into(imageView);
            }
        });
    }

}
