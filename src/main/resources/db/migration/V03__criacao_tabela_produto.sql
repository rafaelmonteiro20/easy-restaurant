CREATE TABLE produto (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    sku VARCHAR(8) NOT NULL,
    nome VARCHAR(60) NOT NULL,
    descricao TEXT,
    valor_unitario DECIMAL(10, 2) NOT NULL,
    qtd_estoque INTEGER NOT NULL,
    ativo BOOLEAN NOT NULL,
    foto VARCHAR(150),
    content_type VARCHAR(30),
    codigo_categoria BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;