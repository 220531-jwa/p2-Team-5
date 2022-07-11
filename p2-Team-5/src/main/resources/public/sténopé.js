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
    window.location.assign("homePage.html");
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

//loginPage
function hidePass()
{
    if (document.getElementById('pKeyBox').getAttribute("type")=="password") {document.getElementById('pKeyBox').setAttribute("type","text");}
    else {document.getElementById('pKeyBox').setAttribute("type","password");}
}

function loginCheck()
{
    let uname = document.getElementById("uNameBox").value; 
    let pkey = document.getElementById("pKeyBox").value;

    console.log(uname + " " + pkey);

    if (uname === "test" && pkey === "positive") 
        {
            console.log("Login succeeded!"); 
            sessionStorage.setItem("uname",uname);
            window.location.assign("homePage.html");
        }
    else {
        console.log("Login failed");
        alert("Login failed");
    }
}

//marketplace 

//userPage 

//petPage
function populatePetPage()
{
    let contentment = ["feral", "dissociating", "bored", "neutral", "amused", "happy", "ecstatic"];
    let hunger = ["dying", "malnourished", "hungry", "neutral", "satisfied", "full", "bloated"]; 
    let owner = 0;
    let pName = "";
    let pSet = 0;
    let fun = 0; 
    let food = 0; 
    let level = 0;
    let sName = "";
    let sSRC = "";

    if (pName != null && sName != null) {document.getElementById("pNameBanner").innerText=`${pName} the ${sName}'s page!`;}

    if (pName != null && sName != null) {document.getElementById("pDataHere").innerHTML = `<h1>${sSRC}</h1><br>
        <a id="ownerName" href="userPage/${owner}"></a>
        <label>Pet Name: <input id="petName" type="text" value="${pName}" readonly> the ${sName}</label><br>
        <label>Pronouns: 
            <select id="petPSet" readonly>
                <option value="1">${pronouns[1]}</option>
                <option value="2">${pronouns[2]}</option>
                <option value="3">${pronouns[3]}</option>
                <option value="4">${pronouns[4]}</option>
                <option value="5">${pronouns[5]}</option>
                <option value="6">${pronouns[6]}</option>
            </select>
        </label><br>
        <label>Contentment: <input id="funBox" type="number" value="${contentment[fun]}" readonly></label><br>
        <label>Hunger: <input id="foodBox" type="number" value="${hunger[food]}" readonly></label><br>
        <label>Level: <input id="levelBox" type="number" value="${level}" readonly></label><br>`;}

    document.getElementById("ownerName").innerText = `User_${owner}`; //do something to get the owner's username here 
    document.getElementById("petPSet").selectedIndex = pSet;
}