function openForm() {
    document.getElementById("myForm").style.display = "block";
    document.getElementById("myForm").classList.add('visible');
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}
// BOOKING 
$('.cinema-seats .seat').on('click', function () {
    $(this).toggleClass('active');
});