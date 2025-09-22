import { LitElement, html, css } from 'lit';

class OrderSystem extends LitElement {
  static properties = {
    header: { type: String },
  }

  static styles = css`
    h1 {
      text-align: center;
    }
    hr {
      margin: 0 2rem;
    }
    .order-form-dialog {
      background-color: white;
      border: 0.25rem #282828;
      border-radius: 0.5rem;
    }

    .order-form-dialog__close-button {
      display: block;
      margin-left: auto;
      background-color: white;
      height: 2rem;
      width: 2rem;
      border: 0;
    }

    .dialog-header {
      display: flex;
      align-items: center;
      margin: 0 1rem;
    }

    .dialog-header h2 {
      margin-right: 2rem;
    }
  `;

  constructor() {
    super();
    this.header = 'Order System';
  }

  render() {
    return html`
      <main>
        <h1>${this.header}</h1>
        <hr>
        <lion-dialog>
          <lion-button slot="invoker">Dodaj zamówienie</lion-button>
          <div slot="content" class="order-form-dialog">
            <div class="dialog-header">
              <h2>Dodawanie nowego zamówienia</h2>
              <button
                class="order-form-dialog__close-button"
                @click="${e => e.target.dispatchEvent(new Event('close-overlay', { bubbles: true }))}"
              >
                X
              </button>
            </div>
            <order-form></order-form>
          </div>
        </lion-dialog>


        <order-panel></order-panel>
      </main>
    `;
  }


}

customElements.define('order-system', OrderSystem);
