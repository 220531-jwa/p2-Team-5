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

async function search()
{
    let searchString = document.getElementById("searchBar").value;
    document.getElementById("searchResult").innerHTML = "";

    let res = await fetch(`/search/${searchString}/pets`, {method: "GET", header: {accept: "application/json", "Content-Type": "text/plain"}, 
        body: null});
    let resJSON = res.json()
        .then((resp) => {
            for (let i=0;i<resp.length;i++)
            {
                let pets = document.createElement("div");
                pets.className = "grid-item";
                pets.innerHTML= `<h1 id="searchResult_${resp[i].id}"><a onclick="viewPet(${resp[i].id})">${resp[i].type.ssrc}</a></h1>
                    <h4>${resp[i].pName}</h4>`;
                document.getElementById("searchResult").appendChild(pets);
            }
        })
        .catch((error) => console.log(error));
}

//loginPage
function hidePass()
{
    if (document.getElementById('pKeyBox').getAttribute("type")=="password") {document.getElementById('pKeyBox').setAttribute("type","text");}
    else {document.getElementById('pKeyBox').setAttribute("type","password");}
}

function createUser() {
    document.getElementById("createUserDiv").innerHTML = 
    `<label>Username: <input id="newUserUName" type="text" maxlength="50"><br>
    <label>Password: <input id="newUserPKey" type="password" maxlength="50"></label> <button onclick="hidePass()">👁</button><br>
    <label>Name: <input id="newUserDName" type="text" maxlength="50"></label><br>
    <label>Pronouns: 
        <select id="newUserPSet">
            <option value="0">${pronouns[0]}</option>
            <option value="1">${pronouns[1]}</option>
            <option value="2">${pronouns[2]}</option>
            <option value="3">${pronouns[3]}</option>
            <option value="4">${pronouns[4]}</option>
            <option value="5">${pronouns[5]}</option>
            <option value="6">${pronouns[6]}</option>
        </select>
    </label><br>
    <label>Blurb: <textarea id="newUserDBlurb" maxlength="200"></textarea></label>
    <button id="submitNewUser" onclick="submitNewUser()">Submit</button>`;
}

async function submitNewUser() {
    let newUser = new Object;
    newUser.id = 0;
    newUser.uName = document.getElementById("newUserUName").value;
    newUser.pKey = document.getElementById("newUserPKey").value;
    newUser.dName = document.getElementById("newUserDName").value;
    newUser.pSet = document.getElementById("newUserPSet").value;
    newUser.dBlurb = document.getElementById("newUserDBlurb").value;
    let res = await fetch(`/create`, 
        {
            method: "POST",
            header: {"Content-Type": "application/json"}, 
            body:JSON.stringify(newUser)
        });
    let resJSON = res.json()
        .then((resp) => 
        {
            console.log(resp);
            window.location.assign('loginPage.html');
        })
        .catch((error) => {
            console.log(error);
            alert("User could not be created");
        });
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
                opt.disabled = false;
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
    } else if (res.status == 204) {
        console.log("Empty Backpack");
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
                opt.disabled = false;
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
    } else if (res.status == 204) {
        console.log("Empty Pet Backpack");
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
                `${baseURL}/users/${u}/items/${itemid}`, {
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
                sessionStorage.setItem("pID", item.pID);
                useItemOnPet(item);
                //setTimeout(function(){window.location.assign("inventory.html")},2000);
            }

            ele.appendChild(useButt);
            break;
        case "Owner": 
            let ownButt = document.createElement("button");
            ownButt.type = 'button';
            ownButt.innerHTML = "Return to Owner!";

            ownButt.onclick = function() {
                giveToPet(itemid, 0);
                setTimeout(function(){window.location.assign("inventory.html")},2000);
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
                setTimeout(function(){window.location.assign("inventory.html")},2000);
            }

            ele.appendChild(petOptions);
            ele.appendChild(giveButt);
            break;
        case "Drop": 
            let dropButt = document.createElement("button");
            dropButt.type = 'button';
            dropButt.innerHTML = "CONFIRM DROP";
            dropButt.onclick = function() {
                dropItem(itemid);
                setTimeout(function(){window.location.assign("inventory.html")},2000);
            }
            ele.appendChild(dropButt);
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
    let res = await fetch(`/users/${a}/items/${Item.id}/use/${b}`, {method: "PATCH", header:{"Content-Type": "application/json"}, body: itemJSON});
    if (res.status == 200) {
        let resJSON = await res.json()
            .then((resp) =>
            {
                //console.log("Yummy " + Item.type.tName);
                alert("Successfully used " + Item.type.tName);
            })
            .catch((error) => console.log(error));
    } else {
        alert("Could not use Item");
    }
    
}

