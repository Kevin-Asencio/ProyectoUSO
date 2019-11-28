package com.uso.proyectouso;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import Entidades.LoginSW;
import Entidades.Usuario;

public class    Login extends AppCompatActivity {
    //Declaracion de variables
    private TextInputLayout contenedorUsuario, contenedorPassword;
    private EditText edtusuario, edtPassword;
    private Button btnAceptar;
    private SwitchMaterial switchCompat;
    private SharedPreferences recordarLogin;


    //variables estaticas
    public static final String NAME_FILE = "RecordarDatos";
    public static final String KEY_USUARIO = "usuario";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_SWITCH = "Switch";
    public static Usuario user;

    public String usertext = "";
    public String passtext = "";
    boolean Switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnAceptar = findViewById(R.id.btnAceptar);
        contenedorUsuario = findViewById(R.id.contenedor_usuario);
        contenedorPassword = findViewById(R.id.contenedor_password);
        edtusuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        switchCompat = findViewById(R.id.switch_signin);

        //guarda los datos
        this.recordarLogin = getSharedPreferences(NAME_FILE, Context.MODE_PRIVATE);
        cargarDatos();

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validarDatos();
                if (ContextCompat.checkSelfPermission(Login.this, Manifest.permission.INTERNET)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(Login.this,
                            Manifest.permission.INTERNET)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            AlertDialog.Builder dlg=new AlertDialog.Builder(Login.this);
                            dlg.setTitle("Permisos de intener Desactivados");
                            dlg.setMessage("Debe aceptar los permisos para poder conectarse al Servicio Web");
                            dlg.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                       requestPermissions(new String[]{Manifest.permission.INTERNET},100);
                                }
                            });
                       }
                    } else {
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(Login.this,
                                new String[]{Manifest.permission.INTERNET},
                                100);
                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                }else
                    {
                        peticionLogin();
                    }

            }
        });
    }

    public  void peticionLogin() {
        LoginSW login=new LoginSW(this.edtusuario.getText().toString(),this.edtPassword.getText().toString());
        login.execute();
        try {
            Thread.sleep(1000);
            if(login.getUserAux()!=null)
            {
                user=login.getUserAux();
                Intent intent=new Intent(this,Principal.class);
                startActivity(intent);
            }else
                {
                    Toast.makeText(this, "Datos Erroneos", Toast.LENGTH_SHORT).show();
                }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void cargarDatos() {
        this.usertext = recordarLogin.getString(KEY_USUARIO, "");
        this.passtext = recordarLogin.getString(KEY_PASSWORD, "");
        this.Switch = recordarLogin.getBoolean(KEY_SWITCH, false);
        switchCompat.setChecked(Switch);
        edtusuario.setText(usertext);
        edtPassword.setText(passtext);
    }

    //cargar los datos
    public void obtenerDatos() {
        if (switchCompat.isChecked()) {
            SharedPreferences.Editor editor = recordarLogin.edit();
            editor.putString(KEY_USUARIO, edtusuario.getText().toString());
            editor.putString(KEY_PASSWORD, edtPassword.getText().toString());
            editor.putBoolean(KEY_SWITCH, switchCompat.isChecked());
            editor.apply();

        } else {
            SharedPreferences.Editor editor = recordarLogin.edit();
            editor.putString(KEY_USUARIO, "");
            editor.putString(KEY_PASSWORD, "");
            editor.putBoolean(KEY_SWITCH, switchCompat.isChecked());
            editor.apply();
        }
    }

    private boolean validarUsuario(String usuario) {
        Pattern patron = Pattern.compile("^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
        if (!patron.matcher(usuario).matches() || usuario.length() > 20 || usuario.length() < 3) {
            contenedorUsuario.setError(getString(R.string.lbl_usuario_invalido));
            contenedorPassword.requestFocus();
            return false;
        } else {
            contenedorUsuario.setError(null);
        }

        return true;
    }

    private boolean validarPassword(String password) {
        if (password.length() < 1) {
            contenedorPassword.setError(getString(R.string.lbl_contraseña_invalida));
            contenedorPassword.requestFocus();
            return false;

        } else {
            contenedorPassword.setError(null);
        }
        return true;
    }

    private void validarDatos() {
        String user = edtusuario.getText().toString().trim();
        String pass = edtPassword.getText().toString().trim();

        boolean a = validarUsuario(user);
        boolean b = validarPassword(pass);

        if (a && b) {
            obtenerDatos();
            validarLogin();
        }

    }

    private void validarLogin() {
        //variables para validacion de los datos
        String userPrueba = "root";
        String passwordPrueba = "admin";
        String user=edtusuario.getText().toString().trim();
        String pass=edtPassword.getText().toString().trim();

        if (user.equals(userPrueba) && pass.equals(passwordPrueba)) {
            new AlertDialog.Builder(Login.this).setTitle(getString(R.string.lbl_title_successful)).setMessage(getString(R.string.lbl_msj_successful) + user)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //guardarDatos();
                            Intent i = new Intent(Login.this, Principal.class);
                            startActivity(i);
                        }
                    }).show();
        } else {
            new AlertDialog.Builder(Login.this).setTitle(getString(R.string.lbl_title_error)).setMessage(getString(R.string.lbl_msj_error))
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            edtusuario.setText("");
                            edtPassword.setText("");
                            edtusuario.requestFocus();
                        }
                    }).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 100: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    peticionLogin();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
