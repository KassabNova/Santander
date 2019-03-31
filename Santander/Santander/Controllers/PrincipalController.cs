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
            respuesta.cliente = cliente;
            respuesta.ResultadoOperacion = new ResultadoOperacion();
            respuesta.ResultadoOperacion.Tipo = TipoResultado.NO_ERROR;
            respuesta.ResultadoOperacion.Detalle = "DETALLE";
            respuesta.saldo = 42;
            return respuesta;
        }
        [HttpPost]
        public RespuestaLogin Consultas([FromBody] Cliente cliente)
        {
            RespuestaLogin respuesta = new RespuestaLogin();

            cliente.UltimaConexion = "HOY";
            respuesta.cliente = cliente;
            respuesta.ResultadoOperacion = new ResultadoOperacion();
            respuesta.ResultadoOperacion.Tipo = TipoResultado.NO_ERROR;
            respuesta.ResultadoOperacion.Detalle = "TEST";
            
            return respuesta;
        }
        [HttpPost]
        public RespuestaLogin Pagos([FromBody] Cliente cliente)
        {
            RespuestaLogin respuesta = new RespuestaLogin();

            cliente.UltimaConexion = "HOY";
            respuesta.cliente = cliente;
            respuesta.ResultadoOperacion = new ResultadoOperacion();
            respuesta.ResultadoOperacion.Tipo = TipoResultado.NO_ERROR;
            respuesta.ResultadoOperacion.Detalle = "TEST";

            return respuesta;
        }
        [HttpPost]
        public RespuestaLogin Creditos([FromBody] Cliente cliente)
        {
            RespuestaLogin respuesta = new RespuestaLogin();

            cliente.UltimaConexion = "HOY";
            respuesta.cliente = cliente;
            respuesta.ResultadoOperacion = new ResultadoOperacion();
            respuesta.ResultadoOperacion.Tipo = TipoResultado.NO_ERROR;
            respuesta.ResultadoOperacion.Detalle = "TEST";

            return respuesta;
        }

        [HttpPost]
        public RespuestaSucursales Sucursales()
        {
            RespuestaSucursales respuesta = new RespuestaSucursales();
            
            List<Sucursal> sucursales = new List<Sucursal>();
            sucursales = SantanderHelper.ObtenerSucursales(out respuesta.ResultadoOperacion);
            respuesta.sucursales = sucursales;
            //Sucursal sucursal1 = new Sucursal();
            //Sucursal sucursal2 = new Sucursal();
            //List<Sucursal> sucursales = new List<Sucursal>();
            //sucursal1.IdSucursal = 1;
            //sucursal2.IdSucursal = 2;
            //sucursal1.Nombre = "Sucursal1";
            //sucursal2.Nombre = "Sucursal2";
            //sucursal1.Tipo = "Banco";
            //sucursal2.Tipo = "Cajero";
            //sucursales.Add(sucursal1);
            //sucursales.Add(sucursal2);
            //respuesta.sucursales = sucursales;
            

            return respuesta;
        }
    }
}
