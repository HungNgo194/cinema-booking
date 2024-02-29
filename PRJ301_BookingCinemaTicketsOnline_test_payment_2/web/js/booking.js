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

    // Add click event listener to each item-time element for date selection
    itemShowTimes.forEach(function (item) {
        item.addEventListener('click', function () {
            const dateInput = document.querySelector('input[name="showDate"]');
            const dateDisplay = this.getElementById('.date');
            dateInput.value = dateDisplay;
        });
    });

    // Add click event listener to each item-time element for show time selection
    itemTimes.forEach(function (item) {
        const showTimeInput = document.querySelector('input[name="showTime"]');
        item.addEventListener('click', function () {
            this.classList.toggle('active');
            if (this.classList.contains('active')) {
                showTimeInput.value = this.textContent;
            } else {
                unselectAllSeats();
                showTimeInput.value = '';
            }
        });
    });

    // Add click event listener to each seat for seat selection
    seats.forEach(seat => {
        seat.addEventListener('click', () => {
//            const selectedDates = document.querySelectorAll('.time-item.active');
            const selectedShowTimes = document.querySelectorAll('.item-time.active');
            if (selectedShowTimes.length === 1) {
                if (!seat.classList.contains('already-selected')) {
                    seat.classList.toggle('selected');
                    updateSelectedSeats();
                }
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
});
