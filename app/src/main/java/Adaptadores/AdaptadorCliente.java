package Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uso.proyectouso.R;

import java.util.List;

import Entidades.Clientes;

public class AdaptadorCliente extends RecyclerView.Adapter<viewHolderCliente>{
    List<Clientes> source;

    public AdaptadorCliente(List<Clientes> lst)
    {
        this.source=lst;
    }
    @NonNull
    @Override
    public viewHolderCliente onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente,parent,false);
        viewHolderCliente vhClientes=new viewHolderCliente(view);
        return vhClientes;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderCliente holder, int position) {
        holder.getTxvNombres().setText(source.get(position).get_Nombres()+""+source.get(position).get_Apellidos());
        holder.getTxvDUI().setText(source.get(position).get_DUI());
        holder.getTxvNIT().setText(source.get(position).get_NIT());
        holder.getTxvTelefono().setText(source.get(position).get_Telefono());
    }

    @Override
    public int getItemCount() {
        return source.size();
    }
}
