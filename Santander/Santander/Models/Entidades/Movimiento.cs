using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Santander.Models.Entidades
{
    public class Movimiento
    {
        public int Id { get; set; }

        public int Monto { get; set; }

        public String IdTarjeta { get; set; }

        public String Fecha { get; set; }

        public Concepto concepto { get; set; }

        public String Detalle { get; set; }
    }
    public enum Concepto
    {
        TRANSFERENCIA,
        PAGO,
        COMPRA

    }
}
