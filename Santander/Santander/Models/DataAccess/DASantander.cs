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
        private static string CONEXIONDEV = "Data Source = localhost; Initial Catalog = Santander; Integrated Security = SSPI";
        private static string CONEXION = "Data Source = localhost; Initial Catalog = Santander;  Persist Security Info=True; User ID = Kebab; Password=kassab123;Pooling=False";
     
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

        internal static List<Credito> ObtenerCreditos(int idCliente)
        {
            List<Credito> creditos = new List<Credito>();

            SqlConnection conexion = new SqlConnection(CONEXION);

            string query = " select Cr.IdCredito, Cr.Saldo, Cr.Interes, Cr.Tipo, Cr.PlazoMeses, Cr.FechaCorte, Cr.FechaPago, Cr.FechaPlazo from tblCredito Cr" +
                           " join tblCliente Cl on Cl.NumCuenta = Cr.NumCuenta" +
                           " where Cl.IdCliente = " + idCliente.ToString();

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
                                    creditos.Add(new Credito()
                                    {

                                        IdCredito = Int32.Parse(lector["IdCredito"].ToString()),
                                        Saldo = Double.Parse(lector["Saldo"].ToString()),
                                        Interes = Double.Parse(lector["Interes"].ToString()),
                                        Tipo = lector["Tipo"].ToString(),
                                        PlazoMeses = Int32.Parse(lector["PlazoMeses"].ToString()),
                                        FechaCorte = DateTime.Parse(lector["FechaCorte"].ToString()),
                                        FechaPago = DateTime.Parse(lector["FechaPago"].ToString()),
                                        FechaPlazo = DateTime.Parse(lector["FechaPlazo"].ToString())

                                    });

                                }
                            }
                            conexion.Close();
                        }
                    }
                }

            }
            catch(Exception e)
            {
                creditos = null;
            }
            finally
            {
                if (conexion != null && conexion.State != ConnectionState.Closed)
                {
                    conexion.Close();
                }
            }

            return creditos;
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

        internal static bool RegistrarMovimiento(string idTarjetaOrigen, string idTarjetaDestino, double monto, string detalle)
        {
            bool respuesta = false;
            SqlConnection conexion = new SqlConnection(CONEXION);

            string query = "INSERT INTO tblMovimiento( NumTarjeta, Monto, Fecha, IdConcepto, Detalle) VALUES( " +
                idTarjetaOrigen.ToString() + ",-" + monto.ToString() + ", GETDATE(), 7," + detalle + ")" +
                "INSERT INTO tblMovimiento(NumTarjeta, Monto, Fecha, IdConcepto, Detalle) VALUES(" +
               idTarjetaDestino.ToString() + "," + monto.ToString() + ", GETDATE(), 7," + detalle + ")" ;
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
                            if (lector.RecordsAffected == 2) respuesta = true;
                            else respuesta = false;
                            conexion.Close();
                        }
                    }
                }

            }
            catch (Exception e)
            {
                respuesta = false;
            }
            finally
            {
                if (conexion != null && conexion.State != ConnectionState.Closed)
                {
                    conexion.Close();
                }
            }
            return respuesta;
        }
        
    }
}
