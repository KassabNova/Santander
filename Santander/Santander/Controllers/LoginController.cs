using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Santander.Models.Entidades.Respuestas;
using Santander.Models.Entidades;

namespace Santander.Controllers
{
    [Route("api/[controller]/")]
    [ApiController]
    public class LoginController : ControllerBase
    {
        // GET: api/Login
        [HttpGet]
        public RespuestaLogin Get()
        {
            RespuestaLogin respuesta = new RespuestaLogin();
            Cliente cliente = new Cliente();
            cliente.Cuenta = 1;
            cliente.ID = 123;
            cliente.Password = "AGM#$C:!";
            cliente.UltimaConexion = "HOY";
            cliente.FechaCreacion = "AYER";
            respuesta.cliente = cliente;
            respuesta.ResultadoOperacion = new ResultadoOperacion();
            respuesta.ResultadoOperacion.Tipo = TipoResultado.NO_ERROR;
            respuesta.ResultadoOperacion.Detalle = "DETALLE";
            return respuesta;
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
       

        // GET: api/Login/5
        [HttpGet("{id}", Name = "Get")]
        public string Get(int id)
        {
            return "value";
        }

        // POST: api/Login
        

        // PUT: api/Login/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // DELETE: api/ApiWithActions/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}
