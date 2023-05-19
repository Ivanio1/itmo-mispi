changeOffset();

function clickSubmitButton() {
    let date = new Date();
    let hours = date.getUTCHours() < 10 ? "0" + date.getUTCHours() : date.getUTCHours();
    let minutes = date.getUTCMinutes() < 10 ? "0" + date.getUTCMinutes() : date.getUTCMinutes();
    let seconds = date.getUTCSeconds() < 10 ? "0" + date.getUTCSeconds() : date.getUTCSeconds();
    let day = (date.getUTCDate() < 10 ? '0' : '') + date.getUTCDate();
    let month = (date.getUTCMonth() < 9) ? '0' + (date.getUTCMonth() + 1) : date.getUTCMonth() + 1;
    let year = date.getUTCFullYear();
    let timeValue = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}.000Z`;
    $(".time").val(timeValue);
    setTimeout(function () {
        $(".hidden-submit-button").click();
    }, 1);

}


function offset() {
    $(".x-hidden").val(null);
    $(".y-hidden").val(null);
    $(".r-hidden").val(null);
    setTimeout(function () {
        jQuery(".hidden-submit-btn").click();
    }, 1);

}

function changeR() {
    let date = new Date();
    let hours = date.getUTCHours() < 10 ? "0" + date.getUTCHours() : date.getUTCHours();
    let minutes = date.getUTCMinutes() < 10 ? "0" + date.getUTCMinutes() : date.getUTCMinutes();
    let seconds = date.getUTCSeconds() < 10 ? "0" + date.getUTCSeconds() : date.getUTCSeconds();
    let day = (date.getUTCDate() < 10 ? '0' : '') + date.getUTCDate();
    let month = (date.getUTCMonth() < 9) ? '0' + (date.getUTCMonth() + 1) : date.getUTCMonth() + 1;
    let year = date.getUTCFullYear();
    let timeValue = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}.000Z`;
    $(".time").val(timeValue);
    setTimeout(function () {
        $(".hidden-submit-butt").click();
    }, 1);

}

function changeOffset() {
    let date = new Date();
    let offs = $(".offset-hidden")
    offs.val(date.getTimezoneOffset());


}

setInterval(() => changeOffset(), 1000);




