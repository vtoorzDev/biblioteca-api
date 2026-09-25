create table livros (
     id bigint auto_increment primary key,
     titulo varchar(300) not null,
     autor varchar(250) not null,
     isbn varchar(250) not null unique,
     no_publicacao int,
     categoria varchar(250),
     quantidade int not null
);

create table usuarios (
      id bigint auto_increment primary key,
      nome varchar(250) not null,
      email varchar(250) not null,
      telefone varchar(11),
      data_nascimento date
);

create table emprestimos (
      id bigint auto_increment primary key,
      usuario_id bigint not null,
      livro_id bigint not null,
      data_emprestimo date not null,
      status boolean not null,

                             constraint emprestimo_usuario
                                 foreign key (usuario_id) references usuarios(id),

                             constraint emprestimo_livro
                                 foreign key (livro_id) references livros(id)
);