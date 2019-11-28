package Entidades;

import android.os.AsyncTask;

import com.uso.proyectouso.Navegacion.Contactos.ContactosFragment;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Adaptadores.AdaptadorCliente;

public class ClientesSW extends AsyncTask <Clientes,Integer,Boolean>{
    private List<Clientes> listClientes;

    public ClientesSW()
    {
        this.listClientes=new ArrayList<Clientes>();
    }
    @Override
    protected Boolean doInBackground(Clientes... clientes) {
        String NAMESPACE="http://alveletric.org/";
        String URL="http://192.168.0.13/alvelectric/Services.asmx";
        String METHOD_NAME="consultaClientes";
        String SOAP_ACTION="http://alveletric.org/consultaClientes";
        SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME);
        SoapObject result=null;

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE transportSE=new HttpTransportSE(URL);

        try {
            transportSE.call(SOAP_ACTION,envelope);
            result=(SoapObject)envelope.getResponse();
            this.listClientes=new ArrayList<Clientes>();
            for(int i=0;i<result.getPropertyCount();i++)
            {
                SoapObject aux=(SoapObject) result.getProperty(i);
                Clientes cliente=new Clientes();
                cliente.set_Nombres(aux.getPropertyAsString("Nombres"));
                cliente.set_Apellidos(aux.getPropertyAsString("Apellidos"));
                cliente.set_DUI(aux.getPropertyAsString("DUI"));
                cliente.set_NIT(aux.getPropertyAsString("NIT"));
                cliente.set_Telefono(aux.getPropertyAsString("Telefono"));
                this.listClientes.add(cliente);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return false;
    }
    protected void onPostExecute(Boolean result)
    {
        AdaptadorCliente adp =new AdaptadorCliente(this.listClientes);
        ContactosFragment.recyclerView.setAdapter(adp);
    }
    public List<Clientes> getListClientes() {
        return listClientes;
    }

    public void setListClientes(List<Clientes> listClientes) {
        this.listClientes = listClientes;
    }
}
