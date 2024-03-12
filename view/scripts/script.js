const url = `http://localhost:8080/user/all`;

const hideLoader = () => {
    document.getElementById("loading").style.display = "none";
}


function showServices(userId, users) {
    console.log("ID do usuário recebido:", userId); // Verifica se o ID do usuário está sendo recebido corretamente
    console.log("Dados dos usuários disponíveis:", users); // Verifica se os dados dos usuários estão disponíveis

    userId = parseInt(userId); // Convertendo o ID para um número

    const user = users.find(user => user.id === userId);
    console.log("Usuário encontrado:", user); // Verifica se o usuário foi encontrado

    if (user) {
        let orders = "";

        user.orders.forEach(listSection => {
            orders += `<ul class="todo"><li>${listSection.service}</li></ul>`;
        });

        console.log("Lista de pedidos:", orders); // Verifica a lista de pedidos

        document.getElementById("listSection").innerHTML = orders;
    } else {
        console.error("Usuário não encontrado.");
    }
}


const show = (users) => {
    let navBar = "";

    users.forEach(user => {
        navBar += `
            <div class="user-info">
                <div class="user" data-user-id="${user.id}" onmouseenter="showMensager(${user.id})" onmouseleave="hideMensager(${user.id})">
                    <img src="https://i.pinimg.com/736x/a7/d4/5f/a7d45f7fbf41083a48690660b474899b.jpg" alt="">
                </div>
                <div class="user-name" id="${user.id}">
                    <h2>${user.username}</h2>
                </div>
            </div>
        `;
    });

    document.getElementById("navegationSection").innerHTML = navBar;


    const userElements = document.querySelectorAll('.user');
    userElements.forEach(element => {
        element.addEventListener('click', (event) => {
            const userId = event.currentTarget.getAttribute('data-user-id');
            console.log("ID do usuário:", userId);
            showServices(userId, users);
        });
    });

    titleUser();
}

async function getAPI(url) {
    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error('Erro ao obter os dados dos usuários.');
        }
        const data = await response.json();
        console.log(data);
        hideLoader();
        show(data);
    } catch (error) {
        console.error(error.message);
    }
}

getAPI(url);



function showMensager(id) {
    let mensager = document.getElementById(id)
    mensager.style.display = "block"
}

function hideMensager(id) {
    let mensager = document.getElementById(id)
    mensager.style.display = "none"
}