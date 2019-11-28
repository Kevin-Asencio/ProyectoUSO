package com.uso.proyectouso.Navegacion.Contactos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uso.proyectouso.R;

import Entidades.ProveedoresSW;

public class ProveedoresFragment extends Fragment {

    private ProveedoresSW Peticion_prov;
    public static RecyclerView recyclerView;
    private LinearLayoutManager lManager;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_proveedores, container, false);
        // Inflate the layout for this fragment
        this.Peticion_prov=new ProveedoresSW();
        Peticion_prov.execute();
        this.recyclerView=root.findViewById(R.id.rclProveedores);
        this.lManager=new LinearLayoutManager(getContext());
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(this.lManager);
        return root;
    }
}