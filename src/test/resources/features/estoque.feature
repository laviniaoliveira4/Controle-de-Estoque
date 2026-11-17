# language: pt
Funcionalidade: Controle de estoque
  Como atendente da loja
  Quero registrar entradas e saídas de produtos
  Para saber o saldo e nunca retirar mais do que existe

  Cenário: Entrada de produtos
    Dado que o produto "Caneta" tem 10 unidades em estoque
    Quando eu adiciono 5 unidades
    Então o saldo do produto deve ser 15

  Cenário: Saída com saldo suficiente
    Dado que o produto "Caneta" tem 10 unidades em estoque
    Quando eu retiro 4 unidades
    Então o saldo do produto deve ser 6

  Cenário: Saída com saldo insuficiente
    Dado que o produto "Caneta" tem 10 unidades em estoque
    Quando eu tento retirar 15 unidades
    Então a retirada deve ser recusada
    E o saldo do produto deve ser 10