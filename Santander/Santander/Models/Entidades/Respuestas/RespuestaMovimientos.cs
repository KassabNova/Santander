﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Santander.Models.Entidades.Respuestas
{
    public class RespuestaMovimientos
    {
        public ResultadoOperacion ResultadoOperacion;

        public List<Movimiento> Movimientos;
    }
}
