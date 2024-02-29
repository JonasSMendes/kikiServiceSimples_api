const url = `http://localhost:8080/user/all`

const hideLoader = () => {
    document.getElementById("loading").style.display = "none";
}

const titleUser = () => {
    document.getElementById("title-userpage").innerHTML = `Pedidos dos clientes`
}

const show = (users) => {
    let tab = `<thead> 
        <th>#</th>
        <th>Descrição</th>
        <th>username</th>
        <th>User id</th>
    </thead>`;


    users.forEach(user => {
        user.orders.forEach(order => {
            tab += `<tr>
                <td>${order.id}</td>
                <td>${order.service}</td>
                <td>${user.username}</td>
                <td>${user.id}</td>
            </tr>`;
        });
    });

    document.getElementById("order").innerHTML = tab;

    titleUser();

}

async function getAPI(url) {

    const response = await fetch(url, { method: "GET" });


    let data = await response.json();
    console.log(data)

    if (response) {
        hideLoader();
    }

    show(data);

}


getAPI(url);



