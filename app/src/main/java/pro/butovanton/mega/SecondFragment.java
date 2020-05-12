package pro.butovanton.mega;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import pro.butovanton.mega.R;
import pro.butovanton.mega.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment implements OnMapReadyCallback {

    private ImageView imageView;
    public FragmentSecondBinding binding;
    private ViewModelDetail viewModelDetail;
    private GoogleMap gleMap;
    private ModelDetail mDetail;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false);
        binding.mapView.onCreate(savedInstanceState);
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
                if (modelDetail.getPhoneVisible())
                    binding.textTel.setVisibility(View.VISIBLE);
                 else
                    binding.textTel.setVisibility(View.GONE);
                if (modelDetail.getWwwVisible())
                    binding.textViewWww.setVisibility(View.VISIBLE);
                else
                    binding.textViewWww.setVisibility(View.GONE);

                binding.setModelDetail(modelDetail);
                Picasso
                        .get()
                        .load(modelDetail.img)
                        .into(binding.imageViewSecond);
                if (modelDetail.getLon().equals("0")) binding.mapView.setVisibility(View.GONE);
                else {
                    binding.mapView.setVisibility(View.VISIBLE);
                    mDetail = modelDetail;

                    binding.mapView.getMapAsync(SecondFragment.this::onMapReady);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        int RECT = 1;
        this.gleMap = googleMap;
        Double longitude = Double.parseDouble(mDetail.getLon());
        Double latitude = Double.parseDouble(mDetail.getLat());
        LatLng place = new LatLng(latitude, longitude);
        gleMap.addMarker(new MarkerOptions().position(place).title(mDetail.getName()));

        LatLngBounds AUSTRALIA = new LatLngBounds(
                new LatLng(latitude - RECT, longitude - RECT), new LatLng(latitude + RECT, longitude + RECT));
        gleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(AUSTRALIA, 0));
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        binding.mapView.onLowMemory();
    }
}
