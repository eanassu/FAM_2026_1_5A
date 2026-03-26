create table Funcionarios(
  re int,
  nome varchar(100),
  dataAdmissao date,
  salario decimal(15,2),
  primary key(re)
)


create table Alunos(
  ra int,
  nome varchar(100),
  dataNascimento date,
  renda decimal(15,2),
  primary key(ra));