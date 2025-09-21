import {html, LitElement} from "lit";
import {ajax} from "@lion/ajax";

export class OrderPanel extends LitElement {

  constructor() {
    super();
    this.renderOrders();
  }

  render() {
    return html`
    <table>
      <caption>List of orders</caption>
      <thead>
        <tr>
          <th scope="col">ID</th>
          <th scope="col">UserId</th>
          <th scope="col">Description</th>
          <th scope="col">Status</th>
          <th scope="col">Created</th>
        </tr>
      </thead>
      <tbody id="orders-body">

      </tbody>
    </table>
    `;
  }

  async getOrders() {
    const {response, body} = await ajax.fetchJson('http://localhost:8081/order/all', {
    })
    return body
  }

  async renderOrders() {
    const orders = await this.getOrders()

    const tbody = this.shadowRoot.getElementById("orders-body");
    tbody.textContent = "";

    orders.forEach(order => {
      const tr = document.createElement("tr")

      const tdId = document.createElement("td")
      tdId.textContent = order.orderId;
      tr.appendChild(tdId);

      const tdUserId = document.createElement("td")
      tdUserId.textContent = order.userId;
      tr.appendChild(tdUserId);

      const tdDescription = document.createElement("td")
      tdDescription.innerHTML = `
        <lion-tooltip has-arrow>
          <span slot="invoker">${order.description.substring(0, 10)}...</span>
          <div slot="content">${order.description}</div>
        </lion-tooltip>
      `;
      tr.appendChild(tdDescription);

      const tdStatus = document.createElement("td")
      tdStatus.textContent = order.status;
      tr.appendChild(tdStatus);

      const tdCreatedAt = document.createElement("td")
      tdCreatedAt.textContent = order.createdAt;
      tr.appendChild(tdCreatedAt);

      tbody.appendChild(tr);
    });

  }
}
customElements.define('order-panel', OrderPanel)
