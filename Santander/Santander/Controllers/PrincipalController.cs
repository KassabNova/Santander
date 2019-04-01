using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Santander.Models.Entidades.Respuestas;
using Santander.Models.Entidades;
using Santander.Models.Helpers;

namespace Santander.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class PrincipalController : ControllerBase
    {
        // GET: api/Principal
        [HttpGet]
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }
        [HttpPost]
        public RespuestaLogin Post([FromBody] Cliente cliente)
        {
            RespuestaLogin respuesta = new RespuestaLogin();

            cliente.UltimaConexion = "HOY";
            //respuesta.cliente = cliente;
            //respuesta.ResultadoOperacion = new ResultadoOperacion();
            //respuesta.ResultadoOperacion.Tipo = TipoResultado.NO_ERROR;
            //respuesta.ResultadoOperacion.Detalle = "DETALLE";
            //respuesta.saldo = 42;
            return respuesta;
        }
        [HttpPost]
        public RespuestaLogin Consultas([FromBody] Cliente cliente)
        {
            RespuestaLogin respuesta = new RespuestaLogin();

            
            
            return respuesta;
        }
        [HttpPost]
        public RespuestaLogin Pagos([FromBody] Cliente cliente)
        {
            RespuestaLogin respuesta = new RespuestaLogin();


            return respuesta;
        }
        [HttpPost]
        public RespuestaLogin Creditos([FromBody] Cliente cliente)
        {
            RespuestaLogin respuesta = new RespuestaLogin();

            cliente.UltimaConexion = "HOY";
            
            return respuesta;
        }

        [HttpPost]
        public RespuestaSucursales Sucursales()
        {
            RespuestaSucursales respuesta = new RespuestaSucursales();
            
            List<Sucursal> sucursales = new List<Sucursal>();
            sucursales = SantanderHelper.ObtenerSucursales(out respuesta.ResultadoOperacion);
            respuesta.sucursales = sucursales;
           
            return respuesta;
        }
        [HttpPost]
        public RespuestaCliente Principal(Cliente cliente)
        {
            RespuestaCliente respuesta = new RespuestaCliente();
            double saldo = 0;
            List<Tarjeta> tarjetas = new List<Tarjeta>();
            tarjetas = SantanderHelper.ObtenerTarjetas(cliente.usuario, out respuesta.ResultadoOperacion);
            if (tarjetas != null && tarjetas.Count() > 0)
            {
                foreach (Tarjeta tarjeta in tarjetas) saldo += tarjeta.Saldo;
            }
            respuesta.tarjetas = tarjetas;
            respuesta.saldo = saldo;
            return respuesta;
        }
    }
}
