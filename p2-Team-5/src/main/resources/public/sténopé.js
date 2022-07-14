//S3 link to be tested
//let baseURL = "http://p2-t5-stenope-bucket.s3-website-us-west-1.amazonaws.com";

//let baseURL = "http://ec2-54-67-101-32.us-west-1.compute.amazonaws.com:8080";
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

function viewUser(a)
{
    if (a != null) {sessionStorage.setItem("userInView",a);}
    window.location.assign('userPage.html');
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
     + `<button id="userLink" onclick="viewUser(${sessionStorage.getItem("uID")})">🧑 ${uname}</button>`;
}

//homepage
function showStoredVariables()
{
    console.log(sessionStorage.getItem("uID"));
    console.log(sessionStorage.getItem("uname"));
    console.log(sessionStorage.getItem("userInView"));
}

// async function viewOtherUserPage() {    //still needs some work
//     document.getElementById("otherUsers").innerHTML = `<label> Select User: <select id="selectUsername"></select></label>`;
//     let res = await fetch(`users/${sessionStorage.uID}/${sessionStorage.otherID}`, 
//         {
//             method: `GET`,
//             header:{"Content-Type": "application/json"},
//             body: null
//         });
//     let resJson = await res.json()
//         .then((resp) => {
//             for (let i = 0; i < resp.length; i++) {
//                 let whichUser = document.createElement("option");
//                 let getOtherUsername = document.createElement(`<a href="${baseURL}/users/${sessionStorage.uID}/${resp[i].uID}">${resp[i].uName}</a>`);
//                 whichUser.appendChild(getOtherUsername);
//                 document.getElementById("selectUsername").appendChild(whichUser);
//             }
//         })
//         .catch((error) => 
//         {
//             console.log(error);
//             alert("No such user");
//         });
// }

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

    if (uname != "" && pkey != "") {
        let userLogin = {
            uName : uname,
            pKey: pkey
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
                sessionStorage.setItem("uID", resp.id);
                sessionStorage.setItem("uname", resp.uName);
                sessionStorage.setItem("pkey", resp.pKey);
                sessionStorage.setItem("dname", resp.dName);
                sessionStorage.setItem("dblurb", resp.dBlurb);
                sessionStorage.setItem("pset", resp.pSet);
                window.location.assign('homePage.html');
            })
            .catch((error) => {
                console.log(error);
                alert("Login unsuccessful");
            })
    } 
}

//marketplace 
async function populateMarketplace() {
    populateTopBar();

    let u = sessionStorage.getItem("uID");

    let res = await fetch(
        `${baseURL}/itemTypes`, {
            method: 'GET'
        }
        
    );
    if (res.status == 200) {
        let resJson = await res.json()
        .then((resp) => {
            let grid = document.getElementById("market");

            for (let i = 0; i < resp.length; i ++) {

                let element  = document.createElement("div");
                element.id = "item-" + resp[i].id;
                element.className = "grid-item";
                //TODO: TITLECASE
                element.appendChild(document.createTextNode(titleCase(resp[i].tName)));

                element.appendChild(document.createElement("br"));

                element.appendChild(document.createTextNode(resp[i].tSRC));

                element.appendChild(document.createElement("br"));

                let button = document.createElement("button");
                button.type = 'button';
                button.innerHTML = "Add to Inventory";

                button.onclick = function() {
                    createItem(resp[i].id);
                }

                element.appendChild(button);


                grid.appendChild(element);
            }
            })
            .catch((error) => {
            console.log(error);
            });
    } else {
        console.log("ERROR");
    }
}

