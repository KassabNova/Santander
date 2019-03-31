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
        internal static List<Sucursal> ObtenerSucursales(out ResultadoOperacion resultado)
        {
            List<Sucursal> sucursales = new List<Sucursal>();
            resultado = new ResultadoOperacion();
            try
            {
                
                sucursales = DASantander.ObtenerSucursales();
                if (sucursales != null && sucursales.Count() > 0)
                {

                    resultado.Tipo = TipoResultado.NO_ERROR;
                    resultado.Detalle = "Sucursales obtenidas correctamente";
                }
                else
                {
                    resultado.Tipo = TipoResultado.NOT_FOUND;
                }
            }
            catch(Exception e)
            {
                sucursales = null;
                resultado.Tipo = TipoResultado.DATA_ACCESS_ERROR;
                resultado.Detalle = "Error en el acceso a los datos";
            }
            return sucursales;
        }
    }
}
