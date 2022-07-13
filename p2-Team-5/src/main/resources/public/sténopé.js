let baseURL = "http://localhost:8080";

let pronouns = 
[	//subject / object / adj. possessive / obj. possesive / reflexive
    "e/em/eir/eirs/emself", //Spivak set
    "ey/em/eir/eirs/emself", //Elverson set
    "he/him/his/his/himself", //traditional masculine
    "she/her/her/hers/herself",  //traditional feminine
    "they/them/thier/theirs/themself", //singular 'they'
    "xe/xem/xyr/xyrs/xemself", // There's like 15 ways of spelling this one
    "ze/zir/zir/zirs/zirself" // Ze/zir salad
]; //pulled from en.pronouns.page

//topBar
function logout()
{
    sessionStorage.clear();
    window.location.assign("loginPage.html");
}

function populateTopBar()
{
    let uname = "User Page";
    let keyButton = "Login";
    let onclick = "location.href='loginPage.html'";
    if (sessionStorage.getItem("uname") != null) 
    {
        uname = sessionStorage.getItem("uname");
        keyButton = "Logout";
        onclick = "logout()";
    }

    document.getElementById("topBarInsert").innerHTML = `<button id="homeLink" onclick="location.href='homePage.html'">🏠 Home</button>`
     + `<button id="inventoryLink" onclick="location.href='inventory.html'">🎒 Items</button>`
     + `<button id="loginLink" onclick=${onclick}>🔑 ${keyButton}</button>`
     + `<button id="marketLink" onclick="location.href='marketplace.html'">💹 Market</button>`
     + `<button id="userLink" onclick="location.href='userPage.html'">🧑 ${uname}</button>`;
}

//homepage

//loginPage
function hidePass()
{
    if (document.getElementById('pKeyBox').getAttribute("type")=="password") {document.getElementById('pKeyBox').setAttribute("type","text");}
    else {document.getElementById('pKeyBox').setAttribute("type","password");}
}

async function loginCheck()
{
    let uname = document.getElementById("uNameBox").value; 
    let pkey = document.getElementById("pKeyBox").value;

    console.log(uname + " " + pkey);

    sessionStorage.setItem("uID", 1);
    sessionStorage.setItem("userInView", "BUTT");
    window.location.assign('homePage.html');
    /*
    if (uname != "" && pkey != "") {
        let userLogin = {
            username : uname,
            passkey: pkey
        };

        let userLoginJson = JSON.stringify(userLogin);
        let res = await fetch(`/login`, {
                method: `POST`,
                header: {"Content-Type": "application/json"},
                body: userLoginJson
        });
        let resJson = await res.json()
            .then((resp) => {
                console.log(resp);
                sessionStorage.setItem("uID", resp.uId);
                sessionStorage.setItem("userInView", resp.uname);
                window.location.assign('homePage.html');
            })
            .catch((error) => {
                console.log(error);
                alert("Login unsuccessful");
            })
    } 
    */
}

//marketplace 

//inventory
async function populateInventory() {
    populateTopBar();

    let u = sessionStorage.getItem("uID");

    let res = await fetch(
        `${baseURL}/users/${u}/items`, {
            method: 'GET'
        }
        
    );
    if (res.status == 200) {
        let resJson = await res.json()
        .then((resp) => {
            let grid = document.getElementById("backpack");

            for (let i = 0; i < resp.length; i ++) {

                let element  = document.createElement("div");
                element.id = i;
                element.className = "grid-item";
                element.appendChild(document.createTextNode(titleCase(resp[i].type.tName)));
                element.appendChild(document.createElement("br"));
                element.appendChild(document.createTextNode(resp[i].type.tSRC));
                element.appendChild(document.createElement("br"));
                let selector = document.createElement("select");
                let opt = document.createElement("option");
                opt.innerHTML = "---";
                opt.selected = true;
                opt.disabled = true;
                selector.appendChild(opt);
                opt = document.createElement("option");
                opt.onclick = "useItem()";
                opt.innerHTML = "Use";
                selector.appendChild(opt);
                opt = document.createElement("option");
                opt.onclick = "giveToPet()";
                opt.innerHTML = "Give to Pet";
                selector.appendChild(opt);
                opt = document.createElement("option");
                opt.onclick = "dropItem()";
                opt.innerHTML = "Drop";
                selector.appendChild(opt);
                element.appendChild(selector);


                grid.appendChild(element);
            }


            })
            .catch((error) => {
            console.log(error);
            });
    } else {
        console.log("User Does not exist");
    }

    res = await fetch(
        `${baseURL}/users/${u}/pets`, {
            method: 'GET'
        }
        
    );
    if (res.status == 200) {
        let resJson = await res.json()

        .then((resp) => {
            let petOptions = document.getElementById("petDrop");

            for (let i = 0; i < resp.length; i ++) {
                let opt = document.createElement("option");
                opt.value = resp[i].id;
                opt.innerHTML = resp[i].pName;
                petOptions.appendChild(opt);
            }


            })
            // .catch will execute if there's any error
            .catch((error) => {
            console.log(error);
            });
    } else {
        console.log("User Does not exist");
    }
}

