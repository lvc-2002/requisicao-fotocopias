create trigger numero_requisicao BEFORE INSERT ON requisicao FOR EACH ROW
begin
set new.numero = GeraNumeroRequisicao();
end