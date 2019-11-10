
function over(event)
{
    if(!event.target.clicked)
    {
        event.target.style.backgroundColor="#004080";
        event.target.style.color="white";

    }
}

function leave(event)
{
    if(!event.target.clicked)
    {
        event.target.style.backgroundColor="#003366";
        event.target.style.color="white";
    }
}


function clickRegistry(event) {
    document.getElementById("registry").style.display='block';
    document.getElementById("login").style.display='none';
}
function clickLogin(event) {
    document.getElementById("registry").style.display='none';
    document.getElementById("login").style.display='block';
}

function showLabel() {
    document.getElementById("loginLabel").style.color='white';
}