async function loadPetBackpack() {
    
    let u = sessionStorage.getItem("uID");
    let select = document.getElementById('petDrop');
    let p = select.options[select.selectedIndex].value;
    
    let res = await fetch(
        `${baseURL}/users/${u}/pets/${p}/items`, {
            method: 'GET'
        }
        
    );
    if (res.status == 200) {
        let resJson = await res.json()
        .then((resp) => {

            let grid = document.getElementById("petBackpack");

            for (let i = 0; i < resp.length; i ++) {

                let element  = document.createElement("div");
                element.id = i;
                element.className = "grid-item";
                element.appendChild(document.createTextNode(titleCase(resp[i].type.tName)));
                element.appendChild(document.createElement("br"));
                element.appendChild(document.createTextNode(resp[i].type.tSRC));
                element.appendChild(document.createElement("br"));
                let selector = document.createElement("select");
                let opt = document.createElement("option");
                opt.innerHTML = "---";
                opt.selected = true;
                opt.disabled = true;
                selector.appendChild(opt);
                opt = document.createElement("option");
                opt.onclick = "useItem()";
                opt.innerHTML = "Use";
                selector.appendChild(opt);
                opt = document.createElement("option");
                opt.onclick = "giveToPet()";
                opt.innerHTML = "Give to Pet";
                selector.appendChild(opt);
                opt = document.createElement("option");
                opt.onclick = "dropItem()";
                opt.innerHTML = "Drop";
                selector.appendChild(opt);
                element.appendChild(selector);


                grid.appendChild(element);
            }


            })
            // .catch will execute if there's any error
            .catch((error) => {
            console.log(error);
            });
    } else {
        console.log("Pet Does not exist");
    }
}

async function useItemOnPet(Item) //will fail if sessionStorage doesn't hold uID and pID
{
    let a = sessionStorage.getItem("uID");
    let b = sessionStorage.getItem("pID");
    let itemJSON = JSON.stringify(Item);
    let res = await fetch(`/users/${a}/pets/${b}/item`, {method: "PATCH", header:{"Content-Type": "application/json"}, body: itemJSON});
    let resJSON = await res.json()
            .then((resp) =>
            {
                console.log(resp);
            })
            .catch((error) => console.log(error));
}

//userPage 
function populateUserPage()
{
    populateTopBar();
    let uName = "";
    let pKey = "";
    let dName = ""; 
    let dBlurb = "";
    let pSet = 0;
    document.getElementById("uDataHere").innerHTML = 
        `<label>Username: <input id="username" type="text" value="${uName}" readonly></label><br>
        <label style="display:none">Password: <input id="passkey" type="text" value="${pKey}" readonly></label><br>
        <label>Pronouns: 
            <select id="userPSet" disabled>
                <option value="0">${pronouns[0]}</option>
                <option value="1">${pronouns[1]}</option>
                <option value="2">${pronouns[2]}</option>
                <option value="3">${pronouns[3]}</option>
                <option value="4">${pronouns[4]}</option>
                <option value="5">${pronouns[5]}</option>
                <option value="6">${pronouns[6]}</option>
            </select>
        </label><br>
        <label>Name: <input id="dName" type="text" value="${dName}" readonly></label><br>
        <label>Description: <textarea id="dBlurb" readonly></label><br>`;
        document.getElementById("userPSet").selectedIndex = pSet;
    document.getElementById("dBlurb").value = dBlurb;
    document.getElementById("commentsHere").innerHTML = "";
}

//createPet
async function getSpeciesList()
{
    let res = await fetch(`/petTypes`, {method: "GET", header:{"Content-Type": "application/json"}, body: null});
    let resJSON = await res.json()
        .then((resp) =>
        {
            for (let i=0;i<resp.length;i++)
            {
                let species = document.createElement("option");
                species.value = resp[i].id;
                species.innerText = `${resp[i].ssrc}: ${resp[i].sname}`;
                document.getElementById("petSpeciesSelector").appendChild(species);
            }
        })
        .catch((error) => console.log(error));
}

