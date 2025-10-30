# E-commerce de Maquiagens 💄

Este projeto é um e-commerce de maquiagens desenvolvido para **simular o processo de pagamento utilizando o sandbox do PayPal**.

Ele possui:
- **Front-end** em React + JavaScript
- **Back-end** em Java + Spring Boot
- Integração com a **API REST do PayPal**

---

## 🛍️ Funcionalidades

- Exibição de produtos com:
    - Foto
    - Nome
    - Descrição
    - Valor
- Carrinho de compras
- Cálculo do valor total
- Finalização de pagamento via **PayPal Sandbox**

---

## 🧰 Tecnologias Utilizadas

### Front-end:
- React
- JavaScript
- HTML / CSS

### Back-end:
- Java
- Spring Boot
- REST API

### Pagamentos:
- PayPal Sandbox (REST API)

---


---

## 🔄 Fluxo do Pagamento com PayPal

1. O usuário adiciona produtos ao carrinho.
2. O front-end envia o carrinho para o back-end calcular o valor total.
3. O back-end calcula o valor dos itens e retorna o total ao front-end.
4. Ao confirmar o carrinho, o usuário clica em um botão e um pedido é criado.
5. O PayPal responde com um **approval link**.
6. O usuário é redirecionado para o PayPal para confirmar o pagamento.
---

Aqui estão seções detalhadas da implementação do envio de pedidos para o PayPal:

- [Montagem da Requisição de pedido para o PayPal](./build-paypal.md)
