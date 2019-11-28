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

import Entidades.ClientesSW;


public class ContactosFragment extends Fragment {
    private ClientesSW Peticion_clientes;
    public static RecyclerView recyclerView;
    private LinearLayoutManager lManager;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contactos, container, false);
        this.Peticion_clientes=new ClientesSW();
        Peticion_clientes.execute();
        this.recyclerView=root.findViewById(R.id.rclClientes);
        this.lManager=new LinearLayoutManager(getContext());


        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(this.lManager);
        return root;
    }
}
