import {html, LitElement} from "lit";
import {ajax} from "@lion/ajax";

export class OrderPanel extends LitElement {

  constructor() {
    super();
    this.renderOrders();
    this.subscribeToChanges();
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
      tbody.appendChild(
        this.createTableRow(order)
      );
    });

  }

  createTableRow(order) {
    const tr = document.createElement("tr")
    tr.id = `order-${order.orderId}`

    tr.innerHTML = this.getTableRowColumns(order);
    return tr;
  }

  getTableRowColumns(order) {
    return `
        <td>${order.orderId}</td>
        <td>${order.userId}</td>
        <td>
          <lion-tooltip has-arrow>
            <span slot="invoker">${order.description.substring(0, 10)}...</span>
            <div slot="content">${order.description}</div>
          </lion-tooltip>
        </td>
        <td>${order.status}</td>
        <td>${order.createdAt}</td>
    `;
  }

  subscribeToChanges() {
    const eventSource = new EventSource("http://localhost:8081/order/changes");

    eventSource.addEventListener("ORDER_CHANGE", evt => {
      let order = JSON.parse(evt.data);

      const tableRow = this.shadowRoot.getElementById(`order-${order?.orderId}`);

      if (tableRow) {
        tableRow.innerHTML = this.getTableRowColumns(order);
      } else {
        const tbody = this.shadowRoot.getElementById("orders-body");
        tbody.appendChild(
          this.createTableRow(order)
        );
      }
    })
  }

}
customElements.define('order-panel', OrderPanel)
