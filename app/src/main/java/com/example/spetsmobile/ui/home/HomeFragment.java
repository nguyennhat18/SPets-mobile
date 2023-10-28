package com.example.spetsmobile.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.spetsmobile.R;
import com.example.spetsmobile.databinding.FragmentHomeBinding;
import com.example.spetsmobile.ui.pet.PetFragment;
import com.example.spetsmobile.util.ConstantUtil;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView tvHello = root.findViewById(R.id.tvHello);
        tvHello.setText("Xin chào, " + ConstantUtil.getAuth().getFullname());

        final CardView cvPet = root.findViewById(R.id.cvPet);
        cvPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // Tạo một thể hiện của PetFragment
//                PetFragment petFragment = new PetFragment();
//
//                // Sử dụng FragmentManager để thực hiện giao dịch Fragment
//                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_pet, petFragment); // R.id.fragment_container là ID của Container View trong layout
//                transaction.addToBackStack(null); // (Tùy chọn) Cho phép người dùng nhấn nút back để quay lại HomeFragment
//                transaction.commit();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}