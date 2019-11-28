package Entidades;

public class Usuario {

    private String _IDUsuario;
    private String _Usuario;
    private String _IDRol;
    private String _IDEmpleado;
    private String _Empleado;
    private String _Rol;
    private String pass;

    public String get_IDUsuario() {
        return _IDUsuario;
    }

    public void set_IDUsuario(String _IDUsuario) {
        this._IDUsuario = _IDUsuario;
    }

    public String get_Usuario() {
        return _Usuario;
    }

    public void set_Usuario(String _Usuario) {
        this._Usuario = _Usuario;
    }

    public String get_IDRol() {
        return _IDRol;
    }

    public void set_IDRol(String _IDRol) {
        this._IDRol = _IDRol;
    }

    public String get_IDEmpleado() {
        return _IDEmpleado;
    }

    public void set_IDEmpleado(String _IDEmpleado) {
        this._IDEmpleado = _IDEmpleado;
    }

    public String get_Empleado() {
        return _Empleado;
    }

    public void set_Empleado(String _Empleado) {
        this._Empleado = _Empleado;
    }

    public String get_Rol() {
        return _Rol;
    }

    public void set_Rol(String _Rol) {
        this._Rol = _Rol;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
