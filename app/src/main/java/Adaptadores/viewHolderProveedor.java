package Adaptadores;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uso.proyectouso.R;

public class viewHolderProveedor extends RecyclerView.ViewHolder {
    private TextView txvProveedor;
    private TextView txvContacto;
    private  TextView txvTelefono;
    private TextView txvCorreo;
    private TextView txvDireccion;
    public viewHolderProveedor(@NonNull View itemView) {
        super(itemView);
        this.txvProveedor= itemView.findViewById(R.id.txvProveedor);
        this.txvContacto=itemView.findViewById(R.id.txvContacto);
        this.txvCorreo=itemView.findViewById(R.id.txvCorreo);
        this.txvDireccion=itemView.findViewById(R.id.txvDireccion);
        this.txvTelefono=itemView.findViewById(R.id.txvTelefonoo);
    }

    public TextView getTxvProveedor() {
        return txvProveedor;
    }

    public TextView getTxvContacto() {
        return txvContacto;
    }

    public TextView getTxvTelefono() {
        return txvTelefono;
    }

    public TextView getTxvCorreo() {
        return txvCorreo;
    }

    public TextView getTxvDireccion() {
        return txvDireccion;
    }
}
