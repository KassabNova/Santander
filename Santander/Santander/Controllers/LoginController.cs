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
    [Route("api/[controller]/")]
    [ApiController]
    public class LoginController : ControllerBase
    {
        // GET: api/Login
       
        [HttpPost]
        public RespuestaLogin Post([FromBody] Cliente cliente)
        {
            RespuestaLogin respuesta = new RespuestaLogin();
            bool login = SantanderHelper.LoginCliente(cliente.usuario, cliente.Password, out respuesta.ResultadoOperacion);
            respuesta.login = login;
            
            return respuesta;
        }
       

    }
}
