package Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uso.proyectouso.R;

import java.util.List;

import Entidades.Proveedores;

public class AdaptadorProveedores extends RecyclerView.Adapter<viewHolderProveedor> {
    private List<Proveedores> source;

    public AdaptadorProveedores(List<Proveedores> lst)
    {
        this.source=lst;
    }
    @NonNull
    @Override
    public viewHolderProveedor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proveedor,parent,false);
        viewHolderProveedor vhProveedor=new viewHolderProveedor(view);
        return vhProveedor;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderProveedor holder, int position) {
        holder.getTxvProveedor().setText(this.source.get(position).get_Proveedor());
        holder.getTxvContacto().setText(source.get(position).get_NombreContacto());
        holder.getTxvCorreo().setText(source.get(position).get_Correo());
        holder.getTxvTelefono().setText(source.get(position).get_Telefono());
        holder.getTxvDireccion().setText(source.get(position).get_Direccion());
    }

    @Override
    public int getItemCount() {
        return source.size();
    }
}
