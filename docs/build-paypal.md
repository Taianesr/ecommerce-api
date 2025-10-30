# Implementação do Envio de Pedidos para o PayPal

Este documento descreve o fluxo de construção do pedido enviado à API do PayPal, incluindo a montagem dos itens, cálculo de valores e configuração de parâmetros relacionados ao checkout.

## 📦 Recebimento da Solicitação

A requisição que inicia o envio do pedido recebe no **corpo da requisição** uma **lista de objetos contendo o SKU e a quantidade** desejada pelo usuário.

Para cada SKU informado:

- O produto é buscado no banco de dados.
- São recuperadas informações como **nome, descrição e valor do produto**.
- Essas informações são usadas para compor os objetos que serão enviados ao PayPal.

Os produtos encontrados são armazenados em um **Map**, onde:
- **Chave:** SKU
- **Valor:** Quantidade

---

## 🧱 Construção do Corpo da Requisição

### 1. Items

A lista de itens enviados ao PayPal é construída a partir do Map de produtos.

Para cada produto:
- É criado um objeto **UnitAmount**, contendo:
    - Valor unitário
    - Código da moeda (`BRL`)
- Em seguida, é criado um objeto **Item**, contendo:
    - Nome
    - Descrição
    - Quantidade
    - Categoria
    - Demais propriedades relevantes
    - O objeto `UnitAmount`

---

### 2. Amount (Valor Total)

O valor total do pedido é calculado somando:

- valor_unitário * quantidade de cada item

Além disso:
- O valor do frete é definido como **R$10,00**.
- É criado um objeto **ItemTotal** contendo o somatório dos produtos e o código da moeda definido como BRL.
- É criado um objeto **Shipping** contendo o valor do frete e o código da moeda definido como BRL.
- Esses valores são agrupados em um objeto **BreakDown**.
- O objeto **Amount** contém:
    - Valor total final **(produtos + frete)**
    - Código da moeda (`BRL`)
    - O objeto `BreakDown`

---

## ⚙️ Configurações do PayPal

### Build Paypal Types
Existem dois tipos possíveis:

| Tipo | Descrição |
|------|-----------|
| `UNRESTRICTED` | Permite qualquer método de pagamento. |
| `IMMEDIATE_PAYMENT_REQUIRED` | Permite apenas pagamentos imediatos (ex: cartão). |

> **Utilizado no projeto:** `IMMEDIATE_PAYMENT_REQUIRED`.

---

### Landing Page
Define a página inicial exibida ao usuário quando iniciar o processo de pagamento:

| Tipo | Comportamento |
|------|--------------|
| `LOGIN` | Redireciona para a página de login. |
| `GUEST_CHECKOUT` | Permite informar método de pagamento sem login. |
| `NO_PREFERENCE` | Usuário escolhe login ou checkout como convidado. |

> **Utilizado no projeto:** `GUEST_CHECKOUT`.

---

### Shipping Preference
Determina de onde virá o endereço de entrega:

| Tipo | Descrição |
|------|-----------|
| `GET_FROM_FILE` | Usa o endereço armazenado no PayPal do cliente. |
| `NO_SHIPPING` | Remove informações de entrega no checkout. |
| `SET_PROVIDED_ADDRESS` | Usa o endereço fornecido pelo comerciante. |

> **Utilizado no projeto:** `GET_FROM_FILE`.

---

### User Action

Define o fluxo após redirecionamento para o PayPal:

| Ação | Descrição | Uso Indicado |
|------|----------|--------------|
| `CONTINUE` | Exibe botão "Continuar" | Quando o valor final ainda pode mudar. |
| `PAY_NOW` | Exibe botão "Pagar Agora" | Quando o valor final já é conhecido. |

> **Utilizado no projeto:** `PAY_NOW`.

---

### URLs de Redirecionamento

| Campo | Finalidade |
|-------|------------|
| `ReturnUrl` | Redirecionado após **aprovação** do pagamento. |
| `CancelUrl` | Redirecionado caso **cancele** o pagamento. |

> No projeto, foram utilizadas URLs mockadas.

---

## 💳 Purchase Unit

O objeto **PurchaseUnit** recebe:
- A lista de itens
- O objeto `Amount`
- Um identificador de fatura (gerado no código)

---

## 🧾 Order (Pedido Final)

Por fim, é construído o objeto de pedido:

- `intent`: define o momento da captura do pagamento
    - `CAPTURE` (captura imediata) **← utilizado no projeto**
    - `AUTHORIZE` (apenas autorização, captura posterior)

- `payment_source`: define o método de pagamento (PayPal)

- `purchase_units`: contém os itens e valores calculados anteriormente

---

## ✅ Resumo das Configurações Utilizadas

| Configuração | Valor Utilizado |
|-------------|----------------|
| Build Type | `IMMEDIATE_PAYMENT_REQUIRED` |
| Landing Page | `GUEST_CHECKOUT` |
| Shipping Preference | `GET_FROM_FILE` |
| User Action | `PAY_NOW` |
| Intent | `CAPTURE` |
| Frete | `R$ 10,00` |

---