async function dropItem(itemid) //will fail if sessionStorage doesn't hold uID and pID
{
    let u = sessionStorage.getItem("uID");
    let res = await fetch(
        `${baseURL}/users/${u}/items/${itemid}`, {
            method: 'DELETE'
        }
        
    );
    if (res.status == 200) {
        console.log("Deletion Successful");
    } else {
        console.log("Fetch unsuccessful");
    }
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
                getPetsList("pListItems");
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


    res = await fetch(`users/${sessionStorage.userInView}/comments`, {method: `GET`, header:{"Content-Type": "application/json"}, body: null});
    if (res.status == 200) {
        resJson = await res.json()
        .then((resp) => {

            var commentList = document.createElement('table');
            let headers = commentList.insertRow();
            let headerCell = headers.insertCell();
            headerCell.appendChild(document.createTextNode("User"));
            headerCell = headers.insertCell();
            headerCell.appendChild(document.createTextNode("Comment Text"));

            for(let i = 0; i < resp.length; i ++) {
                let row = commentList.insertRow();
                let cell = row.insertCell();
                cell.appendChild(document.createTextNode((resp[i].wID)));
                cell = row.insertCell();
                cell.appendChild(document.createTextNode(resp[i].body));
            }
            document.getElementById("commentsHere").appendChild(commentList);
        })
        .catch((error) => 
        {
            console.log(error);
            alert("Error Loading Comments");
        });
    } else if (res.status == 204) {
        let text = document.createElement("p");
        text.innerHTML = "No Comments, You can change that by adding one!";
        document.getElementById("commentsHere").appendChild(text);
    } else {
        alert("Error Loading Comments")
    }
    
}

function getDispName(id) {
    return "TEMP DISPLAY NAME";
}

async function addComment() {
    let commentBody = document.getElementById("comment").value;
    let comment = {
        "id": 1,
        "wID": sessionStorage.uID,
        "hID": sessionStorage.userInView,
        "body": commentBody
    }

    //console.log(comment);

    res = await fetch(`users/${sessionStorage.uID}/${sessionStorage.userInView}/comment`, {method: `POST`, header:{"Content-Type": "application/json"}, body: JSON.stringify(comment)});
    if (res.status == 201) {
        resJson = await res.json()
        .then((resp) => {
            setTimeout(function(){window.location.assign("userPage.html")},2000);
        })
        .catch((error) => 
        {
            console.log(error);
            alert("Error Posting Comments");
        });
    } else {
        alert("Error Posting Comments")
    }

    return commentBody;
}

async function editPage() {  //unfinished
    let res = await fetch(`users/${sessionStorage.uID}/edit`, 
        {
            method: `PUT`,
            header:{"Content-Type": "application/json"},
            body: null
        });
    let resJson = await res.json()
        .then((resp) => {

        })
        .catch((error) => {
            console.log(error);
            alert("Couldn't edit user page");
        });
}

function viewPet(a) 
{
    sessionStorage.setItem("pID", a); 
    window.location.assign('petPage.html');
}

