package pro.butovanton.mega;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pro.butovanton.mega.R;

public class FirstFragment extends Fragment implements ItemClickListener {

    private RecyclerView recyclerView;
    private RecyclerAdapterMega adapter;
    private LinearLayoutManager lm;
    private ProgressBar progressBar;

    private ViewModelModel viewModelModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);

        recyclerView = view.findViewById(R.id.reciclerView);
        adapter = new RecyclerAdapterMega(this, getContext());
        lm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lm);

        recyclerView.setAdapter(adapter);

        viewModelModel = new ViewModelProvider(this).get(ViewModelModel.class);
        viewModelModel.getModel().observe(getViewLifecycleOwner(), new Observer<List<MModel>>() {
            @Override
            public void onChanged(List<MModel> mModels) {

            }
        });
    }

    @Override
    public void onItemClick(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
    }
}
