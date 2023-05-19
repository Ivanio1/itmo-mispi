beginTime();

function beginTime() {
    let container = document.getElementById("time-container");
    changeTime(container);
    const interval = 1;
    setInterval(() => changeTime(container), interval * 1000);

}

function changeTime(container) {
    let date = new Date();
    let hours = date.getUTCHours() < 10 ? "0" + date.getUTCHours() : date.getUTCHours();
    let minutes = date.getUTCMinutes() < 10 ? "0" + date.getUTCMinutes() : date.getUTCMinutes();
    let seconds = date.getUTCSeconds() < 10 ? "0" + date.getUTCSeconds() : date.getUTCSeconds();
    let day = (date.getUTCDate() < 10 ? '0' : '') + date.getUTCDate();
    let month = (date.getUTCMonth() < 9) ? '0' + (date.getUTCMonth() + 1) : date.getUTCMonth() + 1;
    let year = date.getUTCFullYear();
    let timeValue = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}.000Z`;
    //  alert(container.value)
    container.value = timeValue;
}