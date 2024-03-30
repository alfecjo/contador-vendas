
CREATE TABLE vendas (
    num_venda SERIAL PRIMARY KEY,
    data_venda DATE NOT NULL,
    data_entrega DATE NOT NULL,
    uf VARCHAR(2) NOT NULL,
    regiao VARCHAR(20) NOT NULL,
    gestor VARCHAR(100) NOT NULL,
    vendedor VARCHAR(100) NOT NULL,
    departamento VARCHAR(100) NOT NULL,
    forma_pagamento VARCHAR(100) NOT NULL,
    valor_venda DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL
);

INSERT INTO vendas (data_venda, data_entrega, uf, regiao, gestor, vendedor, departamento, forma_pagamento, valor_venda, status) VALUES
('2010-01-01', '2010-01-06', 'SP', 'Sudeste', 'Luiz Miranda', 'Adriana Gomes', 'Casa e Construção', 'Cartão de Débito', 4546.71, 'Concluída'),
('2010-01-01', '2010-01-08', 'PA', 'Norte', 'Elenice Mendes', 'Luíza Campos', 'Roupas e Acessórios', 'Cartão de Débito', 329.97, 'Concluída'),
('2010-01-02', '2010-01-11', 'AL', 'Nordeste', 'Raimundo Moraes', 'Marcos Reis', 'Móveis e Decoração', 'Cartão de Crédito', 3887.21, 'Concluída'),
('2010-01-03', '2010-01-07', 'PR', 'Sul', 'Roberta Camargo', 'Érica Macedo', 'Eletrodomésticos', 'Cartão de Crédito', 2615.29, 'Concluída'),
('2010-01-04', '2010-01-09', 'PE', 'Nordeste', 'Raimundo Moraes', 'Augusto Melo', 'Móveis e Decoração', 'Transferência Eletrônica', 1463.97, 'Concluída'),
('2010-01-05', '2010-01-14', 'SP', 'Sudeste', 'Luiz Miranda', 'Adriana Gomes', 'Casa e Construção', 'Cartão de Débito', 787.99, 'Concluída'),
('2010-01-05', '2010-01-18', 'AL', 'Nordeste', 'Raimundo Moraes', 'Marcos Reis', 'Eletrônicos', 'Cartão de Crédito', 6996.45, 'Concluída'),
('2010-01-05', '2010-01-19', 'AC', 'Norte', 'Elenice Mendes', 'Célio Carvalho', 'Esporte e Lazer', 'Cartão de Crédito', 1563.79, 'Concluída'),
('2010-01-06', '2010-01-17', 'MT', 'Centro-Oeste', 'Amauri Castro', 'Isabel Dias', 'Eletrônicos', 'Boleto Bancário', 9804.85, 'Concluída'),
('2010-01-06', '2010-01-08', 'PE', 'Nordeste', 'Raimundo Moraes', 'Augusto Melo', 'Móveis e Decoração', 'Cartão de Débito', 1775.67, 'Concluída'),
('2010-01-06', '2010-01-11', 'DF', 'Centro-Oeste', 'Amauri Castro', 'Maria José Dias', 'Esporte e Lazer', 'Cartão de Crédito', 962.09, 'Concluída'),
('2010-01-07', '2010-01-17', 'BA', 'Nordeste', 'Raimundo Moraes', 'Newton Souza', 'Móveis e Decoração', 'Boleto Bancário', 2004.53, 'Concluída'),
('2010-01-07', '2010-01-16', 'AM', 'Norte', 'Elenice Mendes', 'Daniel Leite', 'Roupas e Acessórios', 'Transferência Eletrônica', 82.21, 'Cancelada'),
('2010-01-07', '2010-01-13', 'RJ', 'Sudeste', 'Luiz Miranda', 'Daniel Campos', 'Eletrodomésticos', 'Boleto Bancário', 502.93, 'Concluída'),
('2010-01-08', '2010-01-15', 'MS', 'Centro-Oeste', 'Amauri Castro', 'Marco Aurélio Castro', 'Eletrônicos', 'Cartão de Crédito', 8404.05, 'Concluída'),
('2010-01-08', '2010-01-14', 'AC', 'Norte', 'Elenice Mendes', 'Célio Carvalho', 'Eletrodomésticos', 'Transferência Eletrônica', 1546.31, 'Concluída'),
('2010-01-08', '2010-01-16', 'CE', 'Nordeste', 'Raimundo Moraes', 'Alice da Silva', 'Eletrônicos', 'Cartão de Débito', 4421.36, 'Concluída'),
('2010-01-09', '2010-01-20', 'SC', 'Sul', 'Roberta Camargo', 'João Lima', 'Casa e Construção', 'Cartão de Débito', 6815.24, 'Concluída'),
('2010-01-09', '2010-01-12', 'MG', 'Sudeste', 'Luiz Miranda', 'Rosângela Almeida', 'Casa e Construção', 'Transferência Eletrônica', 3136.93, 'Concluída'),
('2010-01-09', '2010-01-22', 'MG', 'Sudeste', 'Luiz Miranda', 'Rosângela Almeida', 'Esporte e Lazer', 'Cartão de Débito', 1811.27, 'Cancelada'),
('2010-01-09', '2010-01-14', 'MA', 'Nordeste', 'Raimundo Moraes', 'Luciano Camargo', 'Esporte e Lazer', 'Cartão de Crédito', 785.97, 'Concluída');
