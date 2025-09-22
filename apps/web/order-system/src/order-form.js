import {css, html, LitElement} from "lit";
import {ajax} from "@lion/ajax";

export class OrderForm extends LitElement {

  constructor() {
    super();
  }

  submitHandler(ev) {
    const formData = ev.target.serializedValue;
    if (this.validateForm(formData)) return;

    ajax.fetch('http://localhost:8081/order', {
      method: "POST",
      body: JSON.stringify(formData),
      headers: {
        "Content-Type": "application/json",
      },
    })

    this.dispatchEvent(new Event('close-overlay', { bubbles: true }))
  };

  validateForm(formData) {
    let invalid = false;
    const userIdError = this.shadowRoot.getElementById("userid-error");
    const descriptionError = this.shadowRoot.getElementById("description-error");

    userIdError.textContent = ``
    descriptionError.textContent = ``

    if (!formData.userId) {
      userIdError.textContent = `UserId cannot be empty`
      invalid = true;
    }
    if (!formData.description) {
      invalid = true;
      descriptionError.textContent = `Description cannot be empty`
    }
    return invalid
  }

  static styles = css`
    .error {
      color: red;
    }
  `;

  render() {
    return html`
      <lion-form @submit="${this.submitHandler}">
        <form @submit="${ev => ev.preventDefault()}">
          <lion-input name="userId" label="User Id" type="number"></lion-input>
          <span id="userid-error" class="error"></span>
          <lion-textarea name="description" label="Description" rows="4"></lion-textarea>
          <span id="description-error" class="error"></span>
          <button>Submit</button>
        </form>
      </lion-form>
    `;
  }
}
customElements.define('order-form', OrderForm)
