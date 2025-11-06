✨ GlossyHive – E-commerce de Maquiagens 💄

Este projeto é um e-commerce de maquiagens chamado GlossyHive, desenvolvido para simular o processo de pagamento utilizando o ambiente Sandbox do PayPal.

🌐 Acesse o site: https://www.glossyhive.com

## ✨ Principais Funcionalidades

- Catálogo de produtos com imagens, preços e categorias
- Carrinho de compras dinâmico (adicionar, remover e alterar quantidade de itens)
- Persistência dos produtos e pedidos no backend
- Fluxo real de pagamento utilizando a PayPal REST API (Sandbox)
- Deploy do frontend na AWS (S3 integrado ao CloudFront)
- Suporte a CORS para comunicação entre frontend e backend

---

## 🧰 Tecnologias Utilizadas

### Frontend
- React (Vite)
- JavaScript
- HTML / CSS
- Axios

### Backend
- Java 21
- Spring Boot
- REST API

### Deploy / Infraestrutura
- AWS S3 (hospedagem do frontend)
- CloudFront (CDN para distribuição)
- EC2 / Elastic Beanstalk (hospedagem da API)

### Pagamentos
- PayPal REST API (Sandbox)

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
