✨ GlossyHive – E-commerce de Maquiagens 💄

Este projeto é um e-commerce de maquiagens chamado GlossyHive, desenvolvido para simular o processo de pagamento utilizando o ambiente Sandbox do PayPal.

🌐 Acesse o site: https://www.glossyhive.com

Ele possui:
- **Front-end** em React + JavaScript
- **Back-end** em Java + Spring Boot
- Integração com a **API REST do PayPal**

---

  Principais funcionalidades

✔️ Catálogo de produtos com imagens, preço e categorias
✔️ Carrinho de compras dinâmico (adicionar, remover, alterar quantidade)
✔️ Persistência dos produtos e pedidos no backend
✔️ Fluxo real de pagamento usando PayPal REST API (Sandbox)
✔️ Deploy integrado com Amazon S3 + CloudFront (CDN)
✔️ Suporte a CORS para comunicação entre front e back

## 🧰 Tecnologias Utilizadas

### Front-end:
- React
- JavaScript
- HTML / CSS

### Back-end:
- Java
- Spring Boot
- REST API

### Deploy / Infraestrutura:

Hospedado no AWS S3 (Front)

Distribuído via CloudFront (CDN)

API hospedada em EC2 / Elastic Beanstalk (dependendo da etapa do desenvolvimento)

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

- [Montagem da Requisição de pedido para o PayPal](./docs/build-paypal.md)
