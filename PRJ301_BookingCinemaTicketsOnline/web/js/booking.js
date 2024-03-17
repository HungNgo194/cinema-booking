document.addEventListener("DOMContentLoaded", function () {
    const seatsContainer = document.querySelector('.seats');
    const seats = document.querySelectorAll('.seat');
    const selectedSeats = document.getElementById('Seatnumber');
    const count = document.getElementById('count');
    const checkoutBtn = document.getElementById('checkout-btn');
    const backBtn = document.querySelector('.back-btn');
    const coll = document.getElementsByClassName("collapsible");
    const itemTimes = document.querySelectorAll('.item-time');
    const itemShowTimes = document.querySelectorAll('.time-item');
    const numOfBookInput = document.querySelector('input[name="numOfBook"]');
    const totalMoney = document.querySelector('input[name="total"]');
    const seatNums = document.querySelector('input[name="seat"]');
    let selectedSeatCount = 0;
    // Add click event listeners to collapsible elements
    Array.from(coll).forEach(function (collapsible) {
        collapsible.addEventListener("click", function () {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.maxHeight) {
                content.style.maxHeight = null;
            } else {
                content.style.maxHeight = content.scrollHeight + "px";
            }
        });
    });
//   itemTimes.forEach(function (item) {
//        item.addEventListener('click', function () {
//            const showTimeInput = document.querySelector('input[name="showTime"]');
//            // Remove the "active" class from all other item-time elements
//            itemTimes.forEach(function (otherItem) {
//                otherItem.classList.remove('active');
//            });
//            // Toggle the "active" class on the clicked element
//            this.classList.add('active');
//            // Set the value of the input field to the text content of the clicked item
//            showTimeInput.value = this.textContent;
//        });
//    });
// Add click event listener to each seat for seat selection
    seats.forEach(seat => {
        seat.addEventListener('click', () => {
//            const selectedShowTimes = document.querySelectorAll('.item-time.active');
            if (!seat.classList.contains('already-selected')) {
                seat.classList.toggle('selected');
                updateSelectedSeats();
            }
        });
    });
// Function to unselect all seats
    function unselectAllSeats() {
        seats.forEach(seat => {
            seat.classList.remove('selected');
        });
        totalMoney.value = 0;
        numOfBookInput.value = 0;
        seatNums.value = '';
    }

// Function to format numbers with commas
    function numberWithCommas(x) {
        var parts = x.toString().split(".");
        parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        return parts.join(".");
    }

// Function to update selected seats and total cost
    function updateSelectedSeats() {
        const selected = document.querySelectorAll('.seat.selected');
        let selectedSeatNumbers = Array.from(selected).map(seat => seat.innerText);
        selectedSeatCount = selected.length;
        if (selectedSeatCount <= 4 && selectedSeatCount > 0) {
            checkoutBtn.removeAttribute('disabled');
        } else if (selectedSeatCount === 0) {
            checkoutBtn.setAttribute('disabled', 'disabled');
        } else {
            checkoutBtn.setAttribute('disabled', 'disabled');
            alert('Không được chọn quá bốn ghế.');
            selected[selected.length - 1].classList.remove('selected');
            selectedSeatNumbers = selectedSeatNumbers.slice(0, 4);
            selectedSeatCount = 4;
            checkoutBtn.removeAttribute('disabled');
        }

        total = selectedSeatCount * 70000; // Assuming 70 per seat
        const formattedTotal = numberWithCommas(total);
        totalMoney.value = formattedTotal;
        numOfBookInput.value = selectedSeatCount;
        seatNums.value = selectedSeatNumbers;
    }

// Checkout button click event
    checkoutBtn.addEventListener('click', () => {
        if (selectedSeatCount === 0) {
            alert('Xin vui lòng chọn ít nhất một ghế.');
        }
    });
// Back button click event
    backBtn.addEventListener('click', function () {
        window.location.href = backBtn.getAttribute('data-link');
    });
}
);
