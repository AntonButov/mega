package pro.butovanton.mega;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import pro.butovanton.mega.R;
import pro.butovanton.mega.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private ImageView imageView;
    private FragmentSecondBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = binding.imageViewSecond;

        Bundle bundle = getArguments();
        String id = bundle.getString("id");


    }
}
