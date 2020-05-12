package pro.butovanton.mega;

import android.content.Intent;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import pro.butovanton.mega.R;
import pro.butovanton.mega.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment implements OnMapReadyCallback {

    private ImageView imageView;
    public FragmentSecondBinding binding;
    private ViewModelDetail viewModelDetail;
    private GoogleMap gleMap;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false);
        binding.mapView.onCreate(savedInstanceState);
        binding.mapView.getMapAsync(this);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





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
                        .into(binding.imageViewSecond);
            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MapsActivity2.class);
               // myIntent.putExtra("key", value); //Optional parameters
                getActivity().startActivity(myIntent);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.gleMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        gleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        gleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.mapView.onResume();
    }
}
