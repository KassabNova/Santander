using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Santander.Models.Entidades.Respuestas
{
    public class RespuestaCliente
    {
        public ResultadoOperacion ResultadoOperacion;

        public Cliente cliente;

        public List<Tarjeta> tarjetas;

        public double saldo;
    }
}
