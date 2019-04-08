using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Santander.Models.Entidades
{
    public class Movimiento
    {
        public int Id { get; set; }

        public double Monto { get; set; }

        public String IdTarjeta { get; set; }

        public DateTime Fecha { get; set; }

        public Concepto Concepto { get; set; }

        public String Detalle { get; set; }
    }
    public enum Concepto
    {
        TRANSFERENCIA,
        PAGO,
        COMPRA

    }
}
