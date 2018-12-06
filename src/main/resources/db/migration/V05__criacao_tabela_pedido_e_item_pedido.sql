CREATE TABLE pedido (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_criacao DATETIME NOT NULL,
    valor_desconto DECIMAL(10,2),
    valor_total DECIMAL(10,2) NOT NULL,
    status VARCHAR(30) NOT NULL,
    cliente_id BIGINT(20) NOT NULL,
    usuario_id BIGINT(20) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE item_pedido (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    quantidade INTEGER NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,
    produto_id BIGINT(20) NOT NULL,
    pedido_id BIGINT(20) NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produto(id),
    FOREIGN KEY (pedido_id) REFERENCES pedido(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
