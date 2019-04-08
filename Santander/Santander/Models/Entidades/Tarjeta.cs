using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Santander.Models.Entidades
{
    public class Tarjeta
    {

        public String NumTarjeta { get; set; }

        public int NumCuenta { get; set; }

        public double Saldo { get; set; }

        public String Tipo { get; set; }
        
        public String LimiteCredito { get; set; }

        public int usuario { get; set; }

    }
}
