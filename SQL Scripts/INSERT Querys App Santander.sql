INSERT INTO tblCliente	VALUES 
			( 1, 'Carlos', 'Kassab', 'André',  1, 'uag', GETDATE(), GETDATE()),
			( 2, 'Yosu', 'Tanamachi', 'Sanchez', 2, 'uag',GETDATE(), GETDATE()),
			(3, 'Luis', 'Cuevas', 'Garcia', 3, 'uag', GETDATE(), GETDATE() )
INSERT INTO tblCuenta	VALUES 
			( 1, 2, 1, 2,   GETDATE(), 'Ahorros'),
			( 2, 3, 2, 1,   GETDATE(), 'Nomina'),
			( 3, 1, 3, 2,   GETDATE(), 'Credito')
INSERT INTO tblTarjeta	VALUES 
			(42 , 3, 4242.42, 'Ahorros',0),
			(69, 3, 1979.69 , 'Nomina',0),
			(1337, 3, -1337.13, 'Credito',2000.0),
			(1000, 1, 1554.58 , 'Nomina',0),
			(1001, 1, 0.15 , 'Ahorros',0),
			(1002, 2, 14000.90 , 'Ahorros',0),
			(1003, 2, 1979.69 , 'Nomina',0)
INSERT INTO tblCredito(NumCuenta, Saldo, Interes, Tipo, PlazoMeses, FechaCorte, FechaPago, FechaPlazo)
			VALUES
			( 3 , 498525 , 32.19, 'Hipotecario',240,DATEADD(day, 10, GETDATE()), DATEADD(day, 34, GETDATE()), DATEADD(month, 240, GETDATE()) ),
			( 1 , 195462 , 28.18, 'Hipotecario',300,DATEADD(day, 5, GETDATE()), DATEADD(day, 25, GETDATE()),DATEADD(month, 300, GETDATE())),
			( 3 , 25463 , 18.36, 'Nomina', 4,DATEADD(day, 2, GETDATE()), DATEADD(day, 22, GETDATE()),DATEADD(month, 4, GETDATE())),
			( 1 , 192346,  47.82, 'Automotriz',36,DATEADD(day, 8, GETDATE()), DATEADD(day, 28, GETDATE()),DATEADD(month, 36, GETDATE()))

INSERT INTO tblMovimiento( NumTarjeta, Monto, Fecha, IdConcepto, Detalle)
VALUES( 1337,-300.1, GETDATE(), 7,'Prueba' );
INSERT INTO tblMovimiento(NumTarjeta, Monto, Fecha, IdConcepto, Detalle) 
VALUES(4242,300.1, GETDATE(), 7,'Prueba' )