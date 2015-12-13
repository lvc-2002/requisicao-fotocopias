use bd_trabalho;
delimiter //
create function GeraNumeroRequisicao() returns char(11)
begin
declare ultimo_registro int;
if (select dayofyear(curdate())) = 1 then
if (select count(ano) from sequencia_requisicao where ano = (select extract(year from curdate()))) = 0 then
insert into sequencia_requisicao values((select extract(year from curdate())), 0);
end if;
end if;
set ultimo_registro = (select numero from sequencia_requisicao where ano = (select extract(year from curdate())));
set ultimo_registro = ultimo_registro + 1;
update sequencia_requisicao set numero = ultimo_registro where ano = (select extract(year from curdate()));
set @resultado = (select concat(extract(year from curdate()), '/', lpad(ultimo_registro, 6, '0')));
return @resultado;
end
//