﻿using System;
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
        public RespuestaCredito Creditos([FromBody] Cliente cliente)
        {
            RespuestaCredito respuesta = new RespuestaCredito();
            List<Credito> creditos = new List<Credito>();

            if (cliente == null || cliente.usuario == 0)
            {
                respuesta.ResultadoOperacion.Tipo = TipoResultado.INCOMPLETE;
                respuesta.ResultadoOperacion.Detalle = "Solicitud Incompleta";
            }
            else
            {
                creditos = SantanderHelper.ObtenerCreditos(cliente.usuario,out respuesta.ResultadoOperacion);
                respuesta.credito = creditos;
            }
            

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
                foreach (Tarjeta tarjeta in tarjetas)
                {
                    saldo += tarjeta.Saldo;
                    tarjeta.usuario = cliente.usuario;
                }
            }
            respuesta.tarjetas = tarjetas;
            respuesta.saldo = saldo;
            respuesta.cliente = cliente;
            return respuesta;
        }
    }
}
