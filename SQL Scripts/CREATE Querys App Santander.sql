
CREATE TABLE tblCuenta (
    NumCuenta int NOT NULL PRIMARY KEY,
	IdCliente int FOREIGN KEY REFERENCES tblCuenta(NumCuenta) NOT NULL,
	NumCuentaPadre int NOT NULL,
    IdSucursal int FOREIGN KEY REFERENCES tblSucursal(IdSucursal)  NOT NULL,
	FechaCreacion DATE NOT NULL,
	Tipo varchar (30) NOT NULL
);

CREATE TABLE tblCliente (
    IdCliente int NOT NULL PRIMARY KEY,
	Nombre varchar (30),
	Paterno varchar (30),
	Materno varchar (30),
    NumCuenta int    NULL,
	Contrasena varchar (30) NOT NULL,
	FechaCreacion DATETIME2 NOT NULL,
	UltimaConexion DATETIME2 NOT NULL
);

CREATE TABLE tblTarjeta (
    NumTarjeta int NOT NULL PRIMARY KEY,
	NumCuenta  int FOREIGN KEY REFERENCES tblCuenta(NumCuenta) NOT NULL,
	Saldo float NOT NULL,
	Tipo varchar (30) NOT NULL,
    LimiteCredito decimal NULL
);




CREATE TABLE tblMovimiento (
    ID  int IDENTITY  PRIMARY KEY NOT NULL,
	NumTarjeta  int FOREIGN KEY REFERENCES tblTarjeta(NumTarjeta) NOT NULL,
	Monto float NOT NULL,
	Fecha DATETIME2 NOT NULL, 
	IdConcepto int FOREIGN KEY REFERENCES tblConcepto(IdConcepto) NOT NULL,
	Detalle varchar (255) NOT NULL,
);


CREATE TABLE tblCredito (
    IdCredito int IDENTITY NOT NULL PRIMARY KEY,
	NumCuenta  int FOREIGN KEY REFERENCES tblCuenta(NumCuenta) NOT NULL,
	Saldo float NOT NULL,
	Interes float NOT NULL,
	Tipo varchar (30) NOT NULL,
	PlazoMeses int NOT NULL,
	FechaCorte DATETIME2 NOT NULL,
	FechaPago DATETIME2 NOT NULL,
	FechaPlazo DATETIME2 NOT NULL
);



CREATE TABLE tblConcepto (
    IdConcepto  int IDENTITY  PRIMARY KEY NOT NULL,
	Concepto varchar (255) NOT NULL
);

CREATE TABLE tblSucursal(
	[IdSucursal] [int] IDENTITY PRIMARY KEY NOT NULL,
	[NombreSucursal] [varchar](50) NULL,
	[Direccion] [varchar](255) NULL,
	[Tipo] [varchar](20) NULL,
	[Longitud] [varchar](20) NULL,
	[Latitud] [varchar](20) NULL
)