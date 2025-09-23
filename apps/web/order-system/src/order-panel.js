import {css, html, LitElement} from "lit";
import {ajax} from "@lion/ajax";

export class OrderPanel extends LitElement {

  constructor() {
    super();
    this.renderOrders();
    this.subscribeToChanges();
  }

  static styles = css`
    table {
      border: 0.1rem solid #282828;
      border-radius: 0.25rem;
      margin: 2rem auto;
    }

    table thead {
      text-align: center;
    }

    th {
      margin: 5px;
    }

    .tooltip-content {
      background-color: white;
      border: 0.1rem solid #282828;
      padding: 0.5rem;
    }
  `;

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
    const description = order.description.length > 10 ? `${order.description.substring(0, 10)}...` : order.description

    const date = new Date(order.createdAt);
    const createdAt =  date.toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");

    return `
        <td>${order.orderId}</td>
        <td>${order.userId}</td>
        <td>
          <lion-tooltip has-arrow>
            <span slot="invoker">${description}</span>
            <div class="tooltip-content" slot="content">${order.description}</div>
          </lion-tooltip>
        </td>
        <td>${order.status}</td>
        <td>${createdAt}</td>
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
