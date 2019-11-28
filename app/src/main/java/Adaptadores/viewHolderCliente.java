package Adaptadores;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uso.proyectouso.R;

public class viewHolderCliente extends RecyclerView.ViewHolder {
    private TextView txvNombres;
    private TextView txvDUI;
    private  TextView txvNIT;
    private  TextView txvTelefono;
    public viewHolderCliente(@NonNull View itemView) {
        super(itemView);
        this.txvNombres=itemView.findViewById(R.id.txvNombre);
        this.txvDUI=itemView.findViewById(R.id.txvDUI);
        this.txvNIT=itemView.findViewById(R.id.txvNIT);
        this.txvTelefono=itemView.findViewById(R.id.txvTelefono);
    }

    public TextView getTxvNombres() {
        return txvNombres;
    }

    public TextView getTxvDUI() {
        return txvDUI;
    }

    public TextView getTxvNIT() {
        return txvNIT;
    }

    public TextView getTxvTelefono() {
        return txvTelefono;
    }
}
