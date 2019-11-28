package Entidades;

import android.os.AsyncTask;

import com.uso.proyectouso.Navegacion.Contactos.ProveedoresFragment;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Adaptadores.AdaptadorProveedores;

public class ProveedoresSW extends AsyncTask<Usuario,Integer, Boolean> {
    private List<Proveedores>listProveedores;

    public ProveedoresSW()
    {
        this.listProveedores=null;
    }

    @Override
    protected Boolean doInBackground(Usuario... usuarios) {
        String NAMESPACE="http://alveletric.org/";
        String URL="http://192.168.0.13/alvelectric/Services.asmx";
        String METHOD_NAME="consultaProveedores";
        String SOAP_ACTION="http://alveletric.org/consultaProveedores";
        SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME);
        SoapObject result=null;

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE transportSE=new HttpTransportSE(URL);

        try {
            transportSE.call(SOAP_ACTION,envelope);
            result=(SoapObject)envelope.getResponse();
            this.listProveedores=new ArrayList<Proveedores>();


            for(int i=0;i<result.getPropertyCount();i++)
            {
                SoapObject aux=(SoapObject) result.getProperty(i);
                Proveedores prov=new Proveedores();
                prov.set_Correo(aux.getProperty("Correo").toString());
                prov.set_Direccion(aux.getProperty("Direccion").toString());
                prov.set_IDProveedor(aux.getProperty("IDProveedor").toString());
                prov.set_Proveedor(aux.getProperty("Proveedor").toString());
                prov.set_Telefono(aux.getPropertyAsString("Telefono"));
                listProveedores.add(prov);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected void onPostExecute(Boolean result)
    {
        AdaptadorProveedores adp =new AdaptadorProveedores(this.listProveedores);
        ProveedoresFragment.recyclerView.setAdapter(adp);
    }
}

