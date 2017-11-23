CREATE TABLE pedido (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_criacao DATETIME NOT NULL,
    valor_desconto DECIMAL(10,2),
    valor_total DECIMAL(10,2) NOT NULL,
    status VARCHAR(30) NOT NULL,
    codigo_cliente BIGINT(20) NOT NULL,
    codigo_usuario BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo),
    FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE item_pedido (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    quantidade INTEGER NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,
    codigo_produto BIGINT(20) NOT NULL,
    codigo_pedido BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_produto) REFERENCES produto(codigo),
    FOREIGN KEY (codigo_pedido) REFERENCES pedido(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;