async function createItem(id) {

    let u = sessionStorage.getItem("uID");

    let res = await fetch(
        `${baseURL}/users/${u}/items?typeId=${id}`, {
            method: 'POST'
        }
        
    );
    if (res.status == 200) {
        alert("Successfully added to inventory!");
    } else {
        alert("Something went wrong.");
    }
}

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
                element.id = "item-" + resp[i].id;
                element.className = "grid-item";
                element.appendChild(document.createTextNode(titleCase(resp[i].type.tName)));

                element.appendChild(document.createElement("br"));

                element.appendChild(document.createTextNode(resp[i].type.tSRC));

                element.appendChild(document.createElement("br"));

                division = document.createElement("div");
                division.id = "itemaction"+resp[i].id;

                let selector = document.createElement("select");
                selector.id = "userselector"+resp[i].id;
                selector.setAttribute("onchange", "formatItemAction('"+selector.id+"', '"+division.id+"', '"+resp[i].id+"')");
                
                let opt = document.createElement("option");
                opt.innerHTML = "---";
                opt.selected = true;
                opt.disabled = true;
                selector.appendChild(opt);
                opt = document.createElement("option");
                opt.innerHTML = "Use";
                opt.value = "Use";
                opt.disabled = true;
                selector.appendChild(opt);
                opt = document.createElement("option");
                opt.innerHTML = "Give to Pet";
                opt.value = "Give";
                selector.appendChild(opt);
                opt = document.createElement("option");
                opt.innerHTML = "Drop";
                opt.value = "Drop";
                opt.disabled = true;
                selector.appendChild(opt);
                element.appendChild(selector);

                element.appendChild(document.createElement("br"));

                element.appendChild(division);

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

    let grid = document.getElementById("petBackpack");
    grid.innerHTML = "";
    
    let res = await fetch(
        `${baseURL}/users/${u}/pets/${p}/items`, {
            method: 'GET'
        }
        
    );
    if (res.status == 200) {
        let resJson = await res.json()
        .then((resp) => {
            for (let i = 0; i < resp.length; i ++) {

                let element  = document.createElement("div");
                element.id = i;
                element.className = "grid-item";
                element.appendChild(document.createTextNode(titleCase(resp[i].type.tName)));

                element.appendChild(document.createElement("br"));

                element.appendChild(document.createTextNode(resp[i].type.tSRC));
                
                element.appendChild(document.createElement("br"));

                division = document.createElement("div");
                division.id = "petitemaction"+resp[i].id;

                let selector = document.createElement("select");
                selector.id = "petitemselector"+resp[i].id;
                selector.setAttribute("onchange", "formatItemAction('"+selector.id+"', '"+division.id+"', '"+resp[i].id+"')");
                
                let opt = document.createElement("option");
                opt.innerHTML = "---";
                opt.selected = true;
                opt.disabled = true;
                selector.appendChild(opt);
                opt = document.createElement("option");
                opt.innerHTML = "Use";
                opt.value = "Use";
                opt.disabled = false;
                selector.appendChild(opt);
                opt = document.createElement("option");
                opt.innerHTML = "Give to Pet";
                opt.value = "Give";
                selector.appendChild(opt);
                opt = document.createElement("option");
                opt.innerHTML = "Return to Owner";
                opt.value = "Owner";
                selector.appendChild(opt);
                opt = document.createElement("option");
                opt.innerHTML = "Drop";
                opt.value = "Drop";
                opt.disabled = true;
                selector.appendChild(opt);
                element.appendChild(selector);

                element.appendChild(document.createElement("br"));

                element.appendChild(division);

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

async function formatItemAction(valueid, eleid, itemid) {

    let value = document.getElementById(valueid).value;
    let ele = document.getElementById(eleid);
    ele.innerHTML = "";
    //console.log(ele);
    let u = sessionStorage.getItem("uID");
    //console.log(value);

    switch(value) {
        case "Use": 
            
            let item = null;

            res = await fetch(
                `${baseURL}/users/${u}/pets`, {
                    method: 'GET'
                }
                
            );
            if (res.status == 200) {
                let resJson = await res.json()
        
                .then((resp) => {        
                    
                    item = resp;
        
                    })
                    // .catch will execute if there's any error
                    .catch((error) => {
                    console.log(error);
                    });
            } else {
                console.log("Fetch unsuccessful");
            }
        
            let useButt = document.createElement("button");
            useButt.type = 'button';
            useButt.innerHTML = "Use Item!";

            useButt.onclick = function() {
                savepID(item.pid);
                useItemOnPet(item);
                setTimeout(function(){window.location.assign("inventory.html")},1000);
            }

            ele.appendChild(useButt);
            break;
        case "Owner": 
            let ownButt = document.createElement("button");
            ownButt.type = 'button';
            ownButt.innerHTML = "Return to Owner!";

            ownButt.onclick = function() {
                giveToPet(itemid, 0);
                setTimeout(function(){window.location.assign("inventory.html")},1000);
            }

            ele.appendChild(ownButt);
            break;
        case "Give": 
            
            let petOptions = document.createElement("select");    

            res = await fetch(
                `${baseURL}/users/${u}/pets`, {
                    method: 'GET'
                }
                
            );
            if (res.status == 200) {
                let resJson = await res.json()
        
                .then((resp) => {        
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
                console.log("Fetch unsuccessful");
            }
        
            let giveButt = document.createElement("button");
            giveButt.type = 'button';
            giveButt.innerHTML = "Give To Selected Pet";

            giveButt.onclick = function() {
                giveToPet(itemid, petOptions.value);
                setTimeout(function(){window.location.assign("inventory.html")},1000);
            }

            ele.appendChild(petOptions);
            ele.appendChild(giveButt);
            break;
        case "Drop": 
            alert("NOT IMPLEMENTED. How did you get here?")
            break;
    }


}

async function giveToPet(itemid, petid) {
    console.log("GIVE ITEM " + itemid + "to pet " + petid);

    let u = sessionStorage.getItem("uID");

    res = await fetch(
        `${baseURL}/users/${u}/items/${itemid}/give/${petid}`, {
            method: 'PUT'
        }
        
    );
    if (res.status == 200) {
        console.log("Change Owner Successful");
    } else {
        console.log("Fetch unsuccessful");
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
async function populateUserPage()
{
    populateTopBar();
    let res = await fetch(`users/${sessionStorage.userInView}`, {method: `GET`, header:{"Content-Type": "application/json"}, body: null});
        let resJson = await res.json()
            .then((resp) => {
                let uName = resp.uName;
                let pKey = resp.pKey;
                let dName = resp.dName; 
                let dBlurb = resp.dBlurb;
                let pSet = resp.pSet;
                let comments = {};
                let pets = {};
                document.getElementById("uNameBanner").innerText = `${uName}'s profile page!`;
                document.getElementById("uDataHere").innerHTML = 
                    `<label>Username: <input id="username" type="text" value="${uName}" readonly maxlength="50"></label><br>
                    <label style="display:none" id="psL">Password: 
                    <input id="passkey" type="text" value="${pKey}" readonly maxlength="50"></label><br>
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
                    <label>Name: <input id="dName" type="text" value="${dName}" readonly maxlength="50"></label><br>
                    <label>Description: <textarea id="dBlurb" readonly maxlength="200"></label><br>`;
                    document.getElementById("userPSet").selectedIndex = pSet;
                document.getElementById("dBlurb").value = dBlurb;
                document.getElementById("petsList").innerHTML = `<label>Pets: <div class="grid-container" id="pListItems"></div></label>`
                document.getElementById("addComment").innerHTML = 
                `<label>Comment: <textarea id="comment" placeholder="Write your comment here..." maxlength="200"></textarea></label><br> 
                <button id="submitComment" onclick="addComment()">Submit Comment</button>`;
                // document.getElementById("commentsHere") = comments;
                getPetsList();
                if (sessionStorage.getItem("userInView")==sessionStorage.getItem("uID"))
                {
                    document.getElementById("username").removeAttribute("readonly");
                    document.getElementById("passkey").removeAttribute("readonly");
                    document.getElementById("dName").removeAttribute("readonly");
                    document.getElementById("dBlurb").removeAttribute("readonly");
                    document.getElementById("psL").setAttribute("style", "display:inline");
                    document.getElementById("userPSet").removeAttribute("disabled");
                }
            })
            .catch((error) => 
            {
                console.log(error);
                alert("No such user");
            });
}

function addComment() {
    let comment = document.getElementById("comment").value;
    return comment;
}

function savepID(a) {sessionStorage.setItem("pID", a); return true;}

async function getPetsList () {
    let res = await fetch(`users/${sessionStorage.userInView}/pets`, 
        {
            method: `GET`,
            header:{"Content-Type": "application/json"},
            body: null
        });
    let resJson = await res.json()
        .then((resp) => {
            for (let i = 0; i < resp.length; i++) {
                let pets = document.createElement("div");
                pets.className = "grid-item";
                pets.innerHTML= 
                    `<h1><a href="petPage.html" onclick="savepID(${resp.pID})">${resp[i].type.ssrc}</a></h1><h6>${resp[i].pName}</h6>`;
                document.getElementById("pListItems").appendChild(pets);
            }
        })
        .catch((error) => console.log(error));
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
    newPet.uID = sessionStorage.getItem("uID");
    newPet.pName = document.getElementById("petName").value;
    newPet.pSet = document.getElementById("petPSet").value;
    newPet.fun = document.getElementById("funBox").value;
    newPet.food = document.getElementById("foodBox").value;
    newPet.level = document.getElementById("levelBox").value;
    newPet.type = new Object;
    newPet.type.id = document.getElementById("petSpeciesSelector").value;
    newPet.type.ssrc = "";
    newPet.type.sname = "";
    let res = await fetch(`users/${newPet.uID}/pets`, {method: "POST", header: {"Content-Type": "application/json"}, 
        body:JSON.stringify(newPet)});
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
    <label>Pet Name: <input id="petName" type="text" maxlength="50"></label><br>
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
    <label style="visibility:hidden">Contentment: <input id="funBox" type="text" value="3" style="visibility:hidden" readonly></label><br>
    <label style="visibility:hidden">Hunger: <input id="foodBox" type="text" value="3" style="visibility:hidden" readonly></label><br>
    <label style="visibility:hidden">Level: <input id="levelBox" type="number" value="1" style="visibility:hidden" readonly></label><br>`;  
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
    if (sessionStorage.getItem("userInView") != null) {owner = sessionStorage.getItem("userInView");}

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
    let str = "";
    for (let x of splitStr) {
        str += x.charAt(0).toUpperCase() + x.slice(1) +" ";
    }
    return str.substring(0, str.length - 1);
}