select * from tblCliente
select * from tblCuenta
select * from tblTarjeta
select * from tblMovimiento
select * from tblCredito

INSERT INTO tblMovimiento( NumTarjeta, Monto, Fecha, IdConcepto, Detalle) VALUES( 4242,-100.1, GETDATE(), 7,'Prueba ')

INSERT INTO tblMovimiento(NumTarjeta, Monto, Fecha, IdConcepto, Detalle) VALUES(1337,100.1, GETDATE(), 7,'Prueba ') 

UPDATE tblTarjeta SET Saldo = (SELECT Saldo from tblTarjeta where NumTarjeta =4242)-100.1WHERE NumTarjeta=4242 

UPDATE tblTarjeta SET Saldo = (SELECT Saldo from tblTarjeta where NumTarjeta =1337)100.1WHERE NumTarjeta=1337

select * from tblCliente where IdCliente = 1 and Contrasena = 'uag'

select C.IdCliente, T.NumTarjeta,  T.NumCuenta,T.Saldo, T.LimiteCredito ,T.Tipo from tblTarjeta T
join tblCuenta C on C.NumCuenta = T.NumCuenta
where C.IdCliente = 1

select Cr.IdCredito, Cr.Saldo, Cr.Interes, Cr.Tipo, Cr.PlazoMeses, Cr.FechaCorte, Cr.FechaPlazo, Cr.FechaPago  from tblCredito Cr
join tblCliente Cl on Cl.NumCuenta = Cr.NumCuenta
where Cl.IdCliente = 1
