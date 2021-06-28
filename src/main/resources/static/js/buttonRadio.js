
function buttonRadioClicked(id, idHidden){
    const hidden = document.getElementById(idHidden);
    const pick = document.getElementById(id)
    const value = pick.value
    const h_values = hidden.value.split(" ")
    const index = h_values.indexOf(value)
    console.log("pick value" + pick.value)
    console.log("start " + pick.classList)
    if (index >= 0){
        h_values.splice(index, 1)
        pick.classList.remove("btn-primary")
        pick.classList.add("btn-light")
    } else {
        h_values.push(value)
        pick.classList.add("btn-primary")
        pick.classList.remove("btn-light")
    }
    console.log("end " + pick.classList)
    hidden.value = h_values.join(" ")
    console.log("value=" + hidden.value)
}