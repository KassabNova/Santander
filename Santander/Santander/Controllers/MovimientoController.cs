using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Santander.Models.Entidades.Respuestas;
using Santander.Models.Entidades.Solicitudes;
using Santander.Models.Entidades;
using Santander.Models.Helpers;

namespace Santander.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class MovimientoController : ControllerBase
    {
        [HttpPost]
        public RespuestaTransferencia Transferencia([FromBody] SolicitudTransferencia solicitud)
        {
            RespuestaTransferencia respuesta = new RespuestaTransferencia();

            if (solicitud.Monto == 0 || solicitud.TarjetaOrigen.NumTarjeta == null)
            {
                respuesta.ResultadoOperacion.Tipo = TipoResultado.INCOMPLETE;
                respuesta.ResultadoOperacion.Detalle = "Solicitud Incompleta";
            }
            else
            {
                if(solicitud.PagoTerceros)SantanderHelper.TransferenciaTerceros(solicitud.TarjetaOrigen.NumTarjeta, solicitud.Monto, solicitud.Detalle, out respuesta.ResultadoOperacion);
                else SantanderHelper.TransferenciaInterbancaria(solicitud.TarjetaOrigen.NumTarjeta, solicitud.TarjetaDestino.NumTarjeta, solicitud.Monto, solicitud.Detalle, out respuesta.ResultadoOperacion);
                //respuesta.credito = creditos;
            }


            return respuesta;
        }
        [HttpPost]
        public RespuestaLogin Pagos([FromBody] Cliente cliente)
        {
            RespuestaLogin respuesta = new RespuestaLogin();


            return respuesta;
        }
    }
}
