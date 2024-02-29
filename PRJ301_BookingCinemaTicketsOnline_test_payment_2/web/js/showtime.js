var coll = document.getElementsByClassName("collapsible");
var itemTimes = document.querySelectorAll('.item-time');
var itemShowTimes = document.querySelectorAll('.time-item');
var i;

for (i = 0; i < coll.length; i++) {
    coll[i].addEventListener("click", function () {
        this.classList.toggle("active");
        var content = this.nextElementSibling;
        if (content.style.maxHeight) {
            content.style.maxHeight = null;
        } else {
            content.style.maxHeight = content.scrollHeight + "px";
        }
    });
}
itemShowTimes.forEach(function (item) {
    // Add click event listener
    item.addEventListener('click', function () {
        // Remove the "active" class from all other item-time elements
        itemShowTimes.forEach(function (otherItem) {
            if (otherItem !== item) {
                otherItem.classList.remove('active');
            }
        });
        // Toggle the "active" class on the clicked element
        this.classList.toggle('active');
    });
});
itemTimes.forEach(function (item) {
    // Add click event listener
    item.addEventListener('click', function () {
        // Remove the "active" class from all other item-time elements
        itemTimes.forEach(function (otherItem) {
            if (otherItem !== item) {
                otherItem.classList.remove('active');
            }
        });
        // Toggle the "active" class on the clicked element
        this.classList.toggle('active');
    });
});