package Entidades;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class LoginSW extends AsyncTask <Usuario,Integer, Boolean>{
    private Usuario userAux;
    private String user;
    private String pass;
    public  LoginSW(String us,String pas)
    {
        this.userAux=new Usuario();
        this.setUser(us);
        this.setPass(pas);
    }

    @Override
    protected Boolean doInBackground(Usuario... usuarios) {
        String NAMESPACE="http://alveletric.org/";
        String URL="http://192.168.0.13/alvelectric/Services.asmx";
        String METHOD_NAME="Login";
        String SOAP_ACTION="http://alveletric.org/Login";
        SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME);
        request.addProperty("nombre",this.user);
        request.addProperty("pass",this.pass);
        SoapObject result=null;

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE transportSE=new HttpTransportSE(URL);

        try {
            transportSE.call(SOAP_ACTION,envelope);
            result=(SoapObject)envelope.getResponse();
            this.userAux=new Usuario();
            this.userAux.set_Usuario(result.getProperty(4).toString());
            this.userAux.set_Rol(result.getProperty(0).toString());
            this.userAux.set_Empleado(result.getProperty(1).toString());
            this.userAux.set_IDEmpleado(result.getProperty(2).toString());
            this.userAux.set_IDRol(result.getProperty(3).toString());
            this.userAux.set_IDUsuario(result.getProperty(5).toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        return false;
    }
   public Usuario getUserAux() {
        return userAux;
    }

    public void setUserAux(Usuario userAux) {
        this.userAux = userAux;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


}
