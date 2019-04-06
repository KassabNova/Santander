using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Santander.Models.Entidades.Solicitudes
{
    public class SolicitudTransferencia
    {
        public Tarjeta TarjetaOrigen;

        public Tarjeta TarjetaDestino;

        public double Monto;

        public bool PagoTerceros;

        public String Detalle;
    }
}
