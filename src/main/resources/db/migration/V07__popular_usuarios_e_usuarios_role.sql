
insert into usuario(codigo, PASSWORD, USERNAME) values(1, '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', '123');
insert into usuario(codigo, PASSWORD, USERNAME) values(2, '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', '1234');
insert into usuario_ROLE(codigoUsuario, ROLE) values(1, 'ADMIN');
insert into usuario_ROLE(codigoUsuario, ROLE) values(1, 'PREMIUM_MEMBER');
insert into usuario_ROLE(codigoUsuario, ROLE) values(2, 'PREMIUM_MEMBER');
commit
