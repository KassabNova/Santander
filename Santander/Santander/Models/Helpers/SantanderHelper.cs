using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Santander.Models.Entidades;
using Santander.Models.DataAccess;

namespace Santander.Models.Helpers
{
    public class SantanderHelper
    {
        internal static List<Sucursal> ObtenerSucursales(out ResultadoOperacion resultadoOperacion)
        {
            List<Sucursal> sucursales = new List<Sucursal>();
            resultadoOperacion = new ResultadoOperacion();
            try
            {

                sucursales = DASantander.ObtenerSucursales();
                if (sucursales != null && sucursales.Count() > 0)
                {

                    resultadoOperacion.Tipo = TipoResultado.NO_ERROR;
                    resultadoOperacion.Detalle = "Sucursales obtenidas correctamente";
                }
                else
                {
                    resultadoOperacion.Tipo = TipoResultado.NOT_FOUND;
                }
            }
            catch (Exception e)
            {
                sucursales = null;
                resultadoOperacion.Tipo = TipoResultado.DATA_ACCESS_ERROR;
                resultadoOperacion.Detalle = "Error en el acceso a los datos: " + e.Message;
            }
            return sucursales;
        }

        internal static bool LoginCliente(int idCliente, string password, out ResultadoOperacion resultadoOperacion)
        {
            resultadoOperacion = new ResultadoOperacion();
            bool login = false;
            try
            {
                if (password != null) login = DASantander.ValidarSesion(idCliente, password);
                else
                {
                    resultadoOperacion.Tipo = TipoResultado.NOT_FOUND;
                    resultadoOperacion.Detalle = "Error validando sesion";
                }
                if (login)
                {
                    resultadoOperacion.Tipo = TipoResultado.NO_ERROR;
                    resultadoOperacion.Detalle = "Login Correcto";
                }
                else
                {
                    resultadoOperacion.Tipo = TipoResultado.NOT_FOUND;
                    resultadoOperacion.Detalle = "Error validando sesion";
                }
            }
            catch (Exception e)
            {
                login = false;
                resultadoOperacion.Tipo = TipoResultado.DATA_ACCESS_ERROR;
                resultadoOperacion.Detalle = "Error en el acceso a los datos: " + e.Message;
            }
            return login;
        }

        internal static List<Credito> ObtenerCreditos(int idCliente, out ResultadoOperacion resultadoOperacion)
        {
            List<Credito> creditos = new List<Credito>();
            resultadoOperacion = new ResultadoOperacion();
            try
            {

                creditos = DASantander.ObtenerCreditos(idCliente);
                if (creditos != null && creditos.Count() > 0)
                {

                    resultadoOperacion.Tipo = TipoResultado.NO_ERROR;
                    resultadoOperacion.Detalle = "Creditos obtenidas a la perfección!";
                }
                else
                {
                    resultadoOperacion.Tipo = TipoResultado.NOT_FOUND;
                    resultadoOperacion.Detalle = "El cliente no tiene créditos";
                }
            }
            catch (Exception e)
            {
                creditos = null;
                resultadoOperacion.Tipo = TipoResultado.DATA_ACCESS_ERROR;
                resultadoOperacion.Detalle = "Error en el acceso a los datos: " + e.Message;
            }
            return creditos;
        }

        internal static List<Tarjeta> ObtenerTarjetas(int idCliente, out ResultadoOperacion resultadoOperacion)
        {
            List<Tarjeta> tarjetas = new List<Tarjeta>();
            resultadoOperacion = new ResultadoOperacion();
            try
            {

                tarjetas = DASantander.ObtenerTarjetas(idCliente);
                if (tarjetas != null && tarjetas.Count() > 0)
                {

                    resultadoOperacion.Tipo = TipoResultado.NO_ERROR;
                    resultadoOperacion.Detalle = "Tarjetas obtenidas correctamente";
                }
                else
                {
                    resultadoOperacion.Tipo = TipoResultado.NOT_FOUND;
                    resultadoOperacion.Detalle = "El cliente no tiene tarjetas";
                }
            }
            catch (Exception e)
            {
                tarjetas = null;
                resultadoOperacion.Tipo = TipoResultado.DATA_ACCESS_ERROR;
                resultadoOperacion.Detalle = "Error en el acceso a los datos: " + e.Message;
            }
            return tarjetas;
        }

        internal static List<Movimiento> ConsultarMovimiento(int idCliente, int idTarjeta, out ResultadoOperacion resultadoOperacion)
        {
            List<Movimiento> movimientos = new List<Movimiento>();
            resultadoOperacion = new ResultadoOperacion();

            return movimientos;
        }

        internal static void TransferenciaInterbancaria(string idTarjetaOrigen, string idTarjetaDestino, double monto, String detalle, out ResultadoOperacion resultadoOperacion)
        {
            resultadoOperacion = new ResultadoOperacion();
            bool registrada = false;
            try
            {

                registrada = RegistrarMovimiento( idTarjetaOrigen, idTarjetaDestino, monto, detalle);
                if (registrada)
                {

                    resultadoOperacion.Tipo = TipoResultado.NO_ERROR;
                    resultadoOperacion.Detalle = "Transferencia exitosa!";
                }
                else
                {
                    resultadoOperacion.Tipo = TipoResultado.NOT_FOUND;
                    resultadoOperacion.Detalle = "El cliente no tiene tarjetas";
                }
            }
            catch (Exception e)
            {
                resultadoOperacion.Tipo = TipoResultado.DATA_ACCESS_ERROR;
                resultadoOperacion.Detalle = "Error en el acceso a los datos: " + e.Message;
            }
        }

        internal static void TransferenciaTerceros(string idTarjetaOrigen, double monto, String detalle, out ResultadoOperacion resultadoOperacion)
        {
            resultadoOperacion = new ResultadoOperacion();
        }

        private static bool RegistrarMovimiento(string idTarjetaOrigen, double monto, String detalle)
        {
            resultadoOperacion = new ResultadoOperacion();
            return false;
        }
        private static bool RegistrarMovimiento(string idTarjetaOrigen, string idTarjetaDestino, double monto, String detalle)
        {
            return false;
        }


    }
}
