using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using Santander.Models.Entidades;

namespace Santander.Models.DataAccess
{
    public class DASantander
    {
        private static string CONEXION = "Data Source = localhost; Initial Catalog = Santander; Integrated Security = SSPI";

     
        internal static List<Sucursal> ObtenerSucursales()
        {
            List<Sucursal> sucursales = new List<Sucursal>();

            SqlConnection conexion = new SqlConnection(CONEXION);

            string query = "SELECT * FROM tblSucursal";
            try
            {
                using (conexion)
                {
                    using (SqlCommand comando = new SqlCommand(query, conexion))
                    {

                        comando.CommandTimeout = 600;
                        comando.CommandType = CommandType.Text;
                        conexion.Open();
                        using (SqlDataReader lector = comando.ExecuteReader())
                        {
                            if (lector.HasRows)
                            {
                                while (lector.Read())
                                {


                                    sucursales.Add(new Sucursal()
                                    {

                                        IdSucursal = Int32.Parse(lector["IdSucursal"].ToString()),
                                        Nombre = lector["NombreSucursal"].ToString(),
                                        Direccion = lector["Direccion"].ToString(),
                                        Tipo = lector["Tipo"].ToString(),
                                        Longitud = lector["Longitud"].ToString(),
                                        Latitud = lector["Latitud"].ToString()

                                    });

                                }
                            }
                            conexion.Close();
                        }
                    }
                }

            }
            catch
            {
                sucursales = null;
            }
            finally
            {
                if (conexion != null && conexion.State != ConnectionState.Closed)
                {
                    conexion.Close();
                }
            }

            return sucursales;
        }

        internal static bool ValidarSesion(int idCliente, string password)
        {
            bool login = false;
            SqlConnection conexion = new SqlConnection(CONEXION);

            string query = "SELECT * FROM tblCliente WHERE IdCliente = " + idCliente.ToString() +
                            " AND Contrasena = '" + password + "'" ;
            try
            {
                using (conexion)
                {
                    using (SqlCommand comando = new SqlCommand(query, conexion))
                    {

                        comando.CommandTimeout = 600;
                        comando.CommandType = CommandType.Text;
                        conexion.Open();
                        using (SqlDataReader lector = comando.ExecuteReader())
                        {
                            if (lector.HasRows)
                            {
                                login = true;
                            }
                            conexion.Close();
                        }
                    }
                }

            }
            catch(Exception e)
            {
                login = false;
            }
            finally
            {
                if (conexion != null && conexion.State != ConnectionState.Closed)
                {
                    conexion.Close();
                }
            }

            return login;
        }

        internal static List<Tarjeta> ObtenerTarjetas(int idCliente)
        {
            List<Tarjeta> tarjetas = new List<Tarjeta>();

            SqlConnection conexion = new SqlConnection(CONEXION);

            string query = "SELECT C.IdCliente, T.NumTarjeta,  T.NumCuenta, T.Saldo, T.LimiteCredito, T.Tipo FROM tblTarjeta T " +
                           "JOIN tblCuenta C on C.NumCuenta = T.NumCuenta WHERE C.IdCliente = " + idCliente.ToString();
            try
            {
                using (conexion)
                {
                    using (SqlCommand comando = new SqlCommand(query, conexion))
                    {

                        comando.CommandTimeout = 600;
                        comando.CommandType = CommandType.Text;
                        conexion.Open();
                        using (SqlDataReader lector = comando.ExecuteReader())
                        {
                            if (lector.HasRows)
                            {
                                while (lector.Read())
                                {
                                    tarjetas.Add(new Tarjeta()
                                    {

                                        NumTarjeta = lector["IdCliente"].ToString(),
                                        NumCuenta = Int32.Parse(lector["NumTarjeta"].ToString()),
                                        Saldo = Double.Parse(lector["Saldo"].ToString()),
                                        Tipo = lector["Tipo"].ToString(),
                                        LimiteCredito = lector["LimiteCredito"].ToString(),

                                    });
                                    
                                }
                            }
                            conexion.Close();
                        }
                    }
                }

            }
            catch
            {
                tarjetas = null;
            }
            finally
            {
                if (conexion != null && conexion.State != ConnectionState.Closed)
                {
                    conexion.Close();
                }
            }

            return tarjetas;
        }
    }
}
