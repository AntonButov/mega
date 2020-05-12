package pro.butovanton.mega

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import pro.butovanton.mega.databinding.FragmentSecondBinding

class SecondFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentSecondBinding
    private var viewModelDetail: ViewModelDetail? = null
    private var gleMap: GoogleMap? = null
    private var mDetail: ModelDetail? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)
        binding.mapView.onCreate(savedInstanceState)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val id = bundle!!.getString("id")
        viewModelDetail = ViewModelDetail()
        viewModelDetail!!.getModelDetail(id!!.toInt()).observe(viewLifecycleOwner, Observer { modelDetail ->
            if (modelDetail.phoneVisible) binding.textTel.visibility = View.VISIBLE else binding.textTel.visibility = View.GONE
            if (modelDetail.wwwVisible) binding.textViewWww.visibility = View.VISIBLE else binding.textViewWww.visibility = View.GONE
            binding.modelDetail = modelDetail
            Picasso
                    .get()
                    .load(modelDetail.img)
                    .into(binding.imageViewSecond)
            if (modelDetail.lon == "0") binding.mapView.visibility = View.GONE else {
                binding.mapView.visibility = View.VISIBLE
                mDetail = modelDetail
                binding.mapView.getMapAsync { googleMap: GoogleMap -> onMapReady(googleMap) }
            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val RECT = 1
        gleMap = googleMap
        val longitude = mDetail!!.lon!!.toDouble()
        val latitude = mDetail!!.lat!!.toDouble()
        val place = LatLng(latitude, longitude)
        gleMap!!.addMarker(MarkerOptions().position(place).title(mDetail!!.name))
        val AUSTRALIA = LatLngBounds(
                LatLng(latitude - RECT, longitude - RECT), LatLng(latitude + RECT, longitude + RECT))
        gleMap!!.moveCamera(CameraUpdateFactory.newLatLngBounds(AUSTRALIA, 0))
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding!!.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding!!.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding!!.mapView.onLowMemory()
    }
}