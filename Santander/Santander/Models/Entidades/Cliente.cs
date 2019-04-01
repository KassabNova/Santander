using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Santander.Models.Entidades
{
    public class Cliente
    {
        public int usuario { get; set; }

        public int NumCuenta { get; set; }

        public String Nombres { get; set; }

        public String Paterno { get; set; }

        public String Materno { get; set; }
        
        public String Password { get; set; }

        public String FechaCreacion { get; set; }

        public String UltimaConexion { get; set; }
    }
}
