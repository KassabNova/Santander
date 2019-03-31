using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Santander.Models.Entidades
{
    public class Cliente
    {
        public int ID { get; set; }

        public int Cuenta { get; set; }

        public String Usuario { get; set; }

        public String Password { get; set; }

        public String FechaCreacion { get; set; }

        public String UltimaConexion { get; set; }
    }
}
