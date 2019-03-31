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
        private static string CONEXION = "Data Source = localhost; Initial Catalog = LittlePets; Integrated Security = SSPI";

        internal static List<Sucursal> ObtenerSucursales(int a)
        {
            throw new NotImplementedException();
        }
        public static List<Sucursal> ObtenerSucursales()
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

    }
}
