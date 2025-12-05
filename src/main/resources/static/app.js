const API_URL = 'http://localhost:8080';
const USER_ID = 1;

async function loadPortfolio() {
    try {
        const response = await fetch(`${API_URL}/assets/${USER_ID}/price`);
        const assets = await response.json();

        const tableBody = document.getElementById('assets-table-body');
        const totalValueElem = document.getElementById('total-portfolio-value');

        tableBody.innerHTML = '';
        let grandTotal = 0;

        assets.forEach(asset => {
            grandTotal += asset.totalValue;

            const row = `
                <tr>
                    <td>${asset.ticker.toUpperCase()}</td>
                    <td>${asset.amount}</td>
                    <td>${asset.purchasePrice.toFixed(2)} $</td>
                    <td style="color: #fab387">${asset.currentPrice.toFixed(2)} $</td>
                    <td style="color: #a6e3a1; font-weight: bold;">${asset.totalValue.toFixed(2)} $</td>
                </tr>
            `;
            tableBody.innerHTML += row;
        });

        totalValueElem.innerText = grandTotal.toFixed(2) + ' $';

    } catch (error) {
        console.error('Błąd pobierania danych:', error);
        alert('Nie udało się pobrać danych z backendu!');
    }
}

async function addAsset(event) {
    event.preventDefault();

    const ticker = document.getElementById('ticker').value;
    const amount = document.getElementById('amount').value;
    const price = document.getElementById('price').value;

    const data = {
        ticker: ticker,
        amount: parseFloat(amount),
        purchasePrice: parseFloat(price),
        userId: USER_ID
    };

    try {
        const response = await fetch(`${API_URL}/assets`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            alert('Dodano aktywo!');
            loadPortfolio();
            document.getElementById('add-asset-form').reset();
        } else {
            const errorData = await response.json();
            alert('Błąd: ' + JSON.stringify(errorData));
        }
    } catch (error) {
        console.error('Błąd wysyłania:', error);
    }
}
document.getElementById('add-asset-form').addEventListener('submit', addAsset);
loadPortfolio();