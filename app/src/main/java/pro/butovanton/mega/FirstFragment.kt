package pro.butovanton.mega

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FirstFragment : Fragment(), ItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private var adapter: RecyclerAdapterMega? = null
    private var lm: LinearLayoutManager? = null
    private var viewModelModel: ViewModelModel? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.reciclerView)
        adapter = RecyclerAdapterMega(this, context)
        lm = LinearLayoutManager(context)
        recyclerView.layoutManager = lm
        recyclerView.setAdapter(adapter)
        viewModelModel = ViewModelProvider(this).get(ViewModelModel::class.java)
        viewModelModel!!.getModel().observe(viewLifecycleOwner, Observer { models -> adapter!!.set(models) })
    }

    override fun onItemClick(id: String?) {
        Log.d("DEBUG", "click $id")
        val bundle = Bundle()
        bundle.putString("id", id)
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }
}