async function postNewPet()
{
    let newPet = new Object;
    newPet.id = 0;
    newPet.uID = document.getElementById("newPetUID").value;
    newPet.pName = document.getElementById("petName").value;
    newPet.pSet = document.getElementById("petPSet").value;
    newPet.fun = document.getElementById("funBox").value;
    newPet.food = document.getElementById("foodBox").value;
    newPet.level = document.getElementById("levelBox").value;
    newPet.type = new Object;
    newPet.type.id = document.getElementById("petSpeciesSelector").value;
    newPet.type.ssrc = "";
    newPet.type.sname = "";
    let res = await fetch(`users/${newPet,uID}/pets`, {method: "POST", header: {"Content-Type": "application/json", 
        body:JSON.stringify(newPet)}});
    let resJSON = res.json()
        .then((resp) => 
        {
            console.log(resp);
        })
        .catch((error) => console.log(error));
}

function populateCreatePage()
{
    document.getElementById("creationDiv").innerHTML = 
    `<label>Owner: You! <input id="newPetUID" value="${sessionStorage.getItem("uID")}" style="visibility:hidden" readonly><br>
    <label>Species: <select id="petSpeciesSelector"></select></label><br>
    <label>Pet Name: <input id="petName" type="text"></label><br>
    <label>Pronouns: 
        <select id="petPSet">
            <option value="0">${pronouns[0]}</option>
            <option value="1">${pronouns[1]}</option>
            <option value="2">${pronouns[2]}</option>
            <option value="3">${pronouns[3]}</option>
            <option value="4">${pronouns[4]}</option>
            <option value="5">${pronouns[5]}</option>
            <option value="6">${pronouns[6]}</option>
        </select>
    </label><br>
    <button id="submitNewPet" onclick="postNewPet()">Submit</button>
    <label>Contentment: <input id="funBox" type="text" value="3" style="visibility:hidden" readonly></label><br>
    <label>Hunger: <input id="foodBox" type="text" value="3" style="visibility:hidden" readonly></label><br>
    <label>Level: <input id="levelBox" type="number" value="1" style="visibility:hidden" readonly></label><br>`;  
    getSpeciesList();  
}

//petPage
async function populatePetPage()
{
    populateTopBar();
    let contentment = ["feral", "dissociating", "bored", "neutral", "amused", "happy", "ecstatic"];
    let hunger = ["dying", "malnourished", "hungry", "neutral", "satisfied", "full", "bloated"]; 
    let pID = 0;
    let owner = 0;
    let pName = null;
    let pSet = 0;
    let fun = 0; 
    let food = 0; 
    let level = 0;
    let sName = null;
    let sSRC = null;

    if (sessionStorage.getItem("pID") != null) {pID = sessionStorage.getItem("pID");}
    if (sessionStorage.getItem("userInView") != null) {pID = sessionStorage.getItem("userInView");}

    let foundPet;
    let res = await fetch(`/users/${owner}/pets/${pID}`, {method: "GET", header: {"Content-Type": "application/json"}, body: null});
    let resJSON = await res.json()
            .then((resp) => 
            {
                console.log(resp); 
                foundPet = resp;
                pID = foundPet.id;
                owner = foundPet.uID; 
                pName = foundPet.pName;
                pSet = foundPet.pSet;
                fun = foundPet.fun;
                food = foundPet.food;
                level = foundPet.level;
                sName = foundPet.type.sname;
                sSRC = foundPet.type.ssrc;

                if (pName != null && sName != null) {document.getElementById("pNameBanner").innerText=`${pName} the ${sName}'s page!`;}

                if (pName != null && sName != null) {document.getElementById("pDataHere").innerHTML = `<h1>${sSRC}</h1><br>
                    <a id="ownerName" href="userPage/${owner}"></a><br>
                    <label>Pet Name: <input id="petName" type="text" value="${pName}" readonly> the ${sName}</label><br>
                    <label>Pronouns: 
                        <select id="petPSet" disabled>
                            <option value="0">${pronouns[0]}</option>
                            <option value="1">${pronouns[1]}</option>
                            <option value="2">${pronouns[2]}</option>
                            <option value="3">${pronouns[3]}</option>
                            <option value="4">${pronouns[4]}</option>
                            <option value="5">${pronouns[5]}</option>
                            <option value="6">${pronouns[6]}</option>
                        </select>
                    </label><br>
                    <label>Contentment: <input id="funBox" type="text" value="${contentment[fun]}" readonly></label><br>
                    <label>Hunger: <input id="foodBox" type="text" value="${hunger[food]}" readonly></label><br>
                    <label>Level: <input id="levelBox" type="number" value="${level}" readonly></label><br>`;}

                document.getElementById("ownerName").innerText = `User_${owner}`; //do something to get the owner's username here 
                document.getElementById("petPSet").selectedIndex = pSet;
            })

            .catch((error) => {console.log(error)});
}

//Utility
function titleCase(sentence) {
    let splitStr = sentence.split(" ");
    console.log(splitStr);
    let str = "";
    for (let x of splitStr) {
        console.log(x);
        str += x.charAt(0).toUpperCase() + x.slice(1);
    }
    return str;
}