async function getPetsList (targetDiv) {
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
                    `<h1><a onclick="viewPet(${resp[i].id})">${resp[i].type.ssrc}</a></h1><h4>${resp[i].pName}</h4>`;
                document.getElementById(targetDiv).appendChild(pets);
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
            window.location.assign("userPage.html")
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
    sessionStorage.setItem("userInView",0);
    getPetsList("poundDiv");
    if (sessionStorage.getItem("uID")==null) 
    {
        document.getElementById("submitNewPet").setAttribute("disabled","");
        document.getElementById("submitNewPet").setAttribute("title","Log in first!");
    }
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
                sessionStorage.setItem("currentPet",JSON.stringify(foundPet));

                if (pName != null && sName != null) {document.getElementById("pNameBanner").innerText=`${pName} the ${sName}'s page!`;}

                if (pName != null && sName != null) {document.getElementById("pDataHere").innerHTML = `<h1>${sSRC}</h1><br>
                    <a id="ownerName" href="userPage.html" onclick="viewUser(${owner})"></a><br>
                    <label>Pet Name: <input id="petName" type="text" value="${pName}" readonly> the ${sName}</label><br>
                    <label>Pronouns: 
                        <select id="petPSet">
                            <option id="ps0" value="0">${pronouns[0]}</option>
                            <option id="ps1" value="1">${pronouns[1]}</option>
                            <option id="ps2" value="2">${pronouns[2]}</option>
                            <option id="ps3" value="3">${pronouns[3]}</option>
                            <option id="ps4" value="4">${pronouns[4]}</option>
                            <option id="ps5" value="5">${pronouns[5]}</option>
                            <option id="ps6" value="6">${pronouns[6]}</option>
                        </select>
                    </label><br>
                    <label>Contentment: <input id="funBox" type="text" value="${contentment[fun]}" readonly></label><br>
                    <label>Hunger: <input id="foodBox" type="text" value="${hunger[food]}" readonly></label><br>
                    <label>Level: <input id="levelBox" type="number" value="${level}" readonly></label><br>`;}

                if (owner > 0) {getOwnerUName(owner);}
                else document.getElementById("ownerName").innerText = "Pound";
                document.getElementById("petPSet").selectedIndex = pSet;
                document.getElementById(`ps${pSet}`).setAttribute("selected", "selected");
                if (sessionStorage.getItem("uID")==owner)
                {
                    document.getElementById("petName").removeAttribute("readonly");
                    document.getElementById("pDataHere").innerHTML += `<button id="pSubmit" onclick="modifyPet()">Submit Changes</button>`;
                    document.getElementById("pDataHere").innerHTML += `<button onclick="petToPound()">Send To Pound</button>`;
                }
                else {document.getElementById("petPSet").setAttribute("disabled", true);}
                if (owner==0) {document.getElementById("pDataHere").innerHTML += `<button onclick="petFromPound()">Adopt from Pound</button>`;}
            })

            .catch((error) => {console.log(error)});
}

async function getOwnerUName(uID) {    //still needs some work
    let res = await fetch(`users/${uID}`, 
        {
            method: `GET`,
            header:{"Content-Type": "application/json"},
            body: null
        });
    let resJson = await res.json()
        .then((resp) => {
            let temp = resp.uName;
            document.getElementById("ownerName").innerText = temp;
        })
        .catch((error) => 
        {
            console.log(error);
        });
}

async function modifyPet()
{
    let changedPet = JSON.parse(sessionStorage.getItem("currentPet"));
    changedPet.pName = document.getElementById("petName").value;
    changedPet.pSet = document.getElementById("petPSet").selectedIndex;

    let res = await fetch(`/users/${changedPet.uID}/pets/${changedPet.id}`, {method: "PUT", header: {"Content-Type": "application/json"}, 
        body: JSON.stringify(changedPet)});
    let resJSON = res.json()
        .then((resp) => {window.location.assign("petPage.html");})
        .catch((error) => console.log(error));
}

async function petFromPound()
{
    let changedPet = JSON.parse(sessionStorage.getItem("currentPet"));
    if (sessionStorage.getItem("uID") == null || sessionStorage.getItem("uID")<1) {alert("Bad user ID--try logging in");}
    else 
    {
        changedPet.uID = sessionStorage.getItem("uID");
        let res = await fetch(`/users/0/pets/${changedPet.id}`, {method: "PUT", header: {"Content-Type": "application/json"}, 
            body: JSON.stringify(changedPet)});
        let resJSON = res.json()
            .then((resp) => {window.location.assign("petPage.html");})
            .catch((error) => console.log(error));
    }
}

async function petToPound()
{
    let changedPet = JSON.parse(sessionStorage.getItem("currentPet"));
    let oldOwner = changedPet.uID;
    changedPet.uID = 0;
    {
        let res = await fetch(`/users/${oldOwner}/pets/${changedPet.id}`, {method: "PUT", header: {"Content-Type": "application/json"}, 
            body: JSON.stringify(changedPet)});
        let resJSON = res.json()
            .then((resp) => {window.location.assign("petPage.html");})
            .catch((error) => console.log(error));
